package Root.GameObjects;

import Root.scenes.GameScene;

import java.util.ArrayList;

public abstract class Enemy extends MovableObject {
    boolean horizontalDirection;
    boolean verticalDirection;
    Player player;
    Gem gem;
    public static ArrayList<Enemy> list = new ArrayList<>();

    Enemy(double centerX, double centerY, double radius, String color, Player player, Gem gem) {
        super(centerX, centerY, radius, color);
        this.player = player;
        this.gem = gem;
    }

    public synchronized void resume() {
        GameScene.isPaused = false;
        notify();
    }


    void setHorizontalDirection(boolean direction){
        horizontalDirection = direction;
    }
    void setVerticalDirection(boolean direction){
        verticalDirection = direction;
    }
}
