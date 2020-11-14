package The.Duck.Game;

public class ButtonInfo {

    private boolean isAPressed;
    private boolean isDPressed;
    private boolean isSpacePressed;

    public ButtonInfo() {

        isAPressed = false;
        isDPressed = false;
        isSpacePressed = false;

    }

    public void setAPressed(boolean value) {
        isAPressed = value;
    }

    public void setDPressed(boolean value) {
        isDPressed = value;
    }

    public void setSpacePressed(boolean value) {
        isSpacePressed = value;
    }

    public boolean isAPressed() {
        return isAPressed;
    }

    public boolean isDPressed() {
        return isDPressed;
    }

    public boolean isSpacePressed() {
        return isSpacePressed;
    }
}
