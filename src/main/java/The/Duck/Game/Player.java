package The.Duck.Game;

import FXMLControlers.PlayerController;
import javafx.scene.layout.Region;

public class Player {

    private static final double MAX_SPEED = 0.25;
    private static final double ACC_VAL = 0.002;
    private static final double SLOW_VAL = 0.0003;
    private static final double HOR_MAX_SIZE = 1360.0;

    private static final double MAX_VERT_SPEED = 0.4;
    private static final double FALL_ACC = 0.0004;
    private static final double MAX_VER_SIZE = 701.0;

    private boolean isPlayerFacedRight;
    private double horSpeed;
    private double verticalSpeed;
    private boolean jumping;
    private Weapon weapon;

    private final PlayerController controller;
    private final Rectangle playerModel;

    public Player(Region playerModel) {
        this.controller = new PlayerController(playerModel);
        this.horSpeed = 0;
        this.verticalSpeed = 0;
        this.playerModel = new Rectangle(playerModel);
        this.weapon = null;
        this.isPlayerFacedRight = true;
    }

    public void accelerate(boolean toRight) {
        horSpeed = toRight ? Math.min(MAX_SPEED, horSpeed + ACC_VAL)
                : Math.max(-MAX_SPEED, horSpeed - ACC_VAL);
    }

    private void slowDownHorizontally() {

        if (horSpeed != 0)
            horSpeed += (horSpeed > 0 ? -SLOW_VAL : SLOW_VAL);

        if (Math.abs(horSpeed) < SLOW_VAL)
            horSpeed = 0;

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
        makeHorizontalMovement();
        makeVerticalMovement();
        slowDownHorizontally();
        fall();
        orderController();
        manageWeapon();
    }

    private void orderController() {
        controller.setLayoutX(playerModel.getLayoutX());
        controller.setLayoutY(playerModel.getLayoutY());
    }

    private void manageWeapon() {
        if (hasWeapon())
            weapon.followOwner(isPlayerFacedRight, jumping);
    }

    public boolean tryToGrabWeapon() {

        if (!hasWeapon()) {

            weapon = BoardWeapons.getInstance().findCollision(playerModel);

            if (hasWeapon())
                weapon.setOwner(playerModel);

        }

        return hasWeapon();
    }

    public boolean tryToThrowWeaponAway() {

        boolean thrown = false;

        if (!jumping) {
            weapon.setOwner(null);
            weapon.fallDown();
            weapon = null;
            thrown = true;
        }

        return thrown;
    }

    private boolean hasWeapon() {
        return weapon != null;
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

    private void makeHorizontalMovement() {

        doRegularHorizontalMove();
        dealWithHorizontalCollision();

    }

    private void makeVerticalMovement() {

        doRegularVerticalMovement();
        dealWithVerticalCollision();

    }

    private void doRegularVerticalMovement() {

        playerModel.addVertically(verticalSpeed);

        if (playerModel.getLayoutY() > MAX_VER_SIZE)
            playerModel.setY(MAX_VER_SIZE);

    }

    private void dealWithVerticalCollision() {

        Obstacle collision = BoardObstacles.getInstance().findCollision(playerModel);

        if (collision != null) {

            setVerticalObstacleBounds(collision.getObstacleRegion());

            verticalSpeed = 0;
        }
    }

    private void setVerticalObstacleBounds(Rectangle obstacle) {

        if (verticalSpeed < 0)
            playerModel.setY(obstacle.getSecondY() + 1);
        else if (verticalSpeed > 0) {
            playerModel.setY(obstacle.getLayoutY() - playerModel.getHeight());
            jumping = false;
        }

    }


    private void dealWithHorizontalCollision() {

        Obstacle collided = BoardObstacles.getInstance().findCollision(playerModel);

        if (collided != null && !isPlayerOnPlatform(collided.getObstacleRegion())) {

            setHorizontalObstacleBounds(collided.getObstacleRegion());

            horSpeed = 0;
        }

    }

    private boolean isPlayerOnPlatform(Rectangle obstacle) {
        return obstacle.getLayoutY() == playerModel.getSecondY();
    }


    private void setHorizontalObstacleBounds(Rectangle obstacle) {

        if (horSpeed > 0)
            playerModel.setX(obstacle.getLayoutX() - playerModel.getWidth() - 1);
        else if (horSpeed < 0)
            playerModel.setX(obstacle.getSecondX() + 1);

    }


    private void doRegularHorizontalMove() {

        playerModel.addHorizontally(horSpeed);

        if (playerModel.getLayoutX() > HOR_MAX_SIZE) {
            playerModel.setX(HOR_MAX_SIZE);
            horSpeed = 0;
        } else if (playerModel.getLayoutX() < 0) {
            playerModel.setX(0);
            horSpeed = 0;
        }

        decidePlayerFacing();
    }


    private void decidePlayerFacing() {

        if (isPlayerFacedRight && horSpeed < 0)
            isPlayerFacedRight = false;
        else if (!isPlayerFacedRight && horSpeed > 0)
            isPlayerFacedRight = true;

    }

    public void setPlayerGraphic() {
        controller.setPlayerGraphic(jumping, isPlayerFacedRight, horSpeed);
    }


}
