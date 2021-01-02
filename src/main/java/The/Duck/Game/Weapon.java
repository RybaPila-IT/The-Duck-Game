package The.Duck.Game;

import FXMLControlers.WeaponController;
import javafx.scene.layout.Region;

public class Weapon extends BoardObject {

    private static final int SHOTS_AMOUNT = 400;

    private final WeaponController controller;

    private boolean isWeaponFacingRight;
    private int shots;
    private Player owner;

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


    public Weapon(Region weaponCharacter) {
        super(new Rectangle(weaponCharacter));
        this.controller = new WeaponController(weaponCharacter);
        this.shots = SHOTS_AMOUNT;
        this.owner = null;
        this.isWeaponFacingRight = true;
    }

    public void shoot() {

        if (shots > 0) {
            BoardElements.getInstance().addBoardObject(new Bullet(region, isWeaponFacingRight));
            shots--;
        }

    }

    private void setOwner(Player owner) {
        this.owner = owner;
    }

    public void fallDown() {
        setOwner(null);
        region.setY(region.getLayoutY() + 65);
        controller.setLayoutY(region.getLayoutY());
    }


    @Override
    public void onTic() {
        if (hasOwner())
            followOwner();
    }

    @Override
    public void onPlayerCollision(Player player) {

        if (player.wantsToGrabWeapon() && !hasOwner()) {
            setOwner(player);
            player.setWeapon(this);
        }

    }

    @Override
    public boolean isObjectValid() {
        return true;
    }

    public boolean equals(Object obj) {

        if (obj instanceof Weapon) {
            Weapon o = (Weapon) obj;
            return region.equals(o.region) && shots == o.shots &&
                    isWeaponFacingRight == o.isWeaponFacingRight;
        }

        return false;
    }
}
