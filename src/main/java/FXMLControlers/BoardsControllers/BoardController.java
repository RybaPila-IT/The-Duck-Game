package FXMLControlers.BoardsControllers;

import The.Duck.Game.BoardObjects.RegularBoardObjects.BoardObject;
import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.Bots.Bot;
import The.Duck.Game.GameManagers.BoardConstants;
import The.Duck.Game.GameManagers.GameBoardManager;
import The.Duck.Game.Player.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * Top of the board controllers hierarchy.
 * <p>
 * BoardController is the abstract class implementing features
 * and actions used for every board controller.
 * It implies on later board controllers to enable
 * specific actions in order to communicate with board managers
 * correctly.
 */
abstract public class BoardController {

    protected static final List<String> player1StyleClass = Arrays.asList(
            "soldier-jump-right", "soldier-jump-left", "soldier-walk-1-right",
            "soldier-walk-2-right", "soldier-walk-1-left", "soldier-walk-2-left",
            "soldier-stand", "soldier-dead");

    protected static final List<String> player2StyleClass = Arrays.asList(
            "adventurer-jump-right", "adventurer-jump-left", "adventurer-walk-1-right",
            "adventurer-walk-2-right", "adventurer-walk-1-left", "adventurer-walk-2-left",
            "adventurer-stand", "adventurer-dead");


    private final static String GAME_NAME = "The Shooting Game";
    private static final String ICON_PATH = "/Graphics/weapon/weapon_2_face_left.png";

    private Scene gameBoardScene;
    private Stage gameBoardStage;

    private GameBoardManager manager;

    private void setStageOnClose() {
        gameBoardStage.setOnCloseRequest(value -> manager.endGame());
    }

    private void setSceneOnMouseEvents() {

        gameBoardScene.setOnKeyPressed(value -> {

            if (value.getCode() == KeyCode.D)
                BoardConstants.getInstance().getPlayer1Info().setPressedRight(true);
            else if (value.getCode() == KeyCode.A)
                BoardConstants.getInstance().getPlayer1Info().setPressedLeft(true);
            else if (value.getCode() == KeyCode.W)
                BoardConstants.getInstance().getPlayer1Info().setPressedUp(true);
            else if (value.getCode() == KeyCode.V)
                BoardConstants.getInstance().getPlayer1Info().setPressedGrab(true);
            else if (value.getCode() == KeyCode.B)
                BoardConstants.getInstance().getPlayer1Info().setPressedShoot(true);
            else if (value.getCode() == KeyCode.UP)
                BoardConstants.getInstance().getPlayer2Info().setPressedUp(true);
            else if (value.getCode() == KeyCode.LEFT)
                BoardConstants.getInstance().getPlayer2Info().setPressedLeft(true);
            else if (value.getCode() == KeyCode.RIGHT)
                BoardConstants.getInstance().getPlayer2Info().setPressedRight(true);
            else if (value.getCode() == KeyCode.NUMPAD1)
                BoardConstants.getInstance().getPlayer2Info().setPressedGrab(true);
            else if (value.getCode() == KeyCode.NUMPAD3)
                BoardConstants.getInstance().getPlayer2Info().setPressedShoot(true);
            else if (value.getCode() == KeyCode.P && !BoardConstants.getInstance().isExplosionOn())
                BoardConstants.getInstance().setExplosionOn(true);
            else if (value.getCode() == KeyCode.P)
                BoardConstants.getInstance().setExplosionOn(false);
            else if (value.getCode() == KeyCode.N)
                BoardConstants.getInstance().getPlayer1Info().setInteract(true);
            else if (value.getCode() == KeyCode.NUMPAD5)
                BoardConstants.getInstance().getPlayer2Info().setInteract(true);
        });

        gameBoardScene.setOnKeyReleased(value -> {

            if (value.getCode() == KeyCode.D)
                BoardConstants.getInstance().getPlayer1Info().setPressedRight(false);
            else if (value.getCode() == KeyCode.A)
                BoardConstants.getInstance().getPlayer1Info().setPressedLeft(false);
            else if (value.getCode() == KeyCode.W)
                BoardConstants.getInstance().getPlayer1Info().setPressedUp(false);
            else if (value.getCode() == KeyCode.V)
                BoardConstants.getInstance().getPlayer1Info().setPressedGrab(false);
            else if (value.getCode() == KeyCode.B)
                BoardConstants.getInstance().getPlayer1Info().setPressedShoot(false);
            else if (value.getCode() == KeyCode.UP)
                BoardConstants.getInstance().getPlayer2Info().setPressedUp(false);
            else if (value.getCode() == KeyCode.LEFT)
                BoardConstants.getInstance().getPlayer2Info().setPressedLeft(false);
            else if (value.getCode() == KeyCode.RIGHT)
                BoardConstants.getInstance().getPlayer2Info().setPressedRight(false);
            else if (value.getCode() == KeyCode.NUMPAD1)
                BoardConstants.getInstance().getPlayer2Info().setPressedGrab(false);
            else if (value.getCode() == KeyCode.NUMPAD3)
                BoardConstants.getInstance().getPlayer2Info().setPressedShoot(false);
            else if (value.getCode() == KeyCode.N)
                BoardConstants.getInstance().getPlayer1Info().setInteract(false);
            else if (value.getCode() == KeyCode.NUMPAD5)
                BoardConstants.getInstance().getPlayer2Info().setInteract(false);
        });
    }

