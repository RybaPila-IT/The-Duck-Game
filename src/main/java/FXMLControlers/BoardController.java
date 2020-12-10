package FXMLControlers;

import The.Duck.Game.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

abstract public class BoardController {

    private final static String GAME_NAME = "The Shooting Game";

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
            else if (value.getCode() == KeyCode.G)
                ButtonInfo.setGPressed(true);
            else if (value.getCode() == KeyCode.K)
                ButtonInfo.setKPressed(true);

        });

        gameBoardScene.setOnKeyReleased(value -> {

            if (value.getCode() == KeyCode.D)
                ButtonInfo.setDPressed(false);
            else if (value.getCode() == KeyCode.A)
                ButtonInfo.setAPressed(false);
            else if (value.getCode() == KeyCode.SPACE)
                ButtonInfo.setSpacePressed(false);
            else if (value.getCode() == KeyCode.G)
                ButtonInfo.setGPressed(false);
            else if (value.getCode() == KeyCode.K)
                ButtonInfo.setKPressed(false);

        });
    }

    protected void makeInit(AnchorPane pane) {

        gameBoardScene = new Scene(pane);
        gameBoardStage = new Stage();

        gameBoardStage.setScene(gameBoardScene);
        gameBoardStage.setResizable(false);
        gameBoardStage.setTitle(GAME_NAME);

        setStageOnClose();
        setSceneOnMouseEvents();

        BoardConstants.setBoardWidth(pane.getPrefWidth());
        BoardConstants.setBoardHeight(pane.getPrefHeight());
        BoardConstants.setController(this);
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

    abstract public Player getPlayer();

    abstract public List<BoardObject> getObstaclesList();

    abstract public void addToScene(Node node);

    abstract public void removeNodeFromScene(Node node);

}
