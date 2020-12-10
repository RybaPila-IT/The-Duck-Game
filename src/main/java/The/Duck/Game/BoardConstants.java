package The.Duck.Game;

import FXMLControlers.BoardController;

public class BoardConstants {

    private static double BOARD_WIDTH;
    private static double BOARD_HEIGHT;

    private static BoardController controller;

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
