package FXMLControlers;

import The.Duck.Game.*;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public class FirstBoardController extends BoardController {

    @FXML
    private Region playerModel1;
    @FXML
    private Region playerModel2;
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

    @FXML
    public void initialize() {
        makeInit(gameBoardPane);
    }

    public Player getFirstPlayer() {
        return new Player(playerModel1, true);
    }

    public Player getSecondPlayer() {
        return new Player(playerModel2, false);
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
