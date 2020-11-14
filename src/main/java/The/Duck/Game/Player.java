package The.Duck.Game;


import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.util.ArrayList;


public class Player {

    private static final double maxSpeed = 0.25;
    private static final double accVal = 0.002;
    private static final double slowVal = 0.0003;
    private static final double horMaxSize = 1360.0;

    private static final double maxVertSpeed = 0.4;
    private static final double fallAcc = 0.0004;
    private static final double maxVerSize = 694.0;

    private Region playerModel;
    private double horSpeed;
    private double verticalSpeed;
    private boolean jumping;

    private final ArrayList<Obstacle> obstacles;

    public Player(Region playerModel, ArrayList<Obstacle> Obstacles) {
        this.playerModel = playerModel;
        this.horSpeed = 0;
        this.verticalSpeed = 0;
        this.obstacles = Obstacles;
    }

    public void accelerate(boolean toRight) {
        horSpeed = toRight ? Math.min(maxSpeed, horSpeed + accVal) : Math.max(-maxSpeed, horSpeed - accVal);
    }

    public void slowDownHorizontally() {

        if (horSpeed != 0)
            horSpeed += (horSpeed > 0 ? -slowVal : slowVal);

    }

    public void jump() {

        if (!jumping) {
            verticalSpeed = -maxVertSpeed;
            jumping = true;
        }

    }

    public Region getRegion() {
        return playerModel;
    }

    public void movePlayerModel() {

        makeHorizontalMovement();
        makeVerticalMovement();
        slowDownHorizontally();
        fall();

    }

    private void fall() {

        if (playerModel.getLayoutY() != maxVerSize) {

            if (verticalSpeed < 0)
                verticalSpeed += fallAcc;
            else
                verticalSpeed = Math.min(verticalSpeed + fallAcc, maxVerSize);

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

        double newYPos = playerModel.getLayoutY() + verticalSpeed;
        playerModel.setLayoutY(Math.min(newYPos, maxVerSize));

    }

    private void dealWithVerticalCollision() {

        Obstacle collision = findCollision();

        if (collision != null) {

            if (verticalSpeed != 0)
                playerModel.setLayoutY(playerModel.getLayoutY() - verticalSpeed);

            if (verticalSpeed > 0)
                jumping = false;

            verticalSpeed = 0;

        }
    }

    private Obstacle findCollision() {

        Obstacle toReturn = null;

        for (Obstacle obstacle : obstacles)
            if (obstacle.intersects(playerModel))
                toReturn = obstacle;


        return toReturn;
    }

    private void dealWithHorizontalCollision() {

        Obstacle collided = findCollision();

        if (collided != null) {

            if (horSpeed != 0)
                playerModel.setLayoutX(playerModel.getLayoutX() - horSpeed);

            horSpeed = 0;

        }

    }

    private void doRegularHorizontalMove() {

        double newXPos = playerModel.getLayoutX() + horSpeed;

        if (horSpeed > 0)
            playerModel.setLayoutX(Math.min(newXPos, horMaxSize));
        else
            playerModel.setLayoutX(Math.max(newXPos, 0));

        if (newXPos > horMaxSize)
            horSpeed = horSpeed > 0 ? 0 : horSpeed;
        else if (newXPos < 0)
            horSpeed = horSpeed < 0 ? 0 : horSpeed;

    }

}