    private void customizeGameStage() {

        gameBoardStage.setScene(gameBoardScene);
        gameBoardStage.setResizable(false);
        gameBoardStage.setTitle(GAME_NAME);
        gameBoardStage.getIcons().add(new Image(ICON_PATH));
    }

    /*
        Function used as "init" in regular board controllers.
        It sets all necessary action listeners
        and creates board Scene and Stage for pane
        delivered by controller. Controller has this pane
        presumably from FXML file specifying the board layout.
     */
    protected void makeInit(AnchorPane pane) {

        gameBoardScene = new Scene(pane);
        gameBoardStage = new Stage();

        customizeGameStage();
        setStageOnClose();
        setSceneOnMouseEvents();

        BoardConstants.getInstance().setBoardWidth(pane.getPrefWidth());
        BoardConstants.getInstance().setBoardHeight(pane.getPrefHeight());
        BoardConstants.getInstance().setController(this);
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

    /**
     * Utility function creating JavaFX Region.
     *
     * <p>
     * Function creates JavaFX Region from acquired
     * The.Duck.Game.Rectangle object. It also
     * adds freshly created Region to the Scene and
     * displays it.
     * </p>
     *
     * @param r Rectangle describing Region to create.
     * @return Created Region.
     */
    public Region createNewRegion(Rectangle r) {

        Region region = new Region();
        addToScene(region);
        region.setLayoutY(r.getLayoutY());
        region.setLayoutX(r.getLayoutX());
        region.setPrefWidth(r.getWidth());
        region.setPrefHeight(r.getHeight());

        return region;
    }

    /**
     * Utility function creating JavaFX Region with Style.
     *
     * <p>
     * Function creates JavaFX Region from acquired
     * The.Duck.Game.Rectangle object. It also
     * adds freshly created Region to the Scene and
     * displays it. Lastly it sets Region style class
     * to Style specified as the argument.
     * </p>
     *
     * @param r     Rectangle describing Region to create.
     * @param style Style of the Region.
     * @return Created Region.
     */
    public Region createNewRegion(Rectangle r, String style) {

        Region region = createNewRegion(r);
        region.getStyleClass().add(style);

        return region;
    }

    /**
     * Function creating Bot.
     *
     * <p>
     * This abstract function creates bot
     * which will move itself during the game
     * and will attempt to fight player.
     * Bot should be implemented with respect
     * to the board on which he will be fighting.
     * </p>
     *
     * @return Bot object.
     */
    abstract public Bot getBoardBot();

    /**
     * Function creating Player object.
     *
     * @return First player object.
     */
    abstract public Player getFirstPlayer();

    /**
     * Function creating Player object.
     *
     * @return Second player object.
     */
    abstract public Player getSecondPlayer();

    /**
     * Getter for all BoardObjects.
     *
     * <p>
     * Function returns the list of the all
     * BoardObjects which are present
     * at the very beginning of the gameplay
     * on the board.
     * </p>
     *
     * @return List of all BoardObjects.
     */
    abstract public List<BoardObject> getBoardObjectsList();

    abstract public void addToScene(Node node);

    abstract public void removeNodeFromScene(Node node);

    /**
     * Getter for spawn points of the weapons.
     *
     * <p>
     * Function returns the array of points (x,y)
     * which do represent the positions of the
     * upper left corner of the area for the spawn point.
     * They are usually equal to beginning weapon
     * positions.
     * In the array coordinates are placed in following
     * order : (x1, y1, x2, y2, ..., xN, yN).
     * </p>
     *
     * @return Array of coordinates of the spawn points.
     */
    abstract public double[] getSpawnPointsForWeapons();

}
