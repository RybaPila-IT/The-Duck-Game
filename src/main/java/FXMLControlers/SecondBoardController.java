package FXMLControlers;

import The.Duck.Game.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Arrays;
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
    private Region weapon1;
    @FXML
    private Region weapon2;
    @FXML
    private AnchorPane GameBoardPane;
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

    private static final List<List<Boolean>> KEY_COMBINATIONS;
    private static final List<Integer> TIMES_OF_COMBINATIONS;

    static {

        KEY_COMBINATIONS = Arrays.asList(
                // Right, Left, Up, Shoot, Grab, Interact
                Arrays.asList(false, true, false, false, false, false), // Left click at the beginning.
                Arrays.asList(true, false, false, false, false, false), // Right click from portal.
                Arrays.asList(false, true, true, false, true, false),  // Grab weapon turn left and jump.
                Arrays.asList(false, false, false, true, false, false), // Shoot 1.
                Arrays.asList(false, false, false, true, false, false), // Shoot 2.
                Arrays.asList(false, false, false, true, false, false), // Shoot 3.
                Arrays.asList(false, false, false, true, false, false), // Shoot 4.
                Arrays.asList(false, false, false, false, true, false), // Leve weapon.
                Arrays.asList(true, false, true, false, false, false),  // Jump to the right
                Arrays.asList(false, true, true, false, false, false), // Turn left jump.
                Arrays.asList(false, false, false, false, true, false), // Drop gun.
                Arrays.asList(false, true, false, false, false, false), // Go to the left.
                Arrays.asList(false, false, false, true, false, false), // Shoot 5.
                Arrays.asList(false, false, false, true, false, false), // Shoot 6.
                Arrays.asList(true, false, false, true, false, false),  // Shoot 7.
                Arrays.asList(false, false, false, true, false, false), // Shoot 8
                Arrays.asList(false, false, false, false, true, false) // Drop second weapon.
        );

        TIMES_OF_COMBINATIONS = Arrays.asList(
                0, 80,
                80, 136,
                165, 166,
                173, 174,
                178, 179,
                182, 183,
                186, 187,
                212, 213,
                214, 226,
                264, 265,
                314, 315,
                317, 323,
                354, 355,
                374, 375,
                384, 385,
                404, 405,
                410, 411
        );
    }


    @FXML
    public void initialize() {
        makeInit(GameBoardPane);
    }

    @Override
    public Bot getBoardBot() {
        return new BasicBot(KEY_COMBINATIONS, TIMES_OF_COMBINATIONS);
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

        ArrayList<BoardObject> obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle(Obstacle1));
        obstacleList.add(new Obstacle(Obstacle2));
        obstacleList.add(new Obstacle(Obstacle3));
        obstacleList.add(new Obstacle(Obstacle4));
        obstacleList.add(new Obstacle(Obstacle5));

        obstacleList.add(new Portal(Portal1_R, Portal1_L.getLayoutX() - 35, Portal1_L.getLayoutY() + 15, false));
        obstacleList.add(new Portal(Portal1_L, Portal1_R.getLayoutX() + 65, Portal1_R.getLayoutY() + 15, true));

        obstacleList.add(new Weapon(weapon1));
        obstacleList.add(new Weapon(weapon2));

        return obstacleList;
    }

    public void addToScene(Node node) {
        GameBoardPane.getChildren().add(node);
    }

    public void removeNodeFromScene(Node node) {
        GameBoardPane.getChildren().remove(node);
    }

    @Override
    public double[] getSpawnPointsForWeapons() {
        return new double[]{weapon1.getLayoutX(), weapon1.getLayoutY(),
                weapon2.getLayoutX(), weapon2.getLayoutY()};
    }
}
