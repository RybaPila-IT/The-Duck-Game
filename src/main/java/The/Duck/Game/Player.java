package The.Duck.Game;


import javafx.scene.layout.Region;

public class Player {

    private static final double maxSpeed = 0.25;
    private static final double accVal = 0.002;
    private static final double slowVal = 0.0003;
    private static final double horMaxSize = 1360.0;


    private Region playerModel;
    private double horSpeed;
    private double verticalSpeed;

    public Player(Region playerModel) {
        this.playerModel = playerModel;
        this.horSpeed = 0;
        this.verticalSpeed = 0;
    }

    public void accelerate(boolean toRight) {
        horSpeed = toRight ? Math.min(maxSpeed, horSpeed + accVal) : Math.max(-maxSpeed, horSpeed - accVal);
    }

    public void slowDown() {

        if (horSpeed != 0)
            horSpeed += (horSpeed > 0 ? -slowVal : slowVal);

    }

    public void movePlayerModel() {

        double newXPos = playerModel.getLayoutX() + horSpeed;

        if (horSpeed > 0)
            playerModel.setLayoutX(Math.min(newXPos, horMaxSize));
        else
            playerModel.setLayoutX(Math.max(newXPos, 0));
    }


}
