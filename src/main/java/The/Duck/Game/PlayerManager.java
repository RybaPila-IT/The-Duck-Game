package The.Duck.Game;

public class PlayerManager {

    private Player player;
    private boolean hasWeapon = false;
    private boolean readyToThrowAway = false;
    private boolean readyToGrab = true;

    public PlayerManager(Player player) {
        this.player = player;
    }

    private void playerMovement() {

        if (ButtonInfo.isAPressed())
            player.accelerate(false);
        else if (ButtonInfo.isDPressed())
            player.accelerate(true);
        else if (ButtonInfo.isSpacePressed())
            player.jump();

    }

    private void playerWeaponHandling() {

        if (ButtonInfo.isGPressed()) {

            if (!hasWeapon && readyToGrab)
                hasWeapon = player.tryToGrabWeapon();
            else if (readyToThrowAway) {

                if (player.tryToThrowWeaponAway()) {
                    readyToThrowAway = false;
                    hasWeapon = false;
                    readyToGrab = false;
                }
            }

        } else {

            if (hasWeapon)
                readyToThrowAway = true;
            else
                readyToGrab = true;

        }
    }


    public void movePlayer() {

        playerMovement();
        playerWeaponHandling();

        player.movePlayerModel();
    }


}
