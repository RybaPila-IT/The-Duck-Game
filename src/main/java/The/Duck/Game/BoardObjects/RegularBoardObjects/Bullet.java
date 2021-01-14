package The.Duck.Game.BoardObjects.RegularBoardObjects;

import FXMLControlers.BoardObjectsControllers.BasicController;
import The.Duck.Game.BoardObjects.Animated.Blood;
import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.GameManagers.BoardConstants;
import The.Duck.Game.GameManagers.BoardElements;
import The.Duck.Game.Player.Player;

import java.util.List;

public class Bullet extends BoardObject {

    private static final String BULLET_STYLE = "bullet";
    private static final double BULLET_WIDTH = 16;
    private static final double BULLET_HEIGHT = 16;
    private static final double BULLET_SPEED = 40;
    private static final int ON_OBSTACLE = 10;
    private static final int ON_PLAYER = 3;

    private final BasicController controller;
    private final boolean isBulletFacingRight;

    private boolean onObstacle;
    private int onObstacleWait;

    /**
     * Bullet constructor.
     *
     * @param weapon              Weapon which spawned bullet as the result of shooting action.
     * @param isWeaponFacingRight Information about in which direction bullet will be moving.
     */
    public Bullet(Rectangle weapon, boolean isWeaponFacingRight) {

        super(new Rectangle(isWeaponFacingRight ? weapon.getSecondX() : weapon.getLayoutX(),
                weapon.getSecondY(), BULLET_WIDTH, BULLET_HEIGHT));

        isBulletFacingRight = isWeaponFacingRight;
        onObstacle = false;
        onObstacleWait = ON_OBSTACLE;
        controller = new BasicController(BoardConstants.getInstance().getController().createNewRegion(region, BULLET_STYLE));
    }

    public void setOnObstacle(boolean onObstacle) {
        this.onObstacle = onObstacle;
    }

    public boolean isBulletFacingRight() {
        return isBulletFacingRight;
    }

    public void moveBullet() {

        if (!onObstacle) {
            changeBulletPosition();
            handleObstacleCollision();
        } else if (onObstacleWait-- <= 0)
            moveBulletOutOfBoard();

        informControllerAboutPosition();
    }

    public boolean outsideBoard() {
        return region.getLayoutX() > BoardConstants.getInstance().getBoardWidth() ||
                region.getSecondX() < 0;
    }

    public void removeBulletFromScene() {
        controller.remove();
    }

    /**
     * Procedure managing bullet on game Tic.
     *
     * <p>
     * This procedure will move bullet.
     * Being stuck at the obstacle is also
     * counted as bullet movement.
     * If bullet is no longer valid (which
     * is determined by being outside of the board)
     * it will be removed from the game scene.
     * </p>
     */
    @Override
    public void onTic() {

        moveBullet();

        if (outsideBoard())
            removeBulletFromScene();

    }

    /**
     * Procedure performing player collision.
     *
     * <p>
     * If collision is valid (bullet is not on the obstacle and
     * player is not dead) bullet will deal damage to the player
     * and force him to reduce his health level.
     * </p>
     */
    @Override
    public void onPlayerCollision(Player player) {

        if (!onObstacle) {

            if (!player.isDead())
                player.decreaseHealth();

            region.setX(player.getRegion().getLayoutX() + 15);
            onObstacle = true;
            onObstacleWait = ON_PLAYER;
            Blood blood = new Blood(region.getLayoutX(), region.getLayoutY());
            BoardElements.getInstance().addBoardObject(blood);
        }

    }

    /**
     * Object validation function.
     *
     * @return True if bullet is not outside board; False otherwise.
     */
    @Override
    public boolean isValid() {
        return !outsideBoard();
    }

    private void handleObstacleCollision() {

        List<BoardObject> objects = BoardElements.getInstance().collidedWith(region);

        for (BoardObject object : objects)
            if (!object.doesNotReact()) {
                BulletCollisionSensitive collided = (BulletCollisionSensitive) object;
                collided.onBulletCollision(this);
            }

    }

    private void informControllerAboutPosition() {
        controller.setLayoutY(region.getLayoutY());
        controller.setLayoutX(region.getLayoutX());
    }

    private void moveBulletOutOfBoard() {
        region.setX(BoardConstants.getInstance().getBoardWidth() + 400);
    }

    private void changeBulletPosition() {
        region.addHorizontally(isBulletFacingRight ? BULLET_SPEED : -BULLET_SPEED);
    }

}
