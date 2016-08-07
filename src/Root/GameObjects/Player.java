package Root.GameObjects;


import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import Root.scenes.GameScene;
import javafx.scene.paint.Paint;

public class Player extends MovableObject {
    public static boolean dead = false;
    private int healthPoint=100;
    public synchronized void resume() {
        GameScene.isPaused = false;
        notify();
    }

    public Player(double centerX, double centerY, double radius) {

        super(centerX, centerY, radius, "green");
        setFill(new ImagePattern(new Image("image/Player.gif")));
        thisThread = new Thread(this);
        thisThread.start();
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void addHealth(int healthPoint) {
        GameScene.playerToolTip.setText("+"+healthPoint);
        GameScene.playerToolTip.setTextFill(Color.LIMEGREEN);
        GameScene.ft.playFromStart();
        this.healthPoint += healthPoint;
    }

    public void substractHealth(int healthPoint) {
        if(this.healthPoint-healthPoint>0){
            this.healthPoint -= healthPoint;
            GameScene.playerToolTip.setText("Ouch!");
            GameScene.playerToolTip.setTextFill(Paint.valueOf("Red"));
            GameScene.ft.playFromStart();
            //Give red effect on player
            this.setEffect (new Bloom (0.3));
        }
        else dead=true;

    }



    private void movePlayer(){
        getScene().addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
            this.setEffect (new Bloom (1));
            if (!GameScene.isPaused) {
                if (e.getSceneX() < getScene().getWidth() && e.getSceneX() > getScene().getX()) {
                    this.setCenterX(e.getSceneX());

                }
                if (e.getSceneY() < getScene().getHeight() && e.getSceneY() > getScene().getY()) {
                    this.setCenterY(e.getSceneY());
                }
                updateTooltipLocation();
            }


        });
    }

    private void updateTooltipLocation(){
        GameScene.playerToolTip.setTranslateX(this.getCenterX()-20);
        GameScene.playerToolTip.setTranslateY(this.getCenterY()-50);
    }

    public void run() {
        while (!dead) {
            Platform.runLater(() -> {
                    movePlayer ();

                }
            );


            try {
                Thread.sleep(20);
                synchronized (this) {
                    while (GameScene.isPaused) {
                        wait();
                    }
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
