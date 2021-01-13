package The.Duck.Game.GameManagers;

import FXMLControlers.BoardsControllers.BoardController;

public class BoardConstants {

    private double BOARD_WIDTH;
    private double BOARD_HEIGHT;
    private boolean EXPLOSION_ON;

    private BoardController controller;
    private GameBoardManager manager;
    private WeaponRespawn weaponRespawn;

    private final ButtonInfo player1Info = new ButtonInfo();
    private final ButtonInfo player2Info = new ButtonInfo();

    private static BoardConstants instance;

    private BoardConstants() {
        BOARD_WIDTH = 0;
        BOARD_HEIGHT = 0;
        EXPLOSION_ON = false;
        controller = null;
        manager = null;
        weaponRespawn = null;
    }


    public static BoardConstants getInstance() {

        if (instance == null)
            instance = new BoardConstants();

        return instance;
    }


    public GameBoardManager getManager() {
        return manager;
    }

    public void setManager(GameBoardManager manager) {
        this.manager = manager;
    }

    public ButtonInfo getPlayer1Info() {
        return player1Info;
    }

    public boolean isExplosionOn() {
        return EXPLOSION_ON;
    }

    public void setExplosionOn(boolean explosionOn) {
        EXPLOSION_ON = explosionOn;
    }

    public WeaponRespawn getWeaponRespawn() {
        return weaponRespawn;
    }

    public void setWeaponRespawn(WeaponRespawn weaponRespawn) {
        this.weaponRespawn = weaponRespawn;
    }

    public ButtonInfo getPlayer2Info() {
        return player2Info;
    }

    public BoardController getController() {
        return controller;
    }

    public void setController(BoardController controller) {
        this.controller = controller;
    }

    public double getBoardWidth() {
        return BOARD_WIDTH;
    }

    public void setBoardWidth(double boardWidth) {
        BOARD_WIDTH = boardWidth;
    }

    public double getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public void setBoardHeight(double boardHeight) {
        BOARD_HEIGHT = boardHeight;
    }

    public void clear() {
        BOARD_HEIGHT = 0;
        BOARD_WIDTH = 0;
        EXPLOSION_ON = false;
        player1Info.clear();
        player2Info.clear();
    }
}
