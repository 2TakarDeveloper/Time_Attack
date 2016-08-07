package Root.scenes;

import Root.Application.AudioManager;
import Root.Application.Main;
import Root.CustomContol.CustomButton;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Optional;


class PauseMenu {
    private Main mainMenu;
    private GameScene gameScene;
    private CustomButton resumeButton;
    private CustomButton restartButton;
    private CustomButton settingsButton;
    private CustomButton quitButton;
    private Scene scene;

    PauseMenu(Main mainMenu, GameScene gameScene){
        setScene();
        this.mainMenu=mainMenu;
        this.gameScene=gameScene;
        AudioManager.mediaPlayer.stop();



    }

    private void setScene(){
        VBox layout;
        layout = new VBox();
        Text MenuText=new Text("Pause Menu");
        MenuText.setFont(Font.font("Blackadder ITC", FontWeight.BOLD, 60));
        MenuText.setCache(true);
        MenuText.setFill(Color.MAROON);
        MenuText.setTranslateY(MenuText.getTranslateY() - 20);


        resumeButton=new CustomButton("Resume");
        resumeButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            gameScene.resume();
            mainMenu.getWindow().setScene(gameScene.getScene());
            mainMenu.getWindow().setFullScreen(true);
            mainMenu.getWindow ().getScene ().setCursor (Cursor.NONE);

            AudioManager.mediaPlayer.play();
        });

        restartButton=new CustomButton("Restart");
        restartButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Restart Game?");
            alert.setContentText("All progress will be lost");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                GameScene.isPaused=false;

                mainMenu.getWindow().setScene(new GameScene(mainMenu).getScene());
                mainMenu.getWindow().setFullScreen(true);
            }

        });
        settingsButton=new CustomButton("Settings");
        settingsButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            mainMenu.getWindow().setScene(new SettingsScene(mainMenu,this).getScene());
            if(AudioManager.BGM){
                AudioManager.mediaPlayer.play ();
            }
        });




        quitButton=new CustomButton("Quit");
        quitButton.setOnAction(e->{
            AudioManager.buttonAudio();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Quit Game?");
            alert.setContentText("This will take you back to main Menu");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                GameScene.isPaused=false;
                mainMenu.getWindow().setScene(mainMenu.getScene());
                AudioManager.MainMenuAudio();
            }

        });




        layout.getChildren().addAll(MenuText,resumeButton,restartButton,settingsButton,quitButton);
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image ("image/1.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.ROUND, BackgroundPosition.CENTER,backgroundSize);
        Background background = new Background(backgroundImage);
        layout.setBackground (background);
        scene = new Scene(layout, 800, 600);





    }

    public Scene getScene(){
        return this.scene;
    }



}
