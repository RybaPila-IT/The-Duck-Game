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

    private static final ButtonInfo player1Info;
    private static final ButtonInfo player2Info;

    private static final List<String> player1StyleClass;
    private static final List<String> player2StyleClass;

    private static final List<String> explosionStyles;
    private static final List<String> bloodStyles;

    private static final int EXPLOSION_NUMBER = 18;
    private static final int BLOOD_NUMBER = 3;

    private static final String EXPLOSION_PREFIX = "explosion_";
    private static final String BLOOD_PREFIX = "blood_";

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

        explosionStyles = new ArrayList<>();
        bloodStyles = new ArrayList<>();

        for (int i = 0; i < EXPLOSION_NUMBER; i++)
            explosionStyles.add(EXPLOSION_PREFIX + (i + 1));
        for (int i = 0; i < BLOOD_NUMBER; i++)
            bloodStyles.add(BLOOD_PREFIX + (i + 1));
    }

    public static List<String> getExplosionStyles() {
        return explosionStyles;
    }

    public static List<String> getBloodStyles() {
        return bloodStyles;
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
