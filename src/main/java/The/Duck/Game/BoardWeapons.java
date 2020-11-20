package The.Duck.Game;

import java.util.ArrayList;
import java.util.List;

public class BoardWeapons {

    private List<Weapon> weapons;
    private static BoardWeapons instance;

    private BoardWeapons() {
        weapons = new ArrayList<>();
    }

    public static BoardWeapons getInstance() {

        if (instance == null)
            instance = new BoardWeapons();

        return instance;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public Weapon findCollision(Rectangle region) {

        Weapon toRet = null;

        for (Weapon w : instance.weapons)
            if (w.getArea().intersects(region))
                toRet = w;

        return toRet;
    }


}
