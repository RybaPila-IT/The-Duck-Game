package The.Duck.Game.BoardObjects.RegularBoardObjects;

import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.Player.Player;

/**
 * Abstract class representing board objects.
 *
 * <p>
 * All objects which are present on board should extend this class.
 * It implements two interfaces strongly connected with gameplay:
 * TickResponsive and PlayerCollisionSensitive.
 * BoardObjects are also managed by special class containing list
 * of all board objects present at game scene.
 * </p>
 */
public abstract class BoardObject implements TickResponsive, PlayerCollisionSensitive {

    protected final Rectangle region;

    protected BoardObject(Rectangle r) {
        this.region = r;
    }

    public boolean intersects(Rectangle r) {
        return region.intersects(r);
    }

    public boolean doesNotReact() {
        return true;
    }

    public abstract void onTic();

    public abstract void onPlayerCollision(Player player);

    public abstract boolean isValid();
}
