package The.Duck.Game;

import javafx.scene.Node;

public class Obstacle {

    private Node obstacleRegion;

    public Obstacle(Node region) {
        obstacleRegion = region;
    }

    public boolean intersects(Node newRegion) {
        return obstacleRegion.getBoundsInParent().intersects(newRegion.getBoundsInParent());
    }

    public Node getObstacleRegion() {
        return obstacleRegion;
    }


}
