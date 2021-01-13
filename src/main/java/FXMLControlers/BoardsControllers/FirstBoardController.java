package FXMLControlers.BoardsControllers;

import The.Duck.Game.BoardObjects.RegularBoardObjects.BoardObject;
import The.Duck.Game.BoardObjects.RegularBoardObjects.Jumper;
import The.Duck.Game.BoardObjects.RegularBoardObjects.Obstacle;
import The.Duck.Game.BoardObjects.RegularBoardObjects.Weapon;
import The.Duck.Game.Bots.BasicBot;
import The.Duck.Game.Bots.Bot;
import The.Duck.Game.Player.Player;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Arrays;
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
    private static final List<Integer> TIMING;

    static {
        KEY_COMBINATIONS = Arrays.asList(
                // Right, Left, Up, Shoot, Grab, Interact
                Arrays.asList(true, false, false, false, true, false),      // Right click and dropping weapon.
                Arrays.asList(false, true, false, false, false, false),     // Left click 1.
                Arrays.asList(false, false, false, false, true, false),     // Grab and weapon.
                Arrays.asList(false, true, true, true, false, false),      // Shoot, Jump, Go left
                Arrays.asList(false, false, false, true, false, false),     // Shoot 1
                Arrays.asList(false, false, false, true, false, false),     // Shoot 2
                Arrays.asList(false, false, false, true, false, false),     // Shoot 3
                Arrays.asList(false, true, false, false, false, false),    // Go left
                Arrays.asList(false, true, false, false, true, false),     // Go left and drop weapon
                Arrays.asList(false, false, false, false, true, false),     // Grab weapon
                Arrays.asList(true, false, false, false, false, false),    // Go right
                Arrays.asList(false, true, false, false, false, false),     // Turn left
                Arrays.asList(false, false, false, true, false, false),    // Shoot 1
                Arrays.asList(false, false, false, true, false, false),     // Shoot 2
                Arrays.asList(false, false, false, true, false, false),    // Shoot 3
                Arrays.asList(false, false, false, true, false, false),    // Shoot 4
                Arrays.asList(false, false, false, false, true, false)     // Drop Weapon

        );

        TIMING = Arrays.asList(
                80, 100,
                100, 105,
                120, 121,
                121, 140,
                145, 146,
                151, 152,
                170, 171,
                180, 190,
                190, 200,
                230, 231,
                250, 294,
                340, 341,
                350, 351,
                355, 356,
                360, 361,
                365, 366,
                375, 376
        );
    }

    @FXML
    public void initialize() {
        makeInit(gameBoardPane);
    }

    @Override
    public Bot getBoardBot() {
        return new BasicBot(KEY_COMBINATIONS, TIMING);
    }

    public Player getFirstPlayer() {
        return new Player(playerModel1, player1StyleClass,
                Arrays.asList(player1_heart1, player1_heart2, player1_heart3));
    }

    public Player getSecondPlayer() {
        return new Player(playerModel2, player2StyleClass,
                Arrays.asList(player2_heart1, player2_heart2, player2_heart3));
    }

    public List<BoardObject> getBoardObjectsList() {

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

    public double[] getSpawnPointsForWeapons() {

        return new double[]{weapon1.getLayoutX(), weapon1.getLayoutY(),
                weapon2.getLayoutX(), weapon2.getLayoutY()};
    }

    public void addToScene(Node node) {
        gameBoardPane.getChildren().add(node);
    }

    public void removeNodeFromScene(Node node) {
        gameBoardPane.getChildren().remove(node);
    }
}
