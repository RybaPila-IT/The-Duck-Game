package The.Duck.Game.BoardObjects.RegularBoardObjects;

import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.Player.Player;
import javafx.scene.layout.Region;

public class Portal extends BoardObject implements BulletCollisionSensitive {

    private final double xToTeleport;
    private final double yToTeleport;

    private final boolean isLeft;

    /**
     * Portal constructor.
     *
     * @param region JavaFX region representing portal.
     * @param x,y    Coordinates of the point to which player will be teleported.
     * @param l      Information if the portal is teleporting from left to right (player
     *               will enter through left side of the portal).
     */
    public Portal(Region region, double x, double y, boolean l) {

        super(new Rectangle(region));

        xToTeleport = x;
        yToTeleport = y;
        isLeft = l;
    }

    /**
     * Empty procedure.
     */
    @Override
    public void onTic() {
    }

    /**
     * Procedure will teleport player into point (x,y) specified at
     * the constructor.
     *
     * @param player Player which has collided with the portal.
     */
    @Override
    public void onPlayerCollision(Player player) {

        if (isLeft && player.getRegion().getSecondX() + 5 >= region.getSecondX()) {
            player.setLayoutX(xToTeleport);
            player.setLayoutY(yToTeleport);
        } else if (!isLeft && player.getRegion().getLayoutX() - 5 <= region.getLayoutX()) {
            player.setLayoutX(xToTeleport);
            player.setLayoutY(yToTeleport);
        }

    }

    @Override
    public boolean doesNotReact() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    /**
     * Procedure will teleport bullet to point (x,y)
     * specified at the constructor.
     *
     * @param bullet Bullet with which portal has collided.
     */
    @Override
    public void onBulletCollision(Bullet bullet) {

        double yOffset = bullet.region.getLayoutY() - region.getLayoutY();
        bullet.region.setX(xToTeleport);
        bullet.region.setY(yToTeleport + yOffset);
    }
}
