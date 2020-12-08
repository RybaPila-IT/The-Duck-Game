package The.Duck.Game;

public class PlayerManager {

    private boolean readyToGrabWeapon;
    private boolean readyToDropWeapon;
    private boolean readyToShoot;

    private final Player player;

    public PlayerManager(Player player) {

        this.player = player;
        this.readyToGrabWeapon = true;
        this.readyToDropWeapon = false;
        this.readyToShoot = true;
    }

    public void movePlayer() {

        playerMovement();
        playerWeaponHandling();
        playerShooting();
        playerModelUpdates();
    }

    private void playerMovement() {

        if (ButtonInfo.isAPressed())
            player.accelerate(false);
        else if (ButtonInfo.isDPressed())
            player.accelerate(true);
        else if (ButtonInfo.isSpacePressed())
            player.jump(0);

    }

    private void playerWeaponHandling() {

        if (!player.hasWeapon() && ButtonInfo.isGPressed() && readyToGrabWeapon)
            player.setWantsToGrabWeapon(true);
        else if (!player.hasWeapon() && !ButtonInfo.isGPressed()) {
            player.setWantsToGrabWeapon(false);
            readyToGrabWeapon = true;
        } else if (player.hasWeapon() && readyToDropWeapon && ButtonInfo.isGPressed()) {
            player.dropWeapon();
            readyToDropWeapon = false;
            readyToGrabWeapon = false;
        } else if (player.hasWeapon() && !ButtonInfo.isGPressed())
            readyToDropWeapon = true;

    }

    private void playerShooting() {

        if (player.hasWeapon() && readyToShoot && ButtonInfo.isKPressed()) {
            player.shoot();
            readyToShoot = false;
        } else if (player.hasWeapon() && !ButtonInfo.isKPressed()) {
            readyToShoot = true;
        }
    }

    private void playerModelUpdates() {

        player.setPlayerGraphic();
        player.movePlayerModel();
    }

}
