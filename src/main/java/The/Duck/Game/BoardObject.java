package The.Duck.Game;

public abstract class BoardObject implements TickResponsive, PlayerCollisionSensitive {

    protected final Rectangle region;

    protected BoardObject(Rectangle r) {
        this.region = r;
    }

    public boolean intersects(Rectangle r) {
        return region.intersects(r);
    }

    public boolean isTransparent() {
        return true;
    }

    public abstract void onTic();

    public abstract void onPlayerCollision(Player player);

    public abstract boolean isValid();
}
