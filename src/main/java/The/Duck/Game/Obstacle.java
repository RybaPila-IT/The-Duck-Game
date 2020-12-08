package The.Duck.Game;

import javafx.scene.layout.Region;

public class Obstacle extends BoardObject {


    public Obstacle(Region region) {
        super(new Rectangle(region));
    }

    @Override
    public void onTic() {
    }

    @Override
    public void onPlayerCollision(Player player) {

        Rectangle r = new Rectangle(player.getRegion());
        r.addHorizontally(-1 * player.getHorizontalSpeed());

        if (!r.intersects(region))
            horizontalPlayerCollision(player);
        else
            verticalPlayerCollision(player);
    }

    @Override
    public boolean isObjectValid() {
        return true;
    }

    @Override
    public boolean isBulletProof() {
        return true;
    }

    public Rectangle getObstacleRegion() {
        return region;
    }

    private void horizontalPlayerCollision(Player player) {

        if (player.getHorizontalSpeed() < 0)
            player.setLayoutX(region.getSecondX() + 0.5);
        else
            player.setSecondX(region.getLayoutX() - 0.5);

        player.resetHorizontalSpeed();
    }

    private void verticalPlayerCollision(Player player) {

        if (player.getVerticalSpeed() < 0)
            player.setLayoutY(region.getSecondY());
        else {
            player.setSecondY(region.getLayoutY());
            player.onGround();
        }

        player.resetVerticalSpeed();
    }

}
