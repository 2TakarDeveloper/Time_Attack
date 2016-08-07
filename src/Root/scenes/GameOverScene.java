package Root.scenes;


import Root.Application.AudioManager;
import Root.CustomContol.CustomButton;
import Root.CustomContol.CustomLable;
import Root.CustomContol.ScoreBoard;
import Root.Application.Main;
import Root.gameData.XMLService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


class GameOverScene {
    public Scene scene;
    private CustomButton Next;
    private CustomLable scoreLable;
    private TextField NameField;


    private Text gameOver;
    private Text Score;
    private Text nameLable;
    private Main mainMenu;
    private int levelReached;
    private MediaPlayer mediaPlayer;

    GameOverScene(Main mainMenu, CustomLable scoreLable, int levelReached) {
        AudioManager.gameOverMusic();
        this.scoreLable = scoreLable;
        this.mainMenu=mainMenu;
        this.levelReached=levelReached;



        Next = new CustomButton("Next");
        Next.setOnAction(event1 -> query(mainMenu,levelReached));

        NameField = new TextField();

        NameField.setMaxSize(200, 100);
        NameField.setFont(Font.font("Informal Roman", FontWeight.BOLD, 30));
        NameField.setStyle("-fx-background-color: #CBC6AF;");


        nameLable = new Text("ENTER YOUR NAME");
        nameLable.setFont(Font.font("Informal Roman", FontWeight.BOLD, 30));
        nameLable.setCache(true);
        nameLable.setFill(Color.web("#FF915B"));


        gameOver = new Text("Game Over");
        gameOver.setFont(Font.font("Chiller", FontWeight.BOLD, 60));
        gameOver.setCache(true);
        gameOver.setFill(Color.RED);
        gameOver.setTranslateY(gameOver.getTranslateY() - 20);


        Score = new Text("Score:" + String.valueOf(scoreLable.getValue()));
        Score.setFont(Font.font("Harrington", FontWeight.BOLD, 50));
        Score.setCache(true);
        Score.setFill(Color.web("#C8A780"));
        Score.setTranslateY(Score.getTranslateY() - 20);

        setScene();
    }

    private void setScene() {

        VBox layout = new VBox(20, gameOver, Score, nameLable, NameField, Next);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 800, 600);
        layout.setStyle("-fx-background-color: #000000;");

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                event.consume();
                query(mainMenu,levelReached);
            }
        });


    }




    public Scene getScene() {
        return this.scene;
    }


    /*private void updateDatabase(ScoreBoard sb) {
        //send queries from the data of the given scoreboard object.
        try {
            DBService ds = new DBService();
            ds.updateScoreBoard(sb.getName(), sb.getValue(), sb.getLvlReached());
            ds.dbCon.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    private void updateData(ScoreBoard sb) {
        //send queries from the data of the given scoreboard object.
        try {
            XMLService xs = new XMLService();
            xs.updateScoreBoard(sb.getName(), sb.getScore(), sb.getLvlReached());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void query(Main mainMenu, int levelReached){
        ScoreBoard SB;
        String name = NameField.getText();
        if (!name.isEmpty()) {
            SB = new ScoreBoard(name, String.valueOf(scoreLable.getValue()), String.valueOf(levelReached));
        } else {
            SB = new ScoreBoard("NameLessWonder", String.valueOf(scoreLable.getValue()), String.valueOf(levelReached));
        }

        updateData(SB);

        mainMenu.getWindow().setScene(new HighScoreScene(mainMenu).getScene());
        AudioManager.MainMenuAudio();
    }



}
