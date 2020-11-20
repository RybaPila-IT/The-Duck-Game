package The.Duck.Game;

import FXMLControlers.PlayerController;
import javafx.scene.layout.Region;

public class Player {

    private static final double maxSpeed = 0.25;
    private static final double accVal = 0.002;
    private static final double slowVal = 0.0003;
    private static final double horMaxSize = 1360.0;

    private static final double maxVertSpeed = 0.4;
    private static final double fallAcc = 0.0004;
    private static final double maxVerSize = 701.0;

    private double horSpeed;
    private double verticalSpeed;
    private boolean jumping;
    private Weapon weapon;

    private PlayerController controller;
    private Rectangle playerModel;


    public Player(Region playerModel) {
        this.controller = new PlayerController(playerModel);
        this.horSpeed = 0;
        this.verticalSpeed = 0;
        this.playerModel = new Rectangle(playerModel);
        this.weapon = null;
    }

    public void accelerate(boolean toRight) {
        horSpeed = toRight ? Math.min(maxSpeed, horSpeed + accVal) : Math.max(-maxSpeed, horSpeed - accVal);
    }

    private void slowDownHorizontally() {

        if (horSpeed != 0)
            horSpeed += (horSpeed > 0 ? -slowVal : slowVal);

        if (Math.abs(horSpeed) < slowVal)
            horSpeed = 0;

    }

    public void jump() {

        if (!jumping) {
            verticalSpeed = -maxVertSpeed;
            jumping = true;
        }

    }

    public void movePlayerModel() {

        makeHorizontalMovement();
        makeVerticalMovement();
        slowDownHorizontally();
        fall();

        controller.setLayoutX(playerModel.getLayoutX());
        controller.setLayoutY(playerModel.getLayoutY());

        if (hasWeapon())
            weapon.followOwner();

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

        if (playerModel.getLayoutY() != maxVerSize) {

            if (verticalSpeed < 0)
                verticalSpeed += fallAcc;
            else
                verticalSpeed = Math.min(verticalSpeed + fallAcc, maxVerSize);

            if (Math.abs(verticalSpeed) > 2 * fallAcc)
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

        if (playerModel.getLayoutY() > maxVerSize)
            playerModel.setY(maxVerSize);

    }

    private void dealWithVerticalCollision() {

        Obstacle collision = BoardObstacles.getInstance().findCollision(playerModel);

        if (collision != null) {

            Rectangle obstacle = collision.getObstacleRegion();

            if (verticalSpeed < 0)
                playerModel.setY(obstacle.getSecondY() + 1);
            else if (verticalSpeed > 0) {
                playerModel.setY(obstacle.getLayoutY() - playerModel.getHeight());
                jumping = false;
            }

            verticalSpeed = 0;
        }
    }


    private void dealWithHorizontalCollision() {

        Obstacle collided = BoardObstacles.getInstance().findCollision(playerModel);

        if (collided != null) {

            Rectangle obstacle = collided.getObstacleRegion();

            if (obstacle.getLayoutY() == playerModel.getSecondY())
                return;

            if (horSpeed > 0)
                playerModel.setX(obstacle.getLayoutX() - playerModel.getWidth() - 1);
            else if (horSpeed < 0)
                playerModel.setX(obstacle.getSecondX() + 1);

            horSpeed = 0;
        }

    }

    private void doRegularHorizontalMove() {

        playerModel.addHorizontally(horSpeed);

        if (playerModel.getLayoutX() > horMaxSize) {
            playerModel.setX(horMaxSize);
            horSpeed = 0;
        } else if (playerModel.getLayoutX() < 0) {
            playerModel.setX(0);
            horSpeed = 0;
        }

    }

}
