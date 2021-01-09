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

    private static final List<List<Boolean>> KEY_COMBINATIONS_BEG;
    private static final List<Integer> TIMING_BEG;

    private static final List<List<Boolean>> KEY_COMBINATIONS_LOOP;
    private static final List<Integer> TIMING_LOOP;

    static {

        KEY_COMBINATIONS_BEG = Arrays.asList(
                // Right, Left, Up, Shoot, Grab, Interact
                Arrays.asList(false, true, true, false, false, false),      // Jump to left.
                Arrays.asList(false, true, false, false, false, true),     // Open doors.
                Arrays.asList(true, false, true, false, false, false),       // Jump to the right.
                Arrays.asList(false, true, true, false, false, false),       // Jump to the left.
                Arrays.asList(true, false, true, false, false, false),      // Jump to right
                Arrays.asList(false, true, true, false, false, false),       // Jump to left
                Arrays.asList(false, true, true, false, false, false)       // Jump to left
        );

        TIMING_BEG = Arrays.asList(
                80, 100,
                120, 128,
                150, 170,
                175, 190,
                220, 230,
                250, 260,
                270, 290
        );

        KEY_COMBINATIONS_LOOP = Arrays.asList(
                // Right, Left, Up, Shoot, Grab, Interact
                Arrays.asList(false, false, false, false, true, false),    // Grab weapon
                Arrays.asList(true, false, true, false, false, false),      // Jump to right
                Arrays.asList(false, false, false, true, false, false),     // Shoot 1
                Arrays.asList(false, false, false, true, false, false),     // Shoot 2
                Arrays.asList(true, false, true, false, false, false),      // Jump to right
                Arrays.asList(false, false, false, true, false, false),     // Shoot 3
                Arrays.asList(false, false, false, true, false, false),     // Shoot 4
                Arrays.asList(false, false, false, false, true, false),    // Drop weapon
                Arrays.asList(true, false, false, false, false, false),     // Go to right.
                Arrays.asList(true, false, true, false, false, false),      // Jump to right
                Arrays.asList(false, false, false, false, true, false),    // Grab weapon
                Arrays.asList(false, true, false, false, false, false),    // Go to left
                Arrays.asList(false, false, false, true, false, false),     // Shoot 1
                Arrays.asList(false, false, false, true, false, false),     // Shoot 2
                Arrays.asList(false, false, false, true, false, false),     // Shoot 3
                Arrays.asList(false, false, false, true, false, false),     // Shoot 4
                Arrays.asList(false, false, false, false, true, false),    // Drop weapon
                Arrays.asList(false, true, false, false, false, false),    // Go to left
                Arrays.asList(false, true, true, false, false, false),       // Jump to left
                Arrays.asList(false, true, true, false, false, false)       // Jump to left
        );

        TIMING_LOOP = Arrays.asList(
                60, 61,
                62, 68,
                113, 114,
                116, 117,
                125, 130,
                133, 134,
                136, 137,
                155, 156,
                157, 162,
                174, 190,
                200, 201,
                208, 212,
                250, 251,
                258, 259,
                265, 266,
                271, 272,
                280, 281,
                282, 288,
                289, 300,
                330, 350

        );

    }


    @FXML
    public void initialize() {
        makeInit(GameBoardPane);
    }

    @Override
    public Bot getBoardBot() {
        return new NonLoopStartBot(KEY_COMBINATIONS_BEG, TIMING_BEG, KEY_COMBINATIONS_LOOP, TIMING_LOOP);
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
