package Root.scenes;


import Root.Application.AudioManager;
import Root.Application.Main;
import Root.CustomContol.ScoreBoard;
import Root.CustomContol.CustomButton;
import Root.gameData.XMLService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Optional;

import static javafx.scene.input.KeyCode.ESCAPE;


public class HighScoreScene {

    private Scene scene;
    private VBox layout;
    private Text text;
    private CustomButton backButton;
    private CustomButton clearButton;
    private TableView<ScoreBoard> highScoreBoard;
    private Main mainMenu;
    private  XMLService x;
    public HighScoreScene(Main mainMenu) {
        this.mainMenu=mainMenu;
        x= new XMLService();

        highScoreBoard = new TableView<>();
        setTable();

        text = new Text("High Score");
        text.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        text.setCache(true);
        text.setFill(Color.YELLOWGREEN);
        //text.setTranslateX(mainMenu.getScene().getWindow().getWidth()/2);

        layout = new VBox();
        layout.setStyle("-fx-background-color: #2F4F4F;");

        //button with action to return to scene
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            mainMenu.getWindow().setScene(mainMenu.getScene());
        });
        clearButton= new CustomButton("Clear");

        clearButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Are you sure you wish to clear the Table?");
            alert.setContentText("You can't Undo this process");


            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                x.clearFile();
                mainMenu.getWindow().setScene(new HighScoreScene(mainMenu).getScene());

            }

        });


    }

    private void setScene() {
        BorderPane hBox = new BorderPane();
        hBox.setLeft(backButton);
        hBox.setCenter(text);
        hBox.setRight(clearButton);

        hBox.setStyle("-fx-background-color: #2F4F4F;");



        layout.getChildren().addAll(hBox, highScoreBoard);

        scene = new Scene(layout, 800, 600);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ESCAPE){
                mainMenu.getWindow().setScene(mainMenu.getScene());
            }
        });

        clearButton.setAlignment(Pos.CENTER_RIGHT);
        text.setTextAlignment(TextAlignment.CENTER);
    }

    public Scene getScene() {
        updateTable();
        setScene();
        return this.scene;
    }


    private void setTable() {
        //Takes an scoreBoardObject
        //columns
        //title

        highScoreBoard.setStyle("--fx-base: #dbd94b;\n" +
                "    -fx-control-inner-background: #2f5a94;\n" +
                "    -fx-background-color: #000000;\n" +
                "    -fx-table-cell-border-color: transparent;\n" +
                "    -fx-table-header-border-color: transparent;\n" +
                "    -fx-padding: 5;" +
                "    -fx-column-header-background-color:transparent");
        highScoreBoard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        TableColumn<ScoreBoard, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-alignment:CENTER;");


        //Value
        TableColumn<ScoreBoard, ScoreBoard> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setStyle("-fx-alignment:CENTER;");


        //comment
        TableColumn<ScoreBoard, String> levelColumn = new TableColumn<>("Max Level Reached");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("lvlReached"));
        levelColumn.setStyle("-fx-alignment:CENTER;");


        //addColumns on table
        highScoreBoard.getColumns().add(nameColumn);
        highScoreBoard.getColumns().add(scoreColumn);
        highScoreBoard.getColumns().add(levelColumn);


        highScoreBoard.setMinHeight(mainMenu.getWindow().getHeight());

    }



    private void updateTable() {
        //fetch data from Root.database and get and array and update like this code from a loop.
        //1 row = 1 scoreboard object get and arraylist from the Root.database and create objects from those.
        try {
            ArrayList<ScoreBoard> sb = x.getScoreList();
                for (ScoreBoard scoreBoard : sb) {
                    highScoreBoard.getItems().addAll(scoreBoard);
                }

            highScoreBoard.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
