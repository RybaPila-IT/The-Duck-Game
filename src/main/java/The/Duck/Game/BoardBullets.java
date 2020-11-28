package The.Duck.Game;

import java.util.ArrayList;
import java.util.List;

public class BoardBullets {

    private List<Bullet> bullets;
    private static BoardBullets instance;

    private BoardBullets() {
        bullets = new ArrayList<>();
    }

    public static BoardBullets getInstance() {

        if (instance == null)
            instance = new BoardBullets();

        return instance;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void moveBullets() {

        if (!movedValidBullets())
            clearBullets();

    }

    private boolean movedValidBullets() {

        boolean hasValidBullet = false;

        for (Bullet b : bullets) {

            if (!b.outsideBoard()) {
                b.moveBullet();
                hasValidBullet = true;
            }

        }

        return hasValidBullet;
    }

    private void clearBullets() {

        for (Bullet b : bullets)
            b.removeBulletFromScene();

        bullets.clear();
    }


}
