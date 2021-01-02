package The.Duck.Game;

import FXMLControlers.BoardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardConstants {

    private static double BOARD_WIDTH;
    private static double BOARD_HEIGHT;

    private static BoardController controller;
    private static GameBoardManager manager;

    private static ButtonInfo player1Info;
    private static ButtonInfo player2Info;

    private static List<String> player1StyleClass;
    private static List<String> player2StyleClass;

    static {
        player1Info = new ButtonInfo();
        player2Info = new ButtonInfo();

        player1StyleClass = new ArrayList<>(
                Arrays.asList("soldier-jump-right", "soldier-jump-left", "soldier-walk-1-right",
                        "soldier-walk-2-right", "soldier-walk-1-left", "soldier-walk-2-left",
                        "soldier-stand")
        );

        player2StyleClass = new ArrayList<>(
                Arrays.asList("adventurer-jump-right", "adventurer-jump-left", "adventurer-walk-1-right",
                        "adventurer-walk-2-right", "adventurer-walk-1-left", "adventurer-walk-2-left",
                        "adventurer-stand")
        );

    }

    public static GameBoardManager getManager() {
        return manager;
    }

    public static void setManager(GameBoardManager manager) {
        BoardConstants.manager = manager;
    }

    public static List<String> getPlayer1StyleClass() {
        return player1StyleClass;
    }

    public static List<String> getPlayer2StyleClass() {
        return player2StyleClass;
    }

    public static ButtonInfo getPlayer1Info() {
        return player1Info;
    }

    public static ButtonInfo getPlayer2Info() {
        return player2Info;
    }

    public static BoardController getController() {
        return controller;
    }

    public static void setController(BoardController controller) {
        BoardConstants.controller = controller;
    }

    public static double getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static void setBoardWidth(double boardWidth) {
        BOARD_WIDTH = boardWidth;
    }

    public static double getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public static void setBoardHeight(double boardHeight) {
        BOARD_HEIGHT = boardHeight;
    }
}
