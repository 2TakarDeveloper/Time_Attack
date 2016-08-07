package Root.scenes;


import Root.Application.AudioManager;
import Root.Application.Main;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class LoadingScreen {
    public Scene scene;
    private ImageView loadingScreen;
    private MediaPlayer mediaPlayer;
    public LoadingScreen(Main mainMenu) {


        Image loadingImage = new Image("image/loadingBGI.jpg");
        loadingScreen= new ImageView(loadingImage);
        loadingScreen.setFitWidth(800);
        loadingScreen.setFitHeight(600);
        loadingScreen.setPreserveRatio(true);
        loadingScreen.setSmooth(true);
        loadingScreen.setCache(true);


        setScene(mainMenu);

    }

    public void setScene(Main mainMenu) {



        VBox layout = new VBox(20,loadingScreen);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #000000;");
        scene = new Scene(layout, 800, 600);


        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e ->{
            AudioManager.mediaPlayer.stop();
            mainMenu.getWindow().setScene(new TitleScreen(mainMenu).getScene());

        });

        scene.addEventFilter(KeyEvent.ANY, event -> {
            AudioManager.mediaPlayer.stop();
            mainMenu.getWindow().setScene(new TitleScreen(mainMenu).getScene());

        });



        FadeTransition ft = new FadeTransition(Duration.millis(10000), loadingScreen);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);

        ft.play();
        AudioManager.LoadingScreenAudio();




    }



    public Scene getScene() {

        return this.scene;
    }


}
