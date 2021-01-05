package The.Duck.Game;

import java.util.Random;

public class WeaponRespawn {

    private static final String WEAPON_STYLE_1 = "weapon_left";
    private static final String WEAPON_STYLE_2 = "weapon_right";
    private static final double WEAPON_WIDTH = 70;
    private static final double WEAPON_HEIGHT = 47;

    private Random random;
    private int weapons;
    private double[] spawns;

    public WeaponRespawn(double[] s) {
        this.random = new Random();
        this.weapons = 0;
        this.spawns = s;
    }

    private int getSpawnPointIdx() {
        return random.nextInt((spawns.length / 2));
    }

    private void createNewWeapon(int idx) {
        Rectangle weaponRectangle = new Rectangle(spawns[idx * 2], spawns[idx * 2 + 1], WEAPON_WIDTH, WEAPON_HEIGHT);
        String style = Math.random() < 0.5 ? WEAPON_STYLE_1 : WEAPON_STYLE_2;
        Weapon weapon = new Weapon(BoardConstants.getController().createNewRegion(weaponRectangle, style));
        BoardElements.getInstance().addBoardObject(weapon);
    }

    public void spawnWeapon() {

        if (weapons > 0) {
            int idx = getSpawnPointIdx();
            createNewWeapon(idx);
            weapons--;
        }
    }

    public void weaponNotValid() {
        weapons++;
    }


}
