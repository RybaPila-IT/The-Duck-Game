package FXMLControlers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;

public class MainMenuController {

    private static final String fontPath = "src/main/resources/graphics/kenvector_future_thin.ttf";
    private static final int CreditsBegY = -800;
    private static final int CreditsDestY = 884;

    @FXML
    private Button NewGameB;
    @FXML
    private Button QuitB;
    @FXML
    private Button CreditsB;

    @FXML
    private AnchorPane CreditsPane;
    private boolean slidedIn = false;

    @FXML
    private Button CreatedByButton;

    @FXML
    private AnchorPane MainPane;

    private void setButtonFont(Button button) {

        try {

            button.setFont(Font.loadFont(new FileInputStream(fontPath), 23));

        } catch (IOException e) {

            System.err.println("Unable to load a custom font.");
            button.setFont(Font.font("Verdana", 23));

        }

    }

    private void initializeCreditsPane() {

    }


    @FXML
    public void initialize() {

        setButtonFont(QuitB);
        setButtonFont(NewGameB);
        setButtonFont(CreditsB);
        setButtonFont(CreatedByButton);

        CreditsB.setOnMouseClicked(value -> moveCreditsPaneIn());


    }

    public Button getNewGameButton() {
        return NewGameB;
    }

    public Button getQuitButton() {
        return QuitB;
    }

    public Button getCreditsButton() {
        return CreditsB;
    }

    public void moveCreditsPaneIn() {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.7));
        transition.setNode(CreditsPane);
        transition.setToY(slidedIn ? CreditsBegY : CreditsDestY);
        slidedIn = !slidedIn;
        transition.play();

    }


}
