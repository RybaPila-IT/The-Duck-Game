package The.Duck.Game;

public class ButtonInfo {

    private boolean pressedRight;
    private boolean pressedLeft;
    private boolean pressedUp;
    private boolean pressedGrab;
    private boolean pressedShoot;

    public ButtonInfo() {
        pressedRight = false;
        pressedLeft = false;
        pressedUp = false;
        pressedGrab = false;
        pressedShoot = false;
    }

    public boolean isPressedRight() {
        return pressedRight;
    }

    public void setPressedRight(boolean pressedRight) {
        this.pressedRight = pressedRight;
    }

    public boolean isPressedLeft() {
        return pressedLeft;
    }

    public void setPressedLeft(boolean pressedLeft) {
        this.pressedLeft = pressedLeft;
    }

    public boolean isPressedUp() {
        return pressedUp;
    }

    public void setPressedUp(boolean pressedUp) {
        this.pressedUp = pressedUp;
    }

    public boolean isPressedGrab() {
        return pressedGrab;
    }

    public void setPressedGrab(boolean pressedGrab) {
        this.pressedGrab = pressedGrab;
    }

    public boolean isPressedShoot() {
        return pressedShoot;
    }

    public void setPressedShoot(boolean pressedShoot) {
        this.pressedShoot = pressedShoot;
    }
}
