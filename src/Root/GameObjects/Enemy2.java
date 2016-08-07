package Root.GameObjects;

import Root.GameObjects.PickUps.HourGlass;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import Root.scenes.GameScene;

import static java.lang.Math.abs;



public class Enemy2 extends Enemy {

    private Player player;
    private Gem gem;
    private boolean facingRight=true;


    public Enemy2(double centerX, double centerY,double radius, Player player, Gem gem) {
        super(centerX, centerY, radius, "Blue", player, gem);
        setFill(new ImagePattern(new Image("image/Ghost.gif")));
        this.player = player;
        this.gem = gem;

        setSpeed(1);
        thisThread = new Thread(this);
        thisThread.start();
    }

    private void followPlayer(){
        double dx = player.getCenterX() - this.getCenterX();
        double dy = player.getCenterY() - this.getCenterY();
        double m = dy/dx;

        if(player.getCenterX() < this.getCenterX()){//goes left
            if(facingRight){
                this.setScaleX(getScaleX()*-1);
                facingRight=!facingRight;
            }
            if(player.getCenterY() > this.getCenterY()){
                if(abs(dx) > abs(dy)){

                    this.setCenterX(this.getCenterX() - this.getSpeed());
                    this.setCenterY(this.getCenterY() - (this.getSpeed()*m));
                }
                else{
                    this.setCenterX(this.getCenterX() + (this.getSpeed()*(1/m)));
                    this.setCenterY(this.getCenterY() + this.getSpeed());
                }
            }
            else{
                if(abs(dx) > abs(dy)){
                    this.setCenterX(this.getCenterX() - this.getSpeed());
                    this.setCenterY(this.getCenterY() - (this.getSpeed()*m));
                }
                else{
                    this.setCenterX(this.getCenterX() - (this.getSpeed()*(1/m)));
                    this.setCenterY(this.getCenterY() - this.getSpeed());
                }
            }
        }
        else if(player.getCenterX() > this.getCenterX()){//goes right
            if(!facingRight){
                this.setScaleX(getScaleX()*-1);
                facingRight=!facingRight;
            }

            if(player.getCenterY() < this.getCenterY()){
                if(abs(dx) > abs(dy)){
                    this.setCenterX(this.getCenterX() + this.getSpeed());
                    this.setCenterY(this.getCenterY() + (this.getSpeed()*m));
                }
                else{
                    this.setCenterX(this.getCenterX() - (this.getSpeed()*(1/m)));
                    this.setCenterY(this.getCenterY() - this.getSpeed());
                }
            }
            else{
                if(abs(dx) > abs(dy)){
                    this.setCenterX(this.getCenterX() + this.getSpeed());
                    this.setCenterY(this.getCenterY() + (this.getSpeed()*m));
                }
                else{
                    this.setCenterX(this.getCenterX() + (this.getSpeed()*(1/m)));
                    this.setCenterY(this.getCenterY() + this.getSpeed());
                }
            }
        }
    }


    public void run() {
        //This object needs to follow the player
        while (!Player.dead) {
            Platform.runLater(() -> {
                this.followPlayer();

                if (this.intersect(player)) {
                    player.substractHealth(1);
                }

                if (this.intersect(gem)) {
                    gem.collides(this);
                }
            });
            try {
                Thread.sleep(40);
                synchronized (this) {
                    while (GameScene.isPaused || HourGlass.isPaused()) {
                        wait();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (Exception ignored) {}
        }
    }
}