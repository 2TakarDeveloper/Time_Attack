package Root.scenes;

import Root.Application.AudioManager;
import Root.Application.Main;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

class TitleScreen {
    public Scene scene;

    Main mainMenu;
    private Text prompt;
    private  Text GameName;


    private Image titleImage= new Image("image/TitleImage.png");

    TitleScreen(Main mainMenu) {
        AudioManager.TitleScreenAudio();


        GameName= new Text("Time Attack");
        GameName.setFont(Font.font("Gigi", FontWeight.EXTRA_BOLD, 120));
        GameName.setCache(true);
        GameName.setFill(Color.TAN);
        GameName.setTranslateY(GameName.getTranslateY()-50);

        prompt= new Text("Press Any Key");
        prompt.setFont(Font.font("Blackadder ITC", FontWeight.BOLD, 60));
        prompt.setCache(true);
        prompt.setFill(Color.WHITE);
        prompt.setTranslateY(prompt.getTranslateY()+100);

        this.mainMenu=mainMenu;

        setScene(mainMenu);

    }

    public void setScene(Main mainMenu) {
        BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(titleImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.ROUND, BackgroundPosition.CENTER,backgroundSize);
        Background background = new Background(backgroundImage);

        VBox layout = new VBox(20,GameName,prompt);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background);
        scene = new Scene(layout, 800, 600);


        FadeTransition ft = new FadeTransition(Duration.millis(500), prompt);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);

        ft.play();



        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            Stop ();
            ft.stop();
        });

        scene.addEventFilter(KeyEvent.ANY, event -> {
            Stop ();
            ft.stop();
        });
    }

    public void Stop(){
        AudioManager.mediaPlayer.stop();
        AudioManager.TitleKeyPress();
        mainMenu.getWindow().setScene(mainMenu.getScene());
        AudioManager.MainMenuAudio();
    }

    public Scene getScene() {

        return this.scene;
    }





}
