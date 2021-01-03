package FXMLControlers;

import The.Duck.Game.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.List;

abstract public class BoardController {

    private final static String GAME_NAME = "The Shooting Game";

    private Scene gameBoardScene;
    private Stage gameBoardStage;

    private GameBoardManager manager;


    private void setStageOnClose() {
        gameBoardStage.setOnCloseRequest(value -> manager.endGame());
    }

    private void setSceneOnMouseEvents() {

        gameBoardScene.setOnKeyPressed(value -> {

            if (value.getCode() == KeyCode.D)
                BoardConstants.getPlayer1Info().setPressedRight(true);
            else if (value.getCode() == KeyCode.A)
                BoardConstants.getPlayer1Info().setPressedLeft(true);
            else if (value.getCode() == KeyCode.W)
                BoardConstants.getPlayer1Info().setPressedUp(true);
            else if (value.getCode() == KeyCode.V)
                BoardConstants.getPlayer1Info().setPressedGrab(true);
            else if (value.getCode() == KeyCode.B)
                BoardConstants.getPlayer1Info().setPressedShoot(true);
            else if (value.getCode() == KeyCode.UP)
                BoardConstants.getPlayer2Info().setPressedUp(true);
            else if (value.getCode() == KeyCode.LEFT)
                BoardConstants.getPlayer2Info().setPressedLeft(true);
            else if (value.getCode() == KeyCode.RIGHT)
                BoardConstants.getPlayer2Info().setPressedRight(true);
            else if (value.getCode() == KeyCode.NUMPAD1)
                BoardConstants.getPlayer2Info().setPressedGrab(true);
            else if (value.getCode() == KeyCode.NUMPAD3)
                BoardConstants.getPlayer2Info().setPressedShoot(true);
        });

        gameBoardScene.setOnKeyReleased(value -> {

            if (value.getCode() == KeyCode.D)
                BoardConstants.getPlayer1Info().setPressedRight(false);
            else if (value.getCode() == KeyCode.A)
                BoardConstants.getPlayer1Info().setPressedLeft(false);
            else if (value.getCode() == KeyCode.W)
                BoardConstants.getPlayer1Info().setPressedUp(false);
            else if (value.getCode() == KeyCode.V)
                BoardConstants.getPlayer1Info().setPressedGrab(false);
            else if (value.getCode() == KeyCode.B)
                BoardConstants.getPlayer1Info().setPressedShoot(false);
            else if (value.getCode() == KeyCode.UP)
                BoardConstants.getPlayer2Info().setPressedUp(false);
            else if (value.getCode() == KeyCode.LEFT)
                BoardConstants.getPlayer2Info().setPressedLeft(false);
            else if (value.getCode() == KeyCode.RIGHT)
                BoardConstants.getPlayer2Info().setPressedRight(false);
            else if (value.getCode() == KeyCode.NUMPAD1)
                BoardConstants.getPlayer2Info().setPressedGrab(false);
            else if (value.getCode() == KeyCode.NUMPAD3)
                BoardConstants.getPlayer2Info().setPressedShoot(false);
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

    abstract public Player getFirstPlayer();

    abstract public Player getSecondPlayer();

    abstract public List<BoardObject> getObstaclesList();

    abstract public void addToScene(Node node);

    abstract public void removeNodeFromScene(Node node);

}
