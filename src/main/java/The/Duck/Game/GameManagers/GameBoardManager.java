package The.Duck.Game.GameManagers;

import FXMLControlers.BoardsControllers.BoardController;
import The.Duck.Game.Bots.Bot;
import The.Duck.Game.Player.PlayerManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameBoardManager {


    private static final List<String> GAME_BOARDS = Arrays.asList(
            "/FXML/TheShootingGameBoard1.fxml", "/FXML/TheShootingGameBoard2.fxml", "/FXML/TheShootingGameBoard3.fxml");

    private final MainMenuManager callingManager;
    private final boolean single;

    private GameManager gameManager;
    private BoardController controller;

    private int boardIdx;

    public GameBoardManager(MainMenuManager callingManager, boolean single) {

        this.single = single;
        this.boardIdx = 0;
        this.callingManager = callingManager;

        BoardConstants.getInstance().setManager(this);
        loadNewMap();
    }

    public void startGameBoard() {
        gameManager.startGameLoop();
        controller.showGameBoard();
    }

    public void loadNewMap() {

        if (!isMapLeft())
            endGame();
        else {

            if (existsBoard())
                endGameBoard();

            clearBoardConstants();
            loadNewDataForMap();
            startGameBoard();
        }

    }

    public void endGame() {
        gameManager.stopGameLoop();
        controller.endGame();
        callingManager.showMainMenu();
    }

    private void endGameBoard() {
        controller.endGame();
        gameManager.stopGameLoop();
    }

    private boolean existsBoard() {
        return controller != null && gameManager != null;
    }

    private void loadNewDataForMap() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(GAME_BOARDS.get(boardIdx++)));

        try {

            // Preprocessing of FXML board.
            Parent root = loader.load();
            controller = loader.getController();
            controller.setManager(this);

            // Setting GameManager and PlayerManager.
            PlayerManager firstPlayerManager = new PlayerManager(controller.getFirstPlayer(),
                    BoardConstants.getInstance().getPlayer1Info());
            PlayerManager secondPlayerManager = new PlayerManager(controller.getSecondPlayer(),
                    BoardConstants.getInstance().getPlayer2Info());

            Bot gameBot = single ? controller.getBoardBot() : null;
            gameManager = new GameManager(firstPlayerManager, secondPlayerManager, gameBot);

            BoardConstants.getInstance().setWeaponRespawn(new WeaponRespawn(controller.getSpawnPointsForWeapons()));

            // Setting BoardObstacles singleton value.
            BoardElements.getInstance().setBoardObjectsList(controller.getBoardObjectsList());

        } catch (IOException e) {
            System.err.println("GameBoardManager error. Unable to load FXML file with board layout.");
            System.err.println("Please, check whether the file " + GAME_BOARDS.get(boardIdx - 1) + " exists or is corrupted.");
        }
    }

    private void clearBoardConstants() {
        BoardConstants.getInstance().clear();
        ExplosionGenerator.getInstance().clear();
    }

    private boolean isMapLeft() {
        return boardIdx < GAME_BOARDS.size();
    }

}
