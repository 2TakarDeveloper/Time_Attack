/**
How this works?
 This is The main Menu.It handles other game Root.scenes.Other Root.scenes transit into and out of this.
 Main Function creates an instance of main menu and it's components.
 Whenever scene transition happens this object is passed so that this scene can be called back.
 */

package Root.Application;

import Root.CustomContol.CustomButton;
import Root.scenes.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;


public class Main extends Application {
    //Class variables

    private Stage window;
    private Scene scene;
    private VBox layout;
    private CustomButton startGame;
    private CustomButton exit;
    private CustomButton highScore;
    private CustomButton helpButton;
    private CustomButton SettingsButton;
    private CustomButton  creditsButton;
    private Text gameName;



    //Root.scenes


    public Stage getWindow() {
        return window;
    }

    public Scene getScene() {
        return scene;
    }

    public Main() {
        //initialize buttons

        startGame = new CustomButton("Start Game");
        startGame.setOnAction(event1 -> {
            AudioManager.buttonAudio();
            AudioManager.mediaPlayer.stop();
            window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            window.setScene(new GameScene(this).getScene());
            window.setFullScreen(true);
        });


        exit = new CustomButton("Exit");
        exit.setOnAction(event -> {
            AudioManager.buttonAudio();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT!");
            alert.setHeaderText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platform.exit();
                System.exit(0);
            }

        });

        helpButton = new CustomButton("Help");
        helpButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            window.setScene(new helpScene(this).getScene());
        });

        SettingsButton= new CustomButton("Settings");
        SettingsButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            window.setScene(new SettingsScene(this).getScene());
        });




        highScore = new CustomButton("High Score");
        highScore.setOnAction(event ->{
            AudioManager.buttonAudio();
            window.setScene(new HighScoreScene(this).getScene());
        } );


        creditsButton=new CustomButton("Credits");
        creditsButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            window.setScene(new CreditsScene(this).getScene());
        });

        gameName = new Text("Time Attack");
        gameName.setFont(Font.font("Blackadder ITC", FontWeight.BOLD, 60));
        gameName.setCache(true);
        gameName.setFill(Color.LEMONCHIFFON);
        gameName.setTranslateY(gameName.getTranslateY() - 20);
    }
    public void start(Stage window) throws Exception {
        this.window = window;
        window.setScene(new LoadingScreen(this).getScene());



        //setup MainMenu
        layout = new VBox(20, gameName, startGame, highScore,SettingsButton, helpButton,creditsButton, exit);

        layout.setAlignment(Pos.CENTER);
        BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image ("image/MainMenu.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.ROUND, BackgroundPosition.CENTER,backgroundSize);
        Background background = new Background(backgroundImage);
        layout.setBackground (background);
        scene = new Scene(layout, 800, 600);


        window.setTitle("Time Attack");
        window.setResizable(false);
        window.show();

        window.setOnCloseRequest(event -> {
                    event.consume();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("ALERT!");
                    alert.setHeaderText("Are you sure?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        Platform.exit();
                        System.exit(0);
                    }


                }
        );
    }




    public static void main(String[] args) {
        launch(args);
    }

}
