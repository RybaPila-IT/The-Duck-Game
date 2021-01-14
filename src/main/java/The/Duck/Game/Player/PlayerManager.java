package The.Duck.Game.Player;

import The.Duck.Game.GameManagers.ButtonInfo;

/**
 * Class responsible for managing player movement.
 *
 * <p>
 * This class could be considered as the interpreter of
 * user input for the player.
 * It performs checks required for reasonable weapon handling
 * and shooting.
 * Lastly it orders player to perform movement actions.
 * </p>
 */
public class PlayerManager {

    private boolean readyToGrabWeapon;
    private boolean readyToDropWeapon;
    private boolean readyToShoot;

    private final Player player;
    private final ButtonInfo playerInfo;

    /**
     * Constructor of the PlayerManager class.
     *
     * @param player     Player which will be managed.
     * @param playerInfo Information about buttons bound with player.
     */
    public PlayerManager(Player player, ButtonInfo playerInfo) {

        this.playerInfo = playerInfo;
        this.player = player;
        this.readyToGrabWeapon = true;
        this.readyToDropWeapon = false;
        this.readyToShoot = true;
    }

    /**
     * Procedure managing the player.
     *
     * <p>
     * This procedure orders player to perform certain actions
     * regarding to the user input. The only situation when
     * this actions will be omitted is when player is dead.
     * </p>
     */
    public void movePlayer() {

        if (!isPlayerDead()) {
            playerMovement();
            playerWeaponHandling();
            playerShooting();
            playerExtraActions();
        }

        playerModelUpdates();
    }

    public boolean isPlayerDead() {
        return player.isDead();
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

}
