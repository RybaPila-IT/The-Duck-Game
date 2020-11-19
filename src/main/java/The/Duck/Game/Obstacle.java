package The.Duck.Game;

import javafx.scene.Node;

import javafx.scene.layout.Region;

public class Obstacle {

    private Node nodeRegion;
    private Rectangle obstacleRegion;

    public Obstacle(Node node, Region region) {
        nodeRegion = node;
        obstacleRegion = new Rectangle(region);
    }

    public boolean intersects(Rectangle region) {
        return obstacleRegion.intersects(region);
    }

    public boolean intersects(Node region) {
        return nodeRegion.getBoundsInParent().intersects(region.getBoundsInParent());
    }

    public Rectangle getObstacleRegion() {
        return obstacleRegion;
    }

}
