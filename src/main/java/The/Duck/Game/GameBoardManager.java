package The.Duck.Game;

import FXMLControlers.FirstBoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class GameBoardManager {

    private static final String GAME_BOARD_1_PATH = "/TheShootingGameBoard1.fxml";

    private final MainMenuManager callingManager;
    private FirstBoardController controller;
    private GameManager gameManager;


    public GameBoardManager(MainMenuManager callingManager) {

        this.callingManager = callingManager;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(GAME_BOARD_1_PATH));

        try {

            /** Preprocessing of FXML board. */
            Parent root = loader.load();
            controller = loader.getController();
            controller.setManager(this);

            /** Setting GameManager and PlayerManager. */
            PlayerManager playerManager = new PlayerManager(controller.getPlayer());
            gameManager = new GameManager(playerManager);

            /** Setting BoardObstacles singleton value. */
            BoardObstacles boardObstacles = BoardObstacles.getInstance();
            boardObstacles.setObstacles(controller.getObstaclesList());

            /** Setting BoardWeapons singleton value. */
            BoardWeapons boardWeapons = BoardWeapons.getInstance();
            boardWeapons.setWeapons(controller.getWeaponList());

        } catch (IOException e) {
            System.err.println("GameBoardManager error. Unable to load FXML file with board layout");
            e.printStackTrace();
        }


    }

    public void StartGameBoard() {

        gameManager.startGameLoop();
        controller.showGameBoard();
    }

    public void EndGameBoard() {
        gameManager.stopGameLoop();
        controller.endGame();
        callingManager.showMainMenu();
    }


}
