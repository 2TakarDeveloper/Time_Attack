package Root.scenes;


import Root.Application.AudioManager;

import Root.GameObjects.PickUps.*;
import Root.CustomContol.CustomLable;
import Root.Application.Main;
import Root.GameObjects.*;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.input.KeyCode.ESCAPE;

public class GameScene implements Runnable {
    public static boolean isPaused = false;
    public static Label playerToolTip= new Label("");//Shows Status with player
    public static FadeTransition ft = new FadeTransition(Duration.millis(1300), playerToolTip);

    private Scene scene;
    private Pane Pane;
    private Player player;
    private Gem gem;
    private GameOverScene gameOverScene;
    private Main mainMenu;
    private CustomLable ScoreLable;
    private CustomLable LevelLable;
    private CustomLable Hp;
    private int level;

    private ArrayList<Pickup> pickups;

    private SpeedUp speedUp;
    private SpeedDown speedDown;
    private Coin coin;
    private Health health;
    private ObjectTimer timer;
    private int levelFlag;
    private HourGlass hourGlass;




    public GameScene(Main mainMenu) {
        AudioManager.GameBGM();


        pickups=new ArrayList<>();

        timer = new ObjectTimer();

        level = 0;

        this.mainMenu = mainMenu;


        Random randomPosition = new Random();

        player = new Player(50, 500, mainMenu.getWindow().getScene ().getWidth ()/25);
        playerToolTip.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));

        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);



        Player.dead = false;


        ScoreLable = new CustomLable("Score",0,Color.PALEVIOLETRED,Font.font("Verdana", FontWeight.BOLD, 20));
        LevelLable=new CustomLable("Level",level,Color.RED,Font.font("Verdana", FontWeight.BOLD, 20));
        Hp=new CustomLable("HP",player.getHealthPoint(),Color.VIOLET,Font.font("Verdana", FontWeight.BOLD, 20));



        gem = new Gem(28 + randomPosition.nextInt(800), 28 + randomPosition.nextInt(600), 12, player, ScoreLable);


        Pane = new Pane(player);
        Pane.getChildren().addAll(ScoreLable,LevelLable,Hp,playerToolTip);
        Pane.getChildren().addAll(gem);




       // Pane.setStyle("-fx-background-color: linear-gradient(from 10% 25% to 100% 50%, #050094 , #0057A7);");

        BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(new Image ("image/3.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.ROUND, BackgroundPosition.CENTER,backgroundSize);
        Background background = new Background(backgroundImage);
        Pane.setBackground (background);

        scene = new Scene(Pane,800,600);
        scene.setCursor(Cursor.NONE);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ESCAPE){
                isPaused = true;
                mainMenu.getWindow().setScene(new PauseMenu(mainMenu,this).getScene());
            }
        });

        //Pickups


        health = new Health(50,50,player,ScoreLable);
        speedDown = new SpeedDown(50,50,player,ScoreLable);
        speedUp = new SpeedUp(50,50,player,ScoreLable);
        coin= new Coin(50,50,player,ScoreLable);
        hourGlass = new HourGlass(50,50,player,ScoreLable);



        Pane.getChildren().addAll(speedUp,health,speedDown,coin,hourGlass);
        pickups.add(speedUp);
        pickups.add(health);
        pickups.add(speedDown);
        pickups.add(coin);
        pickups.add(hourGlass);



        Thread mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void resume() {
        isPaused = false;
        notify();
        player.resume();
        gem.resume();
        Enemy.list.forEach(Enemy::resume);
    }




    public Scene getScene() {
        return this.scene;
    }

    private void checkLevel() {



        if (timer.getTime() % 15 ==0 && levelFlag == 0) {
            level++;
            levelFlag = 1;
            Pickup pickup = pickups.get(new Random().nextInt(pickups.size()));
            pickup.setRandomPosition();
            pickup.setVisible(true);


            LevelLable.setValue(level);


            if(level % 1 == 0){
                Enemy1 enemy = new Enemy1(10, 300, mainMenu.getWindow().getScene ().getWidth ()/40, player, gem);
                Enemy.list.add(enemy);
                Pane.getChildren().addAll(enemy);
                enemy.setSpeed(2);
            }

            if(level % 3 == 0){
                Enemy3 enemy3;
                for(int i=0; i<5 ; i++)
                {
                    if(i<3){
                        enemy3 = new Enemy3(0, 0, mainMenu.getWindow().getScene ().getWidth ()/40, "yellow", player, gem);
                        enemy3.setRelativeY(200+i*150);
                        enemy3.setDirection("right");
                    }
                    else{
                        enemy3 = new Enemy3(scene.getWidth(), 0, mainMenu.getWindow().getScene ().getWidth ()/40, "yellow", player, gem);
                        enemy3.setRelativeY(275+(i-3)*150);
                        enemy3.setDirection("left");
                        enemy3.setScaleX(enemy3.getScaleX()*-1);

                    }
                    Enemy.list.add(enemy3);
                    Pane.getChildren().addAll(enemy3);
                    enemy3.setSpeed(2);
                }

            }

            if(level == 3){

                Enemy enemy = new Enemy2(1024, 0, mainMenu.getWindow().getScene ().getWidth ()/40, player, gem);
                enemy.setSpeed(2);
                Enemy.list.add(enemy);
                Pane.getChildren().addAll(enemy);
            }

            for(Enemy enemy:Enemy.list){
                enemy.setSpeed(enemy.getSpeed()+.2);
            }

        }
        else if(timer.getTime() % 25 == 1){
            levelFlag = 0;
        }
    }

    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {
                //do anything
                if(!isPaused){
                    checkLevel();
                    ScoreLable.setText(ScoreLable.getTextAsString());
                    LevelLable.setText(LevelLable.getTextAsString());
                    LevelLable.setLayoutX(mainMenu.getWindow().getWidth()/2-20);
                    Hp.setLayoutX(mainMenu.getWindow().getWidth()-110);
                    Hp.setValue(player.getHealthPoint());
                    Hp.setText(Hp.getTextAsString());



                }


            });


            try {
                Thread.sleep(1);
            }  catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        //runs when player is dead
        Platform.runLater(() ->
        {
            AudioManager.mediaPlayer.stop();
            mainMenu.getWindow().setResizable(false);
            gameOverScene = new GameOverScene(mainMenu, ScoreLable, level);
            mainMenu.getWindow().setScene(gameOverScene.getScene());
        });
    }
}

