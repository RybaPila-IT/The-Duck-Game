package The.Duck.Game.Player;

import FXMLControlers.PlayerController.PlayerController;
import The.Duck.Game.BoardObjects.RegularBoardObjects.BoardObject;
import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.BoardObjects.RegularBoardObjects.Weapon;
import The.Duck.Game.GameManagers.BoardConstants;
import The.Duck.Game.GameManagers.BoardElements;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.util.List;

/**
 * Class representing Player character.
 *
 * <p>
 * This class is the back-end player character management
 * system. It stores all information about player position,
 * speed, weapon, health etc.
 * All constants associated with the speed gaining or
 * character dimensions are also present.
 * Player during gameplay does not move in binary way
 * (full or 0 speed). It accelerates and slows down
 * respectively in order to simulate inertia.
 * </p>
 */
public class Player {

    private static final double MAX_SPEED = 15;
    private static final double ACC_VAL = 3;
    private static final double SLOW_VAL = 0.5;

    private static final double MAX_VERT_SPEED = 20;
    private static final double FALL_ACC = 1.15;

    private static final double PLAYER_WIDTH = 80;
    private static final double PLAYER_HEIGHT = 110;

    private static final int INITIAL_HEALTH = 3;

    private final Rectangle playerModel;
    private final PlayerController controller;

    private boolean wantsToInteract;
    private boolean isPlayerFacedRight;
    private boolean wantsToGrabWeapon;
    private boolean jumping;
    private double horizontalSpeed;
    private double verticalSpeed;
    private int health;

    private Weapon weapon;

    /**
     * Constructor of the Player object.
     *
     * @param playerModel JavaFX.Region representing the player model.
     * @param styleClass  list of style classes associated with player object.
     * @param health      list of nodes containing heart images representing player health.
     */
    public Player(Region playerModel, List<String> styleClass, List<Node> health) {
        this.controller = new PlayerController(playerModel, styleClass, health);
        this.wantsToGrabWeapon = false;
        this.wantsToInteract = false;
        this.horizontalSpeed = 0;
        this.verticalSpeed = 0;
        this.health = INITIAL_HEALTH;
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

    public void jump(double val) {
        if (!jumping) {
            verticalSpeed = (val == 0 ? -MAX_VERT_SPEED : val);
            jumping = true;
        }
    }

    public void decreaseHealth() {
        health--;
        controller.decreaseHealth();
    }

    public boolean isDead() {
        return health <= 0;
    }


    public void shoot() {
        weapon.shoot();
    }

    /**
     * Procedure moving player at the scene.
     *
     * <p>
     * Function orders player to complete his next move in both
     * vertical and horizontal way. Next, it checks for any
     * existing collisions and deals with them.
     * Lastly it changes player speed and orders controller
     * to present back-end changes to the user of the game.
     * </p>
     */
    public void movePlayerModel() {
        movePlayer();
        dealWithCollisions();
        changePlayerSpeed();
        orderController();
    }

    public boolean wantsToInteract() {
        return wantsToInteract;
    }

    public void setWantsToInteract(boolean wantsToInteract) {
        this.wantsToInteract = wantsToInteract;
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

    /**
     * Procedure ordering player to drop his weapon.
     *
     * <p>
     * Player is unable to drop his weapon if:
     * a) he does not have one yet;
     * b) he is jumping.
     * In any other case player will drop the weapon
     * he possesses.
     * </p>
     */
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

        if (playerModel.getLayoutY() != BoardConstants.getInstance().getBoardHeight() - PLAYER_HEIGHT) {

            if (verticalSpeed < 0)
                verticalSpeed += FALL_ACC;
            else
                verticalSpeed = Math.min(verticalSpeed + FALL_ACC, MAX_VERT_SPEED);

            if (Math.abs(verticalSpeed) > 2 * FALL_ACC)
                jumping = true;

        } else if (verticalSpeed >= 0) {

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

        if (playerModel.getLayoutX() > BoardConstants.getInstance().getBoardWidth() - PLAYER_WIDTH) {
            playerModel.setX(BoardConstants.getInstance().getBoardWidth() - PLAYER_WIDTH);
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

        if (playerModel.getLayoutY() > BoardConstants.getInstance().getBoardHeight() - PLAYER_HEIGHT)
            playerModel.setY(BoardConstants.getInstance().getBoardHeight() - PLAYER_HEIGHT);

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
