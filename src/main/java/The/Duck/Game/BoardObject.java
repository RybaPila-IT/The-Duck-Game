package The.Duck.Game;

public abstract class BoardObject {

    protected final Rectangle region;

    protected BoardObject(Rectangle r) {
        this.region = r;
    }

    public boolean intersects(Rectangle r) {
        return region.intersects(r);
    }

    public boolean isBulletProof() {
        return false;
    }

    public double getLayoutX() {
        return region.getLayoutX();
    }

    public double getSecondX() {
        return region.getSecondX();
    }

    public abstract void onTic();

    public abstract void onPlayerCollision(Player player);

    public abstract boolean isObjectValid();
}
