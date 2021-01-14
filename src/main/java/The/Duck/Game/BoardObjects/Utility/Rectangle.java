package The.Duck.Game.BoardObjects.Utility;

import javafx.scene.layout.Region;

/**
 * Most inner class representing the area of the BoardObject.
 */
public class Rectangle {

    /**
     * Coordinates of upper left corner.
     */
    private double x1;
    private double y1;
    /**
     * Coordinates of lower right corner.
     */
    private double x2;
    private double y2;

    private final double w;
    private final double h;


    public Rectangle(Region region) {

        x1 = region.getLayoutX();
        y1 = region.getLayoutY();

        w = region.getPrefWidth();
        h = region.getPrefHeight();

        x2 = x1 + w;
        y2 = y1 + h;
    }

    public Rectangle(Rectangle rectangle) {

        this.y1 = rectangle.y1;
        this.y2 = rectangle.y2;
        this.x1 = rectangle.x1;
        this.x2 = rectangle.x2;
        this.h = rectangle.h;
        this.w = rectangle.w;
    }

    public Rectangle(double x1, double y1, double w, double h) {

        this.y1 = y1;
        this.x1 = x1;

        this.w = w;
        this.h = h;

        x2 = x1 + w;
        y2 = y1 + h;
    }

    /**
     * Information whether two rectangles intersect.
     *
     * @param r Rectangle with which we are checking the intersection.
     * @return True if rectangles do intersect; False otherwise.
     */
    public boolean intersects(Rectangle r) {

        if (x1 > r.x2 || r.x1 > x2)
            return false;

        return !(y2 < r.y1) && !(r.y2 < y1);
    }

    public void addHorizontally(double toAdd) {

        x1 += toAdd;
        x2 += toAdd;
    }

    public void addVertically(double toAdd) {

        y1 += toAdd;
        y2 += toAdd;
    }

    public void setX(double newX) {

        x1 = newX;
        x2 = x1 + w;
    }

    public void setY(double newY) {

        y1 = newY;
        y2 = y1 + h;
    }

    public void setSecondX(double newX) {

        x2 = newX;
        x1 = x2 - w;
    }

    public void setSecondY(double newY) {

        y2 = newY;
        y1 = y2 - h;
    }

    public double getLayoutX() {
        return x1;
    }

    public double getLayoutY() {
        return y1;
    }

    public double getSecondX() {
        return x2;
    }

    public double getSecondY() {
        return y2;
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

}
