package FXMLControlers;

import The.Duck.Game.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public class SecondBoardController extends BoardController {

    @FXML
    private Region PlayerModel1;
    @FXML
    private Region PlayerModel2;
    @FXML
    private Region Obstacle1;
    @FXML
    private Region Obstacle2;
    @FXML
    private Region Obstacle3;
    @FXML
    private Region Obstacle4;
    @FXML
    private Region Obstacle5;
    @FXML
    private Region Portal1_L;
    @FXML
    private Region Portal1_R;

    @FXML
    private AnchorPane GameBoardPane;

    @FXML
    public void initialize() {
        makeInit(GameBoardPane);
    }

    public Player getFirstPlayer() {
        return new Player(PlayerModel1, true);
    }

    @Override
    public Player getSecondPlayer() {
        return new Player(PlayerModel2, false);
    }


    public List<BoardObject> getObstaclesList() {

        ArrayList<BoardObject> obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle(Obstacle1));
        obstacleList.add(new Obstacle(Obstacle2));
        obstacleList.add(new Obstacle(Obstacle3));
        obstacleList.add(new Obstacle(Obstacle4));
        obstacleList.add(new Obstacle(Obstacle5));

        obstacleList.add(new Portal(Portal1_R, Portal1_L.getLayoutX() - 35, Portal1_L.getLayoutY() + 15, false));
        obstacleList.add(new Portal(Portal1_L, Portal1_R.getLayoutX() + 65, Portal1_R.getLayoutY() + 15, true));

        return obstacleList;
    }

    public void addToScene(Node node) {
        GameBoardPane.getChildren().add(node);
    }

    public void removeNodeFromScene(Node node) {
        GameBoardPane.getChildren().remove(node);
    }

}
