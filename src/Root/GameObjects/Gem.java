package Root.GameObjects;

import Root.CustomContol.CustomLable;
import Root.scenes.GameScene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.application.Platform;
import javafx.scene.paint.Paint;


import java.util.Random;

import static Root.scenes.GameScene.isPaused;


public class Gem extends MovableObject {

    private Random randomNumber;
    private Player player;
    private CustomLable ScoreLable;




    public Gem(double centerX, double centerY, double radius, Player player, CustomLable ScoreLable){
        super(centerX, centerY, radius, "Yellow");
        setFill(new ImagePattern(new Image("image/Gem.gif")));
        this.player = player;
        this.ScoreLable = ScoreLable;

        randomNumber = new Random();
        this.setBounds();


        thisThread = new Thread(this);
        thisThread.start();


    }







    public synchronized void resume() {
        isPaused = false;
        notify();
    }


    private void setPosition(){
        this.setCenterX(getRadius()*2 + randomNumber.nextInt((int) getScene().getWidth() - (int)getRadius()*2 ));
        this.setCenterY(getRadius()*2 + randomNumber.nextInt((int) getScene().getHeight() - (int)getRadius()*2 ));

    }






    void collides(Enemy enemy){

        this.setSpeed(enemy.getSpeed());

        if (this.getCenterY() > enemy.getCenterY()) {
            enemy.setVerticalDirection(false);
            if(lowerBound < getScene().getHeight())
                this.moveDown();
        } else if (this.getCenterY() < enemy.getCenterY()) {
            enemy.setVerticalDirection(true);
            if(upperBound > getScene().getY())
                this.moveUp();
        }
        if (this.getCenterX() > enemy.getCenterX()) {
            enemy.setHorizontalDirection(false);
            if(rightBound < getScene().getWidth())
                this.moveRight();
        } else if (this.getCenterX() > enemy.getCenterX()) {
            enemy.setHorizontalDirection(false);
            if(leftBound > getScene().getX())
                this.moveLeft();
        }

    }

    private void collidesWithPlayer(){
        if(!Player.dead){
            ScoreLable.setValue(ScoreLable.getValue() + 20);
            GameScene.playerToolTip.setText("+20");
            GameScene.playerToolTip.setTextFill(Paint.valueOf("White"));
            GameScene.ft.playFromStart();
            this.setPosition();
        }
    }

    public void run() {
        while(!Player.dead) {
            Platform.runLater(() -> {
                if (this.intersect(player)) {
                    this.collidesWithPlayer();
                }
            });
            try {
                Thread.sleep(40);
                synchronized (this) {
                    while (isPaused) {
                        wait();
                    }
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
