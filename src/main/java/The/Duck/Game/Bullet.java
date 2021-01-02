package The.Duck.Game;

import FXMLControlers.BulletController;

import java.util.List;

public class Bullet extends BoardObject {

    private static final double BULLET_WIDTH = 16;
    private static final double BULLET_HEIGHT = 16;
    private static final double BULLET_SPEED = 40;
    private static final int ON_OBSTACLE = 10;
    private static final int ON_PLAYER = 3;

    private final boolean isBulletFacingRight;

    private boolean onObstacle;
    private int onObstacleWait;

    private final BulletController controller;

    public Bullet(Rectangle weapon, boolean isWeaponFacingRight) {

        super(new Rectangle(isWeaponFacingRight ? weapon.getSecondX() : weapon.getLayoutX(),
                weapon.getSecondY(), BULLET_WIDTH, BULLET_HEIGHT));

        isBulletFacingRight = isWeaponFacingRight;
        onObstacle = false;
        onObstacleWait = ON_OBSTACLE;
        controller = new BulletController(region);
    }

    private void changeBulletPosition() {
        region.addHorizontally(isBulletFacingRight ? BULLET_SPEED : -BULLET_SPEED);
    }

    private void handleObstacleCollision() {

        List<BoardObject> objects = BoardElements.getInstance().collidedWith(region);

        for (BoardObject object : objects)
            if (object.isBulletProof()) {
                region.setX(isBulletFacingRight ? object.getLayoutX() - 16 : object.getSecondX());
                onObstacle = true;
            }

    }

    private void informControllerAboutPosition() {
        controller.setLayoutX(region.getLayoutX());
    }

    private void moveBulletOutOfBoard() {
        region.setX(BoardConstants.getBoardWidth() + 400);
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
        return region.getLayoutX() > BoardConstants.getBoardWidth() ||
                region.getSecondX() < 0;
    }

    public void removeBulletFromScene() {
        controller.removeBullet();
    }

    @Override
    public void onTic() {

        moveBullet();

        if (outsideBoard())
            removeBulletFromScene();

    }

    @Override
    public void onPlayerCollision(Player player) {

        if (!onObstacle) {
            region.setX(player.getRegion().getLayoutX() + 15);
            onObstacle = true;
            onObstacleWait = ON_PLAYER;
            player.decreaseHealth();
        }

    }

    @Override
    public boolean isObjectValid() {
        return !outsideBoard();
    }

    public boolean equals(Object obj) {

        if (obj instanceof Bullet) {
            Bullet o = (Bullet) obj;
            return region.equals(o.region) &&
                    onObstacle == o.onObstacle &&
                    isBulletFacingRight == o.isBulletFacingRight &&
                    onObstacleWait == o.onObstacleWait;
        }

        return false;
    }
}
