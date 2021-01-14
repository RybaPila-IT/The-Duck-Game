package The.Duck.Game.BoardObjects.RegularBoardObjects;

import FXMLControlers.BoardObjectsControllers.WeaponController;
import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.GameManagers.BoardConstants;
import The.Duck.Game.GameManagers.BoardElements;
import The.Duck.Game.Player.Player;
import javafx.scene.layout.Region;

import java.util.List;

public class Weapon extends BoardObject {

    private static final int SHOTS_AMOUNT = 4;

    private final WeaponController controller;

    private Player owner;
    private boolean isWeaponFacingRight;
    private int shots;

    /**
     * Weapon constructor.
     *
     * @param weaponCharacter JavaFX Region representing the area of the weapon.
     */
    public Weapon(Region weaponCharacter) {
        super(new Rectangle(weaponCharacter));
        this.controller = new WeaponController(weaponCharacter);
        this.shots = SHOTS_AMOUNT;
        this.owner = null;
        this.isWeaponFacingRight = true;
    }

    public void shoot() {

        if (shots > 0 && noCollision()) {
            BoardElements.getInstance().addBoardObject(new Bullet(region, isWeaponFacingRight));
            shots--;
        }

    }

    /**
     * Procedure will set weapon layout simulating dropping it
     * by the player.
     */
    public void fallDown() {
        setOwner(null);
        region.setY(region.getLayoutY() + 65);
        controller.setLayoutY(region.getLayoutY());
    }


    /**
     * Procedure managing weapon during board tic.
     *
     * <p>
     * Weapon will "follow" the owner if it has one.
     * If it does not have the owner it will
     * remove itself from scene if it is no longer valid.
     * </p>
     */
    @Override
    public void onTic() {

        if (hasOwner())
            followOwner();
        else if (!isValid()) {
            controller.remove();
            BoardConstants.getInstance().getWeaponRespawn().weaponNotValid();
        }
    }

    /**
     * Procedure will set owner of the weapon to player
     * if player wants to grab it.
     *
     * @param player Player which has collided with the weapon.
     */
    @Override
    public void onPlayerCollision(Player player) {

        if (player.wantsToGrabWeapon() && !hasOwner()) {
            setOwner(player);
            player.setWeapon(this);
        }

    }

    /**
     * Weapon is valid until it has owner or has shoots left.
     *
     * @return True if weapon is valid; False otherwise.
     */
    @Override
    public boolean isValid() {
        return shots > 0 || hasOwner();
    }

    private void setOwner(Player owner) {
        this.owner = owner;
    }

    private boolean hasOwner() {
        return owner != null;
    }

    private void followOwner() {

        if (owner.isPlayerFacedRight())
            region.setX(owner.getRegion().getSecondX() - 30);
        else
            region.setX(owner.getRegion().getLayoutX() - 35);

        if (owner.isPlayerJumping())
            region.setY(owner.getRegion().getLayoutY() - 45);
        else
            region.setY(owner.getRegion().getLayoutY());

        isWeaponFacingRight = owner.isPlayerFacedRight();

        controller.setFacingRightGraphic(isWeaponFacingRight);
        controller.setLayoutX(region.getLayoutX());
        controller.setLayoutY(region.getSecondY());

    }

    private boolean noCollision() {

        List<BoardObject> collided = BoardElements.getInstance().collidedWith(region);

        for (BoardObject c : collided)
            if (!c.doesNotReact())
                return false;

        return true;
    }
}
