package The.Duck.Game;

public class ButtonInfo {

    private static boolean isAPressed;
    private static boolean isDPressed;
    private static boolean isSpacePressed;
    private static boolean isGPressed;

    static {

        isAPressed = false;
        isDPressed = false;
        isSpacePressed = false;
        isGPressed = false;

    }

    public static void setGPressed(boolean isGPressed) {
        ButtonInfo.isGPressed = isGPressed;
    }

    public static boolean isGPressed() {
        return isGPressed;
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
