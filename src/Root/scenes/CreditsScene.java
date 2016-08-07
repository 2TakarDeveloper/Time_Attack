package Root.scenes;

import Root.Application.AudioManager;
import Root.Application.Main;
import Root.GameObjects.PickUps.ObjectTimer;
import Root.CustomContol.CustomButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.input.KeyCode.ESCAPE;

public class CreditsScene{
    Scene scene;
    BorderPane layout;
    ObjectTimer timer;
    public CreditsScene(Main mainMenu){
        timer=new ObjectTimer();

        layout=new BorderPane();

        HBox top=new HBox();
        top.setMinHeight(40);
        Text settingsText=new Text("Credits");
        settingsText.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        settingsText.setCache(true);
        settingsText.setFill(Color.web("#05FFB8"));
        settingsText.setTranslateX(settingsText.getLayoutX()+300);

        top.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        CustomButton backButton=new CustomButton("Back");
        backButton.setOnAction(event ->{
            AudioManager.buttonAudio();
            mainMenu.getWindow().setScene(mainMenu.getScene());
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

        //Text part
        Text text=new Text ();
        text.setText ("Project Manager:Tanimul Haque Khan" +
                "\nDeveloper:Shovon,Bashak" +
                "\nResource Manager:Mahmudul Islam saky" +
                "\nSpecial Thanks: Shovra,Das");
        text.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        text.setCache(true);
        text.setFill(Color.WHITE);



        VBox vbox = new VBox(text);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color:#000000");












        layout.setCenter(vbox);
        scene = new Scene(layout, 800,600);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ESCAPE){
                mainMenu.getWindow().setScene(mainMenu.getScene());
            }
        });



    }

    public Scene getScene(){
        return this.scene;
    }





}
