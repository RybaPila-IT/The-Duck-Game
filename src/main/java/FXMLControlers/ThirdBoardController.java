package FXMLControlers;

import The.Duck.Game.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThirdBoardController extends BoardController {

    @FXML
    private Region Obstacle2;
    @FXML
    private Region Obstacle3;
    @FXML
    private Region Obstacle4;
    @FXML
    private Region Obstacle21;
    @FXML
    private Region Obstacle22;
    @FXML
    private Region Obstacle31;
    @FXML
    private Region Obstacle32;
    @FXML
    private Region Obstacle33;
    @FXML
    private Region Obstacle331;
    @FXML
    private Region PlayerModel1;
    @FXML
    private Region PlayerModel2;
    @FXML
    private Region Weapon1;
    @FXML
    private Region Weapon11;
    @FXML
    private Region Doors1;
    @FXML
    private Region Doors2;
    @FXML
    private Region Doors21;
    @FXML
    private Region player1_heart1;
    @FXML
    private Region player1_heart2;
    @FXML
    private Region player1_heart3;
    @FXML
    private Region player2_heart1;
    @FXML
    private Region player2_heart2;
    @FXML
    private Region player2_heart3;


    @FXML
    private AnchorPane GameBoardPane;

    @FXML
    public void initialize() {
        makeInit(GameBoardPane);
    }

    public Player getFirstPlayer() {
        return new Player(PlayerModel1, BoardConstants.getPlayer1StyleClass(),
                Arrays.asList(player1_heart1, player1_heart2, player1_heart3));
    }

    @Override
    public Player getSecondPlayer() {
        return new Player(PlayerModel2, BoardConstants.getPlayer2StyleClass(),
                Arrays.asList(player2_heart1, player2_heart2, player2_heart3));
    }

    public List<BoardObject> getBoardObjectsList() {

        return new ArrayList<>(
                Arrays.asList(new Obstacle(Obstacle2), new Obstacle(Obstacle3), new Obstacle(Obstacle331),
                        new Obstacle(Obstacle21), new Obstacle(Obstacle22), new Obstacle(Obstacle32),
                        new Obstacle(Obstacle33), new Obstacle(Obstacle4), new Obstacle(Obstacle31),
                        new Door(Doors1), new Door(Doors2), new Door(Doors21), new Weapon(Weapon1),
                        new Weapon(Weapon11))
        );
    }

    @Override
    public void addToScene(Node node) {
        GameBoardPane.getChildren().add(node);
    }

    @Override
    public void removeNodeFromScene(Node node) {
        GameBoardPane.getChildren().remove(node);
    }

    @Override
    public double[] getSpawnPointsForWeapons() {
        return new double[]{Weapon1.getLayoutX(), Weapon1.getLayoutY(),
                Weapon11.getLayoutX(), Weapon11.getLayoutY()};
    }
}
