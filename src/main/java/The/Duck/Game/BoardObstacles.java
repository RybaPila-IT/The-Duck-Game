package The.Duck.Game;

import java.util.ArrayList;
import java.util.List;

public class BoardObstacles {

    private List<Obstacle> obstacles;
    private static BoardObstacles instance;

    private BoardObstacles() {
        obstacles = new ArrayList<>();
    }

    public static BoardObstacles getInstance() {

        if (instance == null)
            instance = new BoardObstacles();

        return instance;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        instance.obstacles = obstacles;
    }

    public List<Obstacle> getObstacles() {
        return instance.obstacles;
    }

}
