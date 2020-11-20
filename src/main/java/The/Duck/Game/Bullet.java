package The.Duck.Game;

import FXMLControlers.BulletController;

public class Bullet {

    private static final double BULLET_WIDTH = 16;
    private static final double BULLET_HEIGHT = 16;
    private static final double BULLET_SPEED = 0.5;

    private Rectangle bulletArea;
    private BulletController controller;

    public Bullet(Rectangle weapon) {

        double x1 = weapon.getSecondX();
        double y1 = weapon.getSecondY();

        bulletArea = new Rectangle(x1, y1, BULLET_WIDTH, BULLET_HEIGHT);
        controller = new BulletController(bulletArea);
    }

    public void moveBullet() {
        bulletArea.addHorizontally(BULLET_SPEED);
        controller.setLayoutX(bulletArea.getLayoutX());
    }

    public boolean outsideBoard() {
        return bulletArea.getLayoutX() > BoardConstants.getBoardWidth();
    }

    public void removeBulletFromScene() {
        controller.removeBullet();
    }


}
