package The.Duck.Game;

public class ButtonInfo {

    private static boolean isAPressed;
    private static boolean isDPressed;
    private static boolean isSpacePressed;

    static {

        isAPressed = false;
        isDPressed = false;
        isSpacePressed = false;

    }

    public static void setAPressed(boolean value) {
        isAPressed = value;
    }

    public static void setDPressed(boolean value) {
        isDPressed = value;
    }

    public static void setSpacePressed(boolean value) {
        isSpacePressed = value;
    }

    public static boolean isAPressed() {
        return isAPressed;
    }

    public static boolean isDPressed() {
        return isDPressed;
    }

    public static boolean isSpacePressed() {
        return isSpacePressed;
    }
}
