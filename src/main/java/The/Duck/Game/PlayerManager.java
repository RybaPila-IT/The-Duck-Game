package The.Duck.Game;

public class PlayerManager {

    private Player player;

    public PlayerManager(Player player) {
        this.player = player;
    }


    public void movePlayer() {

        if (ButtonInfo.isAPressed())
            player.accelerate(false);
        else if (ButtonInfo.isDPressed())
            player.accelerate(true);
        else if (ButtonInfo.isSpacePressed())
            player.jump();

        player.movePlayerModel();
    }


}
