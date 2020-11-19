package FXMLControlers;

import The.Duck.Game.ButtonInfo;
import The.Duck.Game.GameBoardManager;
import The.Duck.Game.Obstacle;
import The.Duck.Game.Player;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class FirstBoardController {


    private final static String GAME_NAME = "The Shooting Game";

    @FXML
    private Region playerModel;
    @FXML
    private Region Obstacle1;
    @FXML
    private Region Obstacle2;
    @FXML
    private Region Obstacle3;
    @FXML
    private Region Obstacle4;
    @FXML
    private AnchorPane gameBoardPane;

    private Scene gameBoardScene;
    private Stage gameBoardStage;

    private GameBoardManager manager;


    private void setStageOnClose() {
        gameBoardStage.setOnCloseRequest(value -> manager.EndGameBoard());
    }

    private void setSceneOnMouseEvents() {
        gameBoardScene.setOnKeyPressed(value -> {

            if (value.getCode() == KeyCode.D)
                ButtonInfo.setDPressed(true);
            else if (value.getCode() == KeyCode.A)
                ButtonInfo.setAPressed(true);
            else if (value.getCode() == KeyCode.SPACE)
                ButtonInfo.setSpacePressed(true);

        });

        gameBoardScene.setOnKeyReleased(value -> {

            if (value.getCode() == KeyCode.D)
                ButtonInfo.setDPressed(false);
            else if (value.getCode() == KeyCode.A)
                ButtonInfo.setAPressed(false);
            else if (value.getCode() == KeyCode.SPACE)
                ButtonInfo.setSpacePressed(false);

        });

    }

    public void initialize() {
        gameBoardScene = new Scene(gameBoardPane);
        gameBoardStage = new Stage();

        gameBoardStage.setScene(gameBoardScene);
        gameBoardStage.setResizable(false);
        gameBoardStage.setTitle(GAME_NAME);

        setStageOnClose();
        setSceneOnMouseEvents();
    }

    public void setManager(GameBoardManager manager) {
        this.manager = manager;
    }

    public void showGameBoard() {
        gameBoardStage.show();
    }

    public void endGame() {
        gameBoardStage.close();
    }

    public Player getPlayer() {
        return new Player(playerModel);
    }

    public List<Obstacle> getObstaclesList() {

        ArrayList<Obstacle> obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle(Obstacle1, Obstacle1));
        obstacleList.add(new Obstacle(Obstacle2, Obstacle2));
        obstacleList.add(new Obstacle(Obstacle3, Obstacle3));
        obstacleList.add(new Obstacle(Obstacle4, Obstacle4));

        return obstacleList;
    }


}
