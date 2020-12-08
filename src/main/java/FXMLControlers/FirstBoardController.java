package FXMLControlers;

import The.Duck.Game.*;
import javafx.fxml.FXML;

import javafx.scene.Node;
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
    @FXML
    private Region weapon1;
    @FXML
    private Region weapon2;
    @FXML
    private Region jumper1;
    @FXML
    private Region jumper2;

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

    public void initialize() {
        gameBoardScene = new Scene(gameBoardPane);
        gameBoardStage = new Stage();

        gameBoardStage.setScene(gameBoardScene);
        gameBoardStage.setResizable(false);
        gameBoardStage.setTitle(GAME_NAME);

        setStageOnClose();
        setSceneOnMouseEvents();

        BoardConstants.setBoardWidth(gameBoardPane.getPrefWidth());
        BoardConstants.setBoardHeight(gameBoardPane.getPrefHeight());
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

    public Player getPlayer() {
        return new Player(playerModel);
    }

    public List<Weapon> getWeaponList() {

        ArrayList<Weapon> weaponList = new ArrayList<>();
        weaponList.add(new Weapon(weapon1));

        return weaponList;
    }


    public List<BoardObject> getObstaclesList() {

        ArrayList<BoardObject> obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle(Obstacle1));
        obstacleList.add(new Obstacle(Obstacle2));
        obstacleList.add(new Obstacle(Obstacle3));
        obstacleList.add(new Obstacle(Obstacle4));
        obstacleList.add(new Weapon(weapon1));
        obstacleList.add(new Weapon(weapon2));
        obstacleList.add(new Jumper(jumper1));
        obstacleList.add(new Jumper(jumper2));

        return obstacleList;
    }

    public void addToScene(Node node) {
        gameBoardPane.getChildren().add(node);
    }

    public void removeNodeFromScene(Node node) {
        gameBoardPane.getChildren().remove(node);
    }


}
