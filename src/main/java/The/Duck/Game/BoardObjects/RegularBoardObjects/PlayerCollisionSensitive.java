package The.Duck.Game.BoardObjects.RegularBoardObjects;

import The.Duck.Game.Player.Player;

/**
 * Interface implemented by object interacting with players.
 */
public interface PlayerCollisionSensitive {

    /**
     * Procedure which should be performed when object collides with player.
     *
     * @param player Player character with which this object has collided.
     */
    void onPlayerCollision(Player player);
}
