package Root.GameObjects.PickUps;


import Root.GameObjects.Player;
import Root.CustomContol.CustomLable;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static Root.scenes.GameScene.isPaused;

public abstract class Pickup extends Rectangle implements Runnable {
    boolean isDead;
    Player player;
    Random randomPostion;
    Thread thisThread;
    CustomLable ScoreLable;

    public Pickup(CustomLable ScoreLable) {
        this.ScoreLable=ScoreLable;
        this.setVisible(false);
        isDead = false;
    }

    boolean intersect(Player player) { //returns true if collides with player
        return (player.intersects(this.getBoundsInParent()));
    }

    public void setPlayer(Player player) {
        randomPostion = new Random();
        this.player = player;
    }

    public void setRandomPosition(){
        this.setX(1+(int)(Math.random() * 800));
        this.setY(1+(int)(Math.random() * 600));
    }

    public abstract void Trigger();

    void collidesWithPlayer(){
        if(!Player.dead){
            this.setVisible(false);
            this.setRandomPosition();

        }
    }

    public synchronized void resume() {
        isPaused = false;
        notify();
    }

    public void run() {
        while (isVisible()) {
            try {
                if(this.intersect(player)){
                    collidesWithPlayer();
                }
                Thread.sleep(30);

            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
