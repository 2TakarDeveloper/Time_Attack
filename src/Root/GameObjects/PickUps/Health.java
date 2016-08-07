package Root.GameObjects.PickUps;

import Root.GameObjects.Player;
import Root.CustomContol.CustomLable;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;




public class Health extends Pickup {


    public Health(int height, int width, Player player, CustomLable ScoreLable){
        super(ScoreLable);
        setVisible(false);
        setPlayer(player);
        setHeight(height);
        setWidth(width);
        setFill(new ImagePattern(new Image("image/HP.gif")));
        thisThread = new Thread(this);
        thisThread.start();
    }


    @Override
    public void Trigger() {
        ScoreLable.setValue(ScoreLable.getValue()+10);
        player.addHealth(20);
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
