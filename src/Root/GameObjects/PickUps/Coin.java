package Root.GameObjects.PickUps;

import Root.Application.AudioManager;
import Root.GameObjects.Player;
import Root.CustomContol.CustomLable;
import Root.scenes.GameScene;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;


/**
 * Created by tazim on 6/17/2016.
 */
public class Coin extends Pickup {


    ObjectTimer timer;

    public Coin(int height, int width, Player player, CustomLable ScoreLable) {
        super (ScoreLable);
        setPlayer (player);
        setHeight (height);
        setWidth (width);
        setFill (new ImagePattern (new Image ("image/Coin.gif")));
        thisThread = new Thread (this);
        thisThread.start ();
    }


    @Override
    public void Trigger() {
        ScoreLable.setValue (ScoreLable.getValue () + 50);
        GameScene.playerToolTip.setText("+50");
        GameScene.playerToolTip.setTextFill(Paint.valueOf("White"));
        GameScene.ft.playFromStart();
        AudioManager.CoinAudio ();
    }

    @Override
    public void run() {
        while (!Player.dead) {
            Platform.runLater (() -> {
                if (this.intersect (player) && isVisible ()) {
                    Trigger ();
                    collidesWithPlayer ();

                }
            });


            try {
                Thread.sleep (20);

            } catch (Exception ignored) {
                ignored.printStackTrace ();
            }
        }
    }
}
