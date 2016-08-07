package Root.scenes;


import Root.Application.AudioManager;
import Root.Application.Main;
import Root.CustomContol.CustomButton;
import Root.Settings.Sound;
import Root.gameData.XMLService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.input.KeyCode.ESCAPE;


public class SettingsScene {
   private BorderPane layout;
    private Scene scene;
    private  Slider slider;
    private Button SFX;
    private Button BGM;
    private Sound sound;
    private XMLService xmlService;

    public SettingsScene(Main mainMenu) {
        layout=new BorderPane();

        HBox top=new HBox();
        top.setMinHeight(40);
        Text settingsText=new Text("Settings");
        settingsText.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        settingsText.setCache(true);
        settingsText.setFill(Color.web("#05FFB8"));
        settingsText.setTranslateX(settingsText.getLayoutX()+300);

        top.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        CustomButton backButton=new CustomButton("Back");
        backButton.setOnAction(event ->{
            AudioManager.buttonAudio();
            mainMenu.getWindow().setScene(mainMenu.getScene());
            saveSettings ();
        } );
        top.getChildren().addAll(backButton,settingsText);
        layout.setTop(top);

        VBox left=new VBox(30);
        left.setAlignment(Pos.CENTER);
        left.setMinWidth(75);
        left.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        layout.setLeft(left);

        VBox right=new VBox();
        right.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        right.setFillWidth(true);
        right.setMinWidth(75);
        layout.setRight(right);

        VBox bot=new VBox();
        bot.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        bot.setFillWidth(true);
        bot.setMinHeight(40);
        layout.setBottom(bot);

        //swappable middleLayout

        //audiosettings by default
        AudioSettings();



        scene=new Scene(layout,800,600);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ESCAPE){
                mainMenu.getWindow().setScene(mainMenu.getScene());
                saveSettings ();
            }
        });
    }

    //overloaded constructor to access settings from Pause menu This one is specilized for Pause menu.Update with the other one cautiously
    public SettingsScene(Main mainMenu,PauseMenu gameScene) {
        layout=new BorderPane();

        HBox top=new HBox();
        top.setMinHeight(40);
        Text settingsText=new Text("Settings");
        settingsText.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        settingsText.setCache(true);
        settingsText.setFill(Color.web("#05FFB8"));
        settingsText.setTranslateX(settingsText.getLayoutX()+300);

        top.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        CustomButton backButton=new CustomButton("Back");
        backButton.setOnAction(event ->{
            AudioManager.buttonAudio();
            mainMenu.getWindow().setScene(gameScene.getScene());
            AudioManager.mediaPlayer.stop();
            saveSettings ();
        } );
        top.getChildren().addAll(backButton,settingsText);
        layout.setTop(top);

        VBox left=new VBox(30);
        left.setAlignment(Pos.CENTER);
        left.setMinWidth(75);
        left.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        layout.setLeft(left);

        VBox right=new VBox();
        right.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        right.setFillWidth(true);
        right.setMinWidth(75);
        layout.setRight(right);

        VBox bot=new VBox();
        bot.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        bot.setFillWidth(true);
        bot.setMinHeight(40);
        layout.setBottom(bot);

        //swappable middleLayout

        //audiosettings by default
        AudioSettings();





        scene=new Scene(layout,800,600);
    }




    public Scene getScene() {
        return  scene;
    }

    public void saveSettings(){
        Sound s = new Sound ();
        s.setBGM (AudioManager.BGM);
        s.setSFX (AudioManager.SFX);
        s.setMaxSound(AudioManager.volume);
        XMLService xs=new XMLService ();
        xs.updateSoundInfo (s.getMaxSound (),s.getSFX (),s.getBGM ());
    }

    private void AudioSettings(){

        //load from file
        try{
            XMLService xs=new XMLService ();
            Sound s=xs.info ();
            AudioManager.BGM=s.getBGM ();
            AudioManager.SFX=s.getSFX ();
            AudioManager.volume=s.getMaxSound ();
        }catch (Exception e){e.printStackTrace ();}
        //Controls Scene
        VBox AudioSettings=new VBox(20);
        AudioSettings.setAlignment(Pos.TOP_CENTER);
        AudioSettings.setStyle("-fx-background-color: linear-gradient(#020300, #14b897);");
        Text groupTitleC=new Text("Audio");
        groupTitleC.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        groupTitleC.setCache(true);
        groupTitleC.setFill(Color.BEIGE);


        //slider setup
        HBox maxVolume= new HBox(10);
        maxVolume.setAlignment(Pos.TOP_LEFT);
        Text maxVolText=new Text("Volume");
        maxVolText.setFont(Font.font("Lucida Calligraphy", FontWeight.SEMI_BOLD, 20));
        maxVolText.setCache(true);
        maxVolText.setFill(Color.FUCHSIA);


        slider = new Slider();
        slider.setMin(0);
        slider.setMax(1);
        slider.setValue(AudioManager.volume);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(.1);

        slider.valueProperty().addListener((ov, old_val, new_val) -> {
            AudioManager.volume=slider.getValue();
            AudioManager.updateVolume();
        });


        maxVolume.getChildren().addAll(maxVolText,slider);

        //SFX
        SFX=new Button ("SFX");
        if(AudioManager.SFX){
            SFX.setStyle ("-fx-background-color: #009f8d;");
        }else {
            SFX.setStyle ("-fx-background-color: #db1803;");
        }

        SFX.setOnAction (event -> {
            AudioManager.SFX=!AudioManager.SFX;

            if(AudioManager.SFX){
                SFX.setStyle ("-fx-background-color: #009f8d;");
                AudioManager.mediaPlayer.play ();
                sound.setSFX(true);
                xmlService.info();
            }else {
                SFX.setStyle ("-fx-background-color: #db1803;");
                sound.setSFX(false);
            }
        });

        //BGM
        BGM=new Button ("BGM");
        if(AudioManager.BGM){
            BGM.setStyle ("-fx-background-color: #009f8d;");
        }else {
            BGM.setStyle ("-fx-background-color: #db1803;");
        }

        BGM.setOnAction (event -> {
            AudioManager.BGM=!AudioManager.BGM;

            if(AudioManager.BGM){
                BGM.setStyle ("-fx-background-color: #009f8d;");
                AudioManager.mediaPlayer.play ();
                sound.setBGM(true);
            }else {
                BGM.setStyle ("-fx-background-color: #db1803;");
                AudioManager.mediaPlayer.stop ();
                sound.setBGM(false);
            }
        });

        HBox boolButtons=new HBox (SFX,BGM);
        boolButtons.setSpacing (10);
        boolButtons.setAlignment (Pos.CENTER_LEFT);

        AudioSettings.getChildren().addAll(groupTitleC,maxVolume,boolButtons);

        layout.setCenter(AudioSettings);
    }


}
