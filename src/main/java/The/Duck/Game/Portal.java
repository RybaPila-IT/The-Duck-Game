package The.Duck.Game;

import javafx.scene.layout.Region;

public class Portal extends BoardObject {

    private final double xToTeleport;
    private final double yToTeleport;

    private final boolean isLeft;

    public Portal(Region region, double x, double y, boolean l) {

        super(new Rectangle(region));

        xToTeleport = x;
        yToTeleport = y;
        isLeft = l;
    }

    @Override
    public void onTic() {
    }

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
    public boolean isObjectValid() {
        return true;
    }
}
