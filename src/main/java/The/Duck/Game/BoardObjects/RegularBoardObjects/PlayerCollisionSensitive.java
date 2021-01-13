package The.Duck.Game.BoardObjects.RegularBoardObjects;

import The.Duck.Game.Player.Player;

public interface PlayerCollisionSensitive {
    void onPlayerCollision(Player player);
}
