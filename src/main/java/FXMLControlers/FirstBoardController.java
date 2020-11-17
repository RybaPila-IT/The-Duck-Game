package FXMLControlers;

import The.Duck.Game.Obstacle;
import javafx.fxml.FXML;

import javafx.scene.layout.Region;

import java.util.ArrayList;

public class FirstBoardController {

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


    public void initialize() {

    }

    public Region getPlayer() {
        return playerModel;
    }

    public ArrayList<Obstacle> getObstaclesList() {

        ArrayList<Obstacle> obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle(Obstacle1));
        obstacleList.add(new Obstacle(Obstacle2));
        obstacleList.add(new Obstacle(Obstacle3));
        obstacleList.add(new Obstacle(Obstacle4));

        return obstacleList;
    }


}
