package The.Duck.Game;

import The.Duck.Game.GameManagers.BoardConstants;
import The.Duck.Game.Player.Player;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PlayerTest {

    private static final List<String> player1StyleClass = Arrays.asList(
            "soldier-jump-right", "soldier-jump-left", "soldier-walk-1-right",
            "soldier-walk-2-right", "soldier-walk-1-left", "soldier-walk-2-left",
            "soldier-stand", "soldier-dead");

    private static final List<Node> playerHealth = Arrays.asList(
            new Region(), new Region(), new Region()
    );


    private static final double PLAYER_WIDTH = 80;
    private static final double PLAYER_HEIGHT = 110;

    private static final double MAX_SPEED = 15;
    private static final double ACC_VAL = 3;
    private static final double SLOW_VAL = 0.5;

    private static final double MAX_VERT_SPEED = 20;
    private static final double FALL_ACC = 1.15;

    public static void initConstants() {
        BoardConstants.getInstance().setBoardHeight(1000.0);
        BoardConstants.getInstance().setBoardWidth(1000.0);
    }

    public static Player getTesterPlayer(double x, double y) {
        Region testerRegion = getTesterRegion(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        return new Player(testerRegion, player1StyleClass, playerHealth);
    }

    public static Region getTesterRegion(double x, double y, double w, double h) {

        Region testerRegion = new Region();
        testerRegion.setLayoutX(x);
        testerRegion.setLayoutY(y);
        testerRegion.setPrefWidth(w);
        testerRegion.setPrefHeight(h);

        return testerRegion;
    }

    @Test
    public void PlayerHorizontalMovementTest() {

        final double PLAYER_Y_POS = 1000 - PLAYER_HEIGHT;

        initConstants();

        Player player = getTesterPlayer(0, PLAYER_Y_POS);

        /* Test fo proper coordinates init. */
        Assert.assertEquals(0, player.getRegion().getLayoutX(), 0.0);
        Assert.assertEquals(PLAYER_Y_POS, player.getRegion().getLayoutY(), 0.0);
        Assert.assertEquals(player.getRegion().getHeight(), PLAYER_HEIGHT, 0.0);
        Assert.assertEquals(player.getRegion().getWidth(), PLAYER_WIDTH, 0.0);

        /* Player right movement check. */
        player.accelerate(true);
        checkPlayerSlowingDown(player, ACC_VAL, true);

        for (int i = 0; i < 8; i++)
            player.accelerate(true);

        /* Check for max speed. */
        checkPlayerSlowingDown(player, MAX_SPEED, true);

        /* Check for left movement. */
        player.accelerate(false);
        checkPlayerSlowingDown(player, -ACC_VAL, true);

        /* Check for max speed left. */
        player.setLayoutX(60);
        for (int i = 0; i < 8; i++)
            player.accelerate(false);

        checkPlayerSlowingDown(player, -MAX_SPEED, true);


    }

    @Test
    public void PlayerVerticalMovementTest() {

        final double PLAYER_Y_POS = 1000 - PLAYER_HEIGHT;

        initConstants();

        Player player = getTesterPlayer(0, PLAYER_Y_POS);

        player.jump(0);
        Assert.assertTrue(player.isPlayerJumping());
        checkPlayerSlowingDown(player, -MAX_VERT_SPEED, false);

        player.jump(-4.0);
        Assert.assertTrue(player.isPlayerJumping());
        checkPlayerSlowingDown(player, -4.0, false);

        player.jump(-20);
        Assert.assertTrue(player.isPlayerJumping());
        checkPlayerSlowingDown(player, -20, false);

    }

    @Test
    public void PlayerFallingTest() {

        final double PLAYER_Y_POS = 1000 - PLAYER_HEIGHT - 60;

        initConstants();

        Player player = getTesterPlayer(0, PLAYER_Y_POS);

        for (int i = 0; i < 40; i++)
            player.movePlayerModel();

        Assert.assertEquals(1000 - PLAYER_HEIGHT, player.getRegion().getLayoutY(), 0.0);
        Assert.assertFalse(player.isPlayerJumping());
    }

    private void checkPlayerSlowingDown(Player player, double startS, boolean horizontal) {

        Assert.assertEquals(startS, horizontal ? player.getHorizontalSpeed() : player.getVerticalSpeed(), 0);

        final double PLAYER_X = player.getRegion().getLayoutX();
        final double PLAYER_Y = player.getRegion().getLayoutY();

        double add_s = startS > 0 ? SLOW_VAL : -SLOW_VAL;
        double slow = horizontal ? SLOW_VAL : FALL_ACC;

        for (double p_s = startS - slow, p_p = startS; p_s > 0; p_p += p_s, p_s -= add_s) {
            player.movePlayerModel();
            Assert.assertEquals(p_s, horizontal ? player.getHorizontalSpeed() : player.getVerticalSpeed(), 0.0);
            Assert.assertEquals(p_p, player.getRegion().getLayoutX(), 0.0);
        }

        player.resetHorizontalSpeed();
        player.resetVerticalSpeed();
        player.setLayoutY(PLAYER_Y);
        player.setLayoutX(PLAYER_X);
        player.movePlayerModel();       // Needed for inner model resetting.


    }
}

