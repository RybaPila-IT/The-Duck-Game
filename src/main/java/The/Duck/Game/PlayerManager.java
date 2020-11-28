package The.Duck.Game;

public class PlayerManager {

    private final Player player;
    private boolean hasWeapon;
    private boolean readyToThrowAway;
    private boolean readyToGrab;
    private boolean readyToShoot;

    public PlayerManager(Player player) {

        this.hasWeapon = false;
        this.readyToThrowAway = false;
        this.readyToGrab = true;
        this.readyToShoot = true;

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

    private void playerShooting() {

        if (ButtonInfo.isKPressed() && hasWeapon && readyToShoot) {
            player.shoot();
            readyToShoot = false;
        } else if (!ButtonInfo.isKPressed()) {
            readyToShoot = true;
        }

    }

    public void movePlayer() {

        playerMovement();
        playerWeaponHandling();
        playerShooting();

        player.setPlayerGraphic();
        player.movePlayerModel();
    }


}
