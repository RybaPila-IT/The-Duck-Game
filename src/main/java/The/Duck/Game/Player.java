package The.Duck.Game;

import FXMLControlers.PlayerController;
import javafx.scene.layout.Region;

import java.util.List;

public class Player {

    private static final double MAX_SPEED = 0.25;
    private static final double ACC_VAL = 0.002;
    private static final double SLOW_VAL = 0.0003;
    private static final double HOR_MAX_SIZE = 1360.0;

    private static final double MAX_VERT_SPEED = 0.4;
    private static final double FALL_ACC = 0.0004;
    private static final double MAX_VER_SIZE = 701.0;

    private boolean isPlayerFacedRight;
    private boolean wantsToGrabWeapon;
    private double horizontalSpeed;
    private double verticalSpeed;
    private boolean jumping;

    private Weapon weapon;
    private final Rectangle playerModel;

    private final PlayerController controller;

    public Player(Region playerModel) {
        this.controller = new PlayerController(playerModel);
        this.wantsToGrabWeapon = false;
        this.horizontalSpeed = 0;
        this.verticalSpeed = 0;
        this.playerModel = new Rectangle(playerModel);
        this.weapon = null;
        this.isPlayerFacedRight = true;
    }

    public void accelerate(boolean toRight) {
        horizontalSpeed = toRight ? Math.min(MAX_SPEED, horizontalSpeed + ACC_VAL)
                : Math.max(-MAX_SPEED, horizontalSpeed - ACC_VAL);
    }

    public Rectangle getRegion() {
        return playerModel;
    }

    public void jump() {
        if (!jumping) {
            verticalSpeed = -MAX_VERT_SPEED;
            jumping = true;
        }
    }


    public void shoot() {
        weapon.shoot();
    }

    public void movePlayerModel() {
        movePlayer();
        dealWithCollisions();
        changePlayerSpeed();
        orderController();
    }

    public boolean isPlayerFacedRight() {
        return isPlayerFacedRight;
    }

    public boolean isPlayerJumping() {
        return jumping;
    }

    public void setLayoutX(double x) {
        playerModel.setX(x);
    }

    public void setSecondX(double x) {
        playerModel.setSecondX(x);
    }

    public void setLayoutY(double y) {
        playerModel.setY(y);
    }

    public void setSecondY(double y) {
        playerModel.setSecondY(y);
    }

    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void resetHorizontalSpeed() {
        horizontalSpeed = 0;
    }

    public void resetVerticalSpeed() {
        verticalSpeed = 0;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public void onGround() {
        jumping = false;
    }

    public boolean wantsToGrabWeapon() {
        return wantsToGrabWeapon && !hasWeapon();
    }

    public void setWeapon(Weapon w) {
        weapon = w;
    }

    public void setWantsToGrabWeapon(boolean b) {
        wantsToGrabWeapon = b;
    }

    public boolean hasWeapon() {
        return weapon != null;
    }

    public void dropWeapon() {

        if (!jumping) {
            wantsToGrabWeapon = false;
            weapon.fallDown();
            weapon = null;
        }

    }

    public void setPlayerGraphic() {
        controller.setPlayerGraphic(jumping, isPlayerFacedRight, horizontalSpeed);
    }

    private void fall() {

        if (playerModel.getLayoutY() != MAX_VER_SIZE) {

            if (verticalSpeed < 0)
                verticalSpeed += FALL_ACC;
            else
                verticalSpeed = Math.min(verticalSpeed + FALL_ACC, MAX_VER_SIZE);

            if (Math.abs(verticalSpeed) > 2 * FALL_ACC)
                jumping = true;

        } else {

            verticalSpeed = 0;
            jumping = false;

        }
    }

    private void movePlayer() {
        makeHorizontalMovement();
        makeVerticalMovement();
    }

    private void changePlayerSpeed() {
        slowDownHorizontally();
        fall();
    }

    private void dealWithCollisions() {

        List<BoardObject> elements = BoardElements.getInstance().collidedWith(playerModel);

        for (BoardObject e : elements)
            e.onPlayerCollision(this);

    }


    private void makeHorizontalMovement() {

        playerModel.addHorizontally(horizontalSpeed);

        if (playerModel.getLayoutX() > HOR_MAX_SIZE) {
            playerModel.setX(HOR_MAX_SIZE);
            horizontalSpeed = 0;
        } else if (playerModel.getLayoutX() < 0) {
            playerModel.setX(0);
            horizontalSpeed = 0;
        }

        decidePlayerFacing();
    }

    private void slowDownHorizontally() {

        if (horizontalSpeed != 0)
            horizontalSpeed += (horizontalSpeed > 0 ? -SLOW_VAL : SLOW_VAL);

        if (Math.abs(horizontalSpeed) < SLOW_VAL)
            horizontalSpeed = 0;

    }

    private void makeVerticalMovement() {

        playerModel.addVertically(verticalSpeed);

        if (playerModel.getLayoutY() > MAX_VER_SIZE)
            playerModel.setY(MAX_VER_SIZE);

    }

    private void decidePlayerFacing() {

        if (isPlayerFacedRight && horizontalSpeed < 0)
            isPlayerFacedRight = false;
        else if (!isPlayerFacedRight && horizontalSpeed > 0)
            isPlayerFacedRight = true;

    }

    private void orderController() {
        controller.setLayoutX(playerModel.getLayoutX());
        controller.setLayoutY(playerModel.getLayoutY());
    }


}
