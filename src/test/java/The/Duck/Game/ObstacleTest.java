package The.Duck.Game;

import The.Duck.Game.BoardObjects.RegularBoardObjects.Obstacle;
import The.Duck.Game.GameManagers.BoardConstants;
import The.Duck.Game.GameManagers.BoardElements;
import The.Duck.Game.Player.Player;
import org.junit.Assert;
import org.junit.Test;

public class ObstacleTest {

    @Test
    public void horizontalRightCollision() {

        BoardConstants.getInstance().setBoardWidth(1000);
        BoardConstants.getInstance().setBoardHeight(1000);

        BoardElements.getInstance().addBoardObject(createObstacle(700, 900, 60, 40));
        Player player = PlayerTest.getTesterPlayer(619, 890);
        player.accelerate(true);

        player.movePlayerModel();

        Assert.assertEquals(620, player.getRegion().getLayoutX(), 3.0);
        Assert.assertEquals(0, player.getHorizontalSpeed(), 0.0);
    }

    @Test
    public void horizontalLeftCollision() {

        BoardConstants.getInstance().setBoardWidth(1000);
        BoardConstants.getInstance().setBoardHeight(1000);

        BoardElements.getInstance().addBoardObject(createObstacle(700, 900, 60, 20));
        Player player = PlayerTest.getTesterPlayer(762, 890);
        player.accelerate(false);

        player.movePlayerModel();

        Assert.assertEquals(760, player.getRegion().getLayoutX(), 3.0);
        Assert.assertEquals(0, player.getHorizontalSpeed(), 0.0);

    }

    @Test
    public void verticalUpperCollision() {

        BoardConstants.getInstance().setBoardWidth(2000);
        BoardConstants.getInstance().setBoardHeight(1000);

        BoardElements.getInstance().addBoardObject(createObstacle(1400, 860, 80, 20));
        Player player = PlayerTest.getTesterPlayer(1400, 890);
        player.jump(0);
        player.movePlayerModel();

        Assert.assertEquals(880, player.getRegion().getLayoutY(), 3.0);
        Assert.assertTrue(player.isPlayerJumping());
        Assert.assertEquals(1, player.getVerticalSpeed(), 1);

    }

    @Test
    public void verticalDownCollision() {

        BoardConstants.getInstance().setBoardWidth(2000);
        BoardConstants.getInstance().setBoardHeight(1000);

        BoardElements.getInstance().addBoardObject(createObstacle(1400, 860, 60, 20));

        Player player = PlayerTest.getTesterPlayer(1400, 753);
        player.movePlayerModel();
        player.movePlayerModel();

        Assert.assertEquals(750, player.getRegion().getLayoutY(), 0.0);
    }

    private Obstacle createObstacle(double x, double y, double w, double h) {
        return new Obstacle(PlayerTest.getTesterRegion(x, y, w, h));
    }

}
