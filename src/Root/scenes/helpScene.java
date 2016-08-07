package Root.scenes;


import Root.Application.AudioManager;
import Root.CustomContol.CustomButton;
import Root.Application.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.input.KeyCode.ESCAPE;


public class helpScene {
   private BorderPane layout;
    private Scene scene;
    private CustomButton pickUpsButton =new CustomButton("Power Up");

    private CustomButton enemyButton=new CustomButton("Enemies");
    private CustomButton objectiveButton=new CustomButton("Objective");

    public helpScene(Main mainMenu) {
        layout=new BorderPane();

        HBox top=new HBox();
        top.setMinHeight(40);
        top.setStyle("-fx-background-color: linear-gradient(#780bde, #062f42);");
        CustomButton backButton=new CustomButton("Back");
        backButton.setOnAction(event ->{
            AudioManager.buttonAudio();
            mainMenu.getWindow().setScene(mainMenu.getScene());
        } );
        top.getChildren().addAll(backButton);



        layout.setTop(top);

        VBox left=new VBox(30);
        left.setAlignment(Pos.CENTER);
        left.getChildren().addAll(pickUpsButton,enemyButton,objectiveButton);
        left.setStyle("-fx-background-color: linear-gradient(#780bde, #062f42);");
        layout.setLeft(left);

        VBox right=new VBox();
        right.setStyle("-fx-background-color: linear-gradient(#780bde, #062f42);");
        right.setFillWidth(true);
        right.setMinWidth(75);
        layout.setRight(right);

        VBox bot=new VBox();
        bot.setStyle("-fx-background-color: linear-gradient(#780bde, #062f42);");
        bot.setFillWidth(true);
        bot.setMinHeight(40);
        layout.setBottom(bot);

        //swappable middleLayout


        //set ButtonAction
        enemyButton.setOnAction(e->setEnemyScene());

        pickUpsButton.setOnAction(e-> setPowerupScene());

        objectiveButton.setOnAction(e->setObjectiveScene());











        //defaultbuttonScene
         setEnemyScene();




        scene=new Scene(layout,800,600);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ESCAPE){
                mainMenu.getWindow().setScene(mainMenu.getScene());
            }
        });
    }



    public Scene getScene() {
        return  scene;
    }

    private void setEnemyScene(){
        //enemyButton scene
        VBox enemyGroup=new VBox();
        enemyGroup.setStyle("-fx-background-color: linear-gradient(#020300, #14b897);");
        Text groupTitle=new Text("Enemies");
        groupTitle.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        groupTitle.setCache(true);
        groupTitle.setFill(Color.web("#05FFB8"));
        groupTitle.setTranslateX(groupTitle.getLayoutX()+275);

        //enemy1info
        ImageView enemy1Image=new ImageView(new Image("image/Enemy1.gif"));
        enemy1Image.setFitWidth(50);
        enemy1Image.setFitHeight(50);
        Text enemy1Text=new Text("They will bounce off walls and the candies.");
        enemy1Text.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        enemy1Text.setFill(Color.web("#FF054C"));
        enemy1Text.setTranslateX(enemy1Text.getLayoutX()+60);
        enemy1Text.setTranslateY(enemy1Text.getLayoutY()-20);
        //enemy2Info
        ImageView enemy2Image=new ImageView(new Image("image/Ghost.gif"));
        enemy2Image.setFitHeight(50);
        enemy2Image.setFitWidth(50);
        Text enemy2Text=new Text("HE will chase you to death.");
        enemy2Text.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        enemy2Text.setFill(Color.web("#FF054C"));
        enemy2Text.setTranslateX(enemy1Text.getLayoutX()+60);
        enemy2Text.setTranslateY(enemy1Text.getLayoutY()-20);

        //enemy3Info
        ImageView enemy3Image=new ImageView(new Image("image/Bat.gif"));
        enemy3Image.setFitHeight(50);
        enemy3Image.setFitWidth(50);
        Text enemy3Text=new Text("They Come in Groups to kill you.Watch Out!");
        enemy3Text.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        enemy3Text.setFill(Color.web("#FF054C"));
        enemy3Text.setTranslateX(enemy1Text.getLayoutX()+60);
        enemy3Text.setTranslateY(enemy1Text.getLayoutY()-20);


        //setupEnemiesLayout
        enemyGroup.getChildren().addAll(groupTitle,enemy1Image,enemy1Text,enemy2Image,enemy2Text,enemy3Image,enemy3Text);
        layout.setCenter(enemyGroup);
    }

    private void setPowerupScene(){
        //Controls Scene
        //enemyButton scene
        VBox powerUpGroup=new VBox();
        powerUpGroup.setStyle("-fx-background-color: linear-gradient(#020300, #14b897);");
        Text groupTitle=new Text("PowerUp's");
        groupTitle.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        groupTitle.setCache(true);
        groupTitle.setFill(Color.web("#05FFB8"));
        groupTitle.setTranslateX(groupTitle.getLayoutX()+275);

        //HPinfo
        ImageView HPImage=new ImageView(new Image("image/HP.gif"));
        HPImage.setFitHeight(25);
        HPImage.setFitWidth(25);

        Text HPText=new Text("Obviously it gives you Bonus Health");
        HPText.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        HPText.setFill(Color.WHITE);
        HPText.setTranslateX(HPText.getLayoutX()+30);
        HPText.setTranslateY(HPText.getLayoutY()-20);
        //SpeedUP
        ImageView SpeedUpImage=new ImageView(new Image("image/Speedup.gif"));
        SpeedUpImage.setFitHeight(25);
        SpeedUpImage.setFitWidth(25);
        Text SpeedUpText=new Text("Enemeis get Bonus speed." +
                ".Player gains some Score");
        SpeedUpText.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        SpeedUpText.setFill(Color.WHITE);
        SpeedUpText.setTranslateX(SpeedUpText.getLayoutX()+32);
        SpeedUpText.setTranslateY(SpeedUpText.getLayoutY()-20);

        //SpeedDown
        ImageView SpeedDownImage=new ImageView(new Image("image/SpeedDown.gif"));
        SpeedDownImage.setFitHeight(25);
        SpeedDownImage.setFitWidth(25);
        Text SpeedDownText=new Text("Enemeis Lose Bonus speed" +
                ".Player Loses some Score");
        SpeedDownText.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        SpeedDownText.setFill(Color.WHITE);
        SpeedDownText.setTranslateX(SpeedDownText.getLayoutX()+32);
        SpeedDownText.setTranslateY(SpeedDownText.getLayoutY()-20);


        //Timer
        ImageView hourGlass=new ImageView(new Image("image/HourGlass.gif"));
        hourGlass.setFitHeight(25);
        hourGlass.setFitWidth(25);
        Text hourGlassText=new Text("Enemeis Are Frozen for Brief Time" +
                ".Player Loses Some Score.");
        hourGlassText.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        hourGlassText.setFill(Color.WHITE);
        hourGlassText.setTranslateX(SpeedDownText.getLayoutX()+32);
        hourGlassText.setTranslateY(SpeedDownText.getLayoutY()-20);

        //Coin
        ImageView coin=new ImageView(new Image("image/Coin.gif"));
        coin.setFitHeight(25);
        coin.setFitWidth(25);
        Text coinText=new Text("Gives Bonus Score");
        coinText.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        coinText.setFill(Color.WHITE);
        coinText.setTranslateX(SpeedDownText.getLayoutX()+32);
        coinText.setTranslateY(SpeedDownText.getLayoutY()-20);



        //setupPowerUpsLayout
        powerUpGroup.getChildren().addAll(groupTitle
                ,HPImage,HPText,
                SpeedUpImage, SpeedUpText,
                SpeedDownImage,SpeedDownText,
                hourGlass,hourGlassText,
                coin,coinText
        );


        layout.setCenter(powerUpGroup);
    }


    private void setObjectiveScene(){
        //Controls Scene
        VBox ObjectiveScene=new VBox();
        ObjectiveScene.setAlignment(Pos.TOP_CENTER);
        ObjectiveScene.setStyle("-fx-background-color: linear-gradient(#020300, #14b897);");
        Text groupTitleC=new Text("Objective");
        groupTitleC.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        groupTitleC.setCache(true);
        groupTitleC.setFill(Color.web("#05FFB8"));

        Text objectiveText=new Text("Your a Gem collector." +
                "\nWent in a abandoned mine to collect gems." +
                "\nSeems like Your not alone here." +
                "\nCollect as much gems as you can." +
                "\nGood Luck." );
        objectiveText.setFill(Color.web("#99ccff"));
        objectiveText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 30));




        ObjectiveScene.getChildren().addAll(groupTitleC,objectiveText);
        layout.setCenter(ObjectiveScene);
    }




}
