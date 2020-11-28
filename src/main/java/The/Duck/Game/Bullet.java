package The.Duck.Game;

import FXMLControlers.BulletController;

public class Bullet {

    private static final double BULLET_WIDTH = 16;
    private static final double BULLET_HEIGHT = 16;
    private static final double BULLET_SPEED = 0.5;

    private boolean isBulletFacingRight;
    private boolean onObstacle;
    private int onObstacleWait;

    private Rectangle bulletArea;
    private BulletController controller;

    public Bullet(Rectangle weapon, boolean isWeaponFacingRight) {

        isBulletFacingRight = isWeaponFacingRight;
        onObstacle = false;
        onObstacleWait = 160;

        double x1 = isWeaponFacingRight ? weapon.getSecondX() : weapon.getLayoutX();
        double y1 = weapon.getSecondY();

        bulletArea = new Rectangle(x1, y1, BULLET_WIDTH, BULLET_HEIGHT);
        controller = new BulletController(bulletArea);
    }

    private void changeBulletPosition() {
        bulletArea.addHorizontally(isBulletFacingRight ? BULLET_SPEED : -BULLET_SPEED);
    }

    private void handleObstacleCollision() {

        Obstacle collided = BoardObstacles.getInstance().findCollision(bulletArea);

        onObstacle = collided != null;

        if (collided != null)
            bulletArea.setX(isBulletFacingRight ? collided.getObstacleRegion().getLayoutX() - 16
                    : collided.getObstacleRegion().getSecondX());
    }

    private void informControllerAboutPosition() {
        controller.setLayoutX(bulletArea.getLayoutX());
    }

    private void moveBulletOutOfBoard() {
        bulletArea.setX(1800);
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
        return bulletArea.getLayoutX() > BoardConstants.getBoardWidth() ||
                bulletArea.getSecondX() < 0;
    }

    public void removeBulletFromScene() {
        controller.removeBullet();
    }

}
