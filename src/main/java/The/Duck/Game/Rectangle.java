package The.Duck.Game;

import javafx.scene.layout.Region;

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

    private final double width;
    private final double height;


    public Rectangle(Region region) {
        x1 = region.getLayoutX();
        y1 = region.getLayoutY();

        width = region.getPrefWidth();
        height = region.getPrefHeight();

        x2 = x1 + width;
        y2 = y1 + height;
    }

    public boolean intersects(Rectangle r) {

        if (x1 > r.x2 || r.x1 > x2)
            return false;

        if (y2 < r.y1 || r.y2 < y1)
            return false;

        return true;
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
        x2 = x1 + width;
    }

    public void setY(double newY) {
        y1 = newY;
        y2 = y1 + height;
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
        return width;
    }

    public double getHeight() {
        return height;
    }

}
