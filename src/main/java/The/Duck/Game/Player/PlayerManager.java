package The.Duck.Game.Player;

import The.Duck.Game.GameManagers.ButtonInfo;

public class PlayerManager {

    private boolean readyToGrabWeapon;
    private boolean readyToDropWeapon;
    private boolean readyToShoot;

    private final Player player;
    private final ButtonInfo playerInfo;

    public PlayerManager(Player player, ButtonInfo playerInfo) {

        this.playerInfo = playerInfo;
        this.player = player;
        this.readyToGrabWeapon = true;
        this.readyToDropWeapon = false;
        this.readyToShoot = true;
    }

    public void movePlayer() {

        if (!isPlayerDead()) {
            playerMovement();
            playerWeaponHandling();
            playerShooting();
            playerExtraActions();
        }

        playerModelUpdates();
    }

    private void playerExtraActions() {
        player.setWantsToInteract(playerInfo.isInteract());
    }

    private void playerMovement() {

        if (playerInfo.isPressedLeft())
            player.accelerate(false);
        if (playerInfo.isPressedRight())
            player.accelerate(true);
        if (playerInfo.isPressedUp())
            player.jump(0);
    }

    private void playerWeaponHandling() {

        if (!player.hasWeapon() && playerInfo.isPressedGrab() && readyToGrabWeapon)
            player.setWantsToGrabWeapon(true);
        else if (!player.hasWeapon() && !playerInfo.isPressedGrab()) {
            player.setWantsToGrabWeapon(false);
            readyToGrabWeapon = true;
        } else if (player.hasWeapon() && readyToDropWeapon && playerInfo.isPressedGrab()) {
            player.dropWeapon();
            readyToDropWeapon = false;
            readyToGrabWeapon = false;
        } else if (player.hasWeapon() && !playerInfo.isPressedGrab())
            readyToDropWeapon = true;

    }

    private void playerShooting() {

        if (player.hasWeapon() && readyToShoot && playerInfo.isPressedShoot()) {
            player.shoot();
            readyToShoot = false;
        } else if (player.hasWeapon() && !playerInfo.isPressedShoot()) {
            readyToShoot = true;
        }
    }

    private void playerModelUpdates() {

        player.setPlayerGraphic();
        player.movePlayerModel();

    }

    public boolean isPlayerDead() {
        return player.isDead();
    }

}
