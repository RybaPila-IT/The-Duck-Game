package The.Duck.Game;

import FXMLControlers.BoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoardManager {

    private static final List<String> GAME_BOARDS;

    static {
        GAME_BOARDS = new ArrayList<>(
                Arrays.asList("/TheShootingGameBoard1.fxml", "/TheShootingGameBoard2.fxml")
        );
    }

    private final MainMenuManager callingManager;

    private GameManager gameManager;
    private BoardController controller;

    private int boardIdx;

    public GameBoardManager(MainMenuManager callingManager) {

        this.boardIdx = 0;
        this.callingManager = callingManager;

        BoardConstants.setManager(this);
        loadNewMap();
    }

    public void startGameBoard() {
        gameManager.startGameLoop();
        controller.showGameBoard();
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
            PlayerManager firstPlayerManager = new PlayerManager(controller.getFirstPlayer(), BoardConstants.getPlayer1Info());
            PlayerManager secondPlayerManager = new PlayerManager(controller.getSecondPlayer(), BoardConstants.getPlayer2Info());

            gameManager = new GameManager(firstPlayerManager, secondPlayerManager);

            // Setting BoardObstacles singleton value.
            BoardElements.getInstance().setBoardObjectsList(controller.getObstaclesList());

        } catch (IOException e) {
            System.err.println("GameBoardManager error. Unable to load FXML file with board layout");
            e.printStackTrace();
        }
    }

    private boolean isMapLeft() {
        return boardIdx >= GAME_BOARDS.size();
    }

    public void loadNewMap() {

        if (!isMapLeft())
            endGame();
        else {

            if (existsBoard())
                endGameBoard();

            loadNewDataForMap();
            startGameBoard();
        }

    }

    public void endGame() {
        gameManager.stopGameLoop();
        controller.endGame();
        callingManager.showMainMenu();
    }

}
