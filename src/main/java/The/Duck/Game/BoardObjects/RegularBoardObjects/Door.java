package The.Duck.Game.BoardObjects.RegularBoardObjects;

import FXMLControlers.BoardObjectsControllers.DoorController;
import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.Player.Player;
import javafx.scene.layout.Region;

public class Door extends BoardObject implements BulletCollisionSensitive {

    private static final int INITIAL_HEALTH = 4;

    private final DoorController controller;

    private boolean isClosed;
    private boolean readyToChange;
    private int health;

    public Door(Region region) {

        super(new Rectangle(region));

        this.controller = new DoorController(region);
        this.isClosed = true;
        this.readyToChange = true;
        this.health = INITIAL_HEALTH;
    }

    @Override
    public void onTic() {

        if (!isValid())
            controller.remove();

    }

    @Override
    public void onPlayerCollision(Player player) {

        if (player.wantsToInteract() && isClosed && readyToChange) {
            readyToChange = false;
            isClosed = false;
            controller.open();
        } else if (player.wantsToInteract() && !isClosed && readyToChange) {
            isClosed = true;
            readyToChange = false;
            controller.close();
        } else if (isClosed)
            playerCollision(player);

        if (!player.wantsToInteract())
            readyToChange = true;

    }

    @Override
    public boolean isValid() {
        return health > 0;
    }

    @Override
    public void onBulletCollision(Bullet bullet) {

        if (isClosed) {
            bullet.region.setX(bullet.isBulletFacingRight() ? region.getLayoutX() - 16 : region.getSecondX());
            bullet.setOnObstacle(true);
            health--;
        }

    }

    @Override
    public boolean doesNotReact() {
        return !isClosed;
    }

    private void playerCollision(Player player) {

        if (player.getRegion().getLayoutX() >= region.getLayoutX())
            player.setLayoutX(region.getSecondX() + 0.5);
        else
            player.setSecondX(region.getLayoutX() - 0.5);

        player.resetHorizontalSpeed();
    }
}
