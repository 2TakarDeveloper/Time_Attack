package Root.GameObjects.PickUps;

import Root.GameObjects.Enemy;
import Root.GameObjects.Player;
import Root.CustomContol.CustomLable;
import Root.scenes.GameScene;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;


public class SpeedDown extends Pickup {

    public SpeedDown(int height, int width, Player player, CustomLable ScoreLable){
        super(ScoreLable);
        setPlayer(player);
        setHeight(height);
        setWidth(width);
        setFill(new ImagePattern(new Image("image/SpeedDown.gif")));
        thisThread = new Thread(this);
        thisThread.start();
    }


    @Override
    public void Trigger() {
        ScoreLable.setValue(ScoreLable.getValue()-40);
        GameScene.playerToolTip.setText("Enemies got slower");
        GameScene.playerToolTip.setTextFill(Color.LIME);
        GameScene.ft.playFromStart();


        for (Enemy e:Enemy.list) {
            e.setSpeed(e.getSpeed()-.2);
        }
    }

    @Override
    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {

                if(this.intersect(player) && isVisible()){
                    Trigger();
                    collidesWithPlayer();

                }
            });


            try {
                Thread.sleep(20);

            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
