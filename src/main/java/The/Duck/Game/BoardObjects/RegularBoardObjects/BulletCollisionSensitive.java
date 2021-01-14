package The.Duck.Game.BoardObjects.RegularBoardObjects;

/**
 * Interface implemented by object interacting with bullets.
 */
public interface BulletCollisionSensitive {

    /**
     * Procedure which should be performed when object collides with bullet.
     *
     * @param bullet Bullet with which this object has collided.
     */
    void onBulletCollision(Bullet bullet);

}
