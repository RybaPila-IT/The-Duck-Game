package The.Duck.Game;

import javafx.scene.layout.Region;

public class Obstacle extends BoardObject implements BulletCollisionSensitive {


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
    public boolean isValid() {
        return true;
    }

    @Override
    public void onBulletCollision(Bullet bullet) {

        bullet.region.setX(bullet.isBulletFacingRight() ? region.getLayoutX() - 16 : region.getSecondX());
        bullet.setOnObstacle(true);

    }

    @Override
    public boolean isTransparent() {
        return false;
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
