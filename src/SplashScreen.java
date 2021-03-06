/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * SplashScreen.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 4.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Sion):
 * - does nothing
 *
 * Version 3 (Aaron):
 * - splash screen displays for 5 seconds
 */

/**
 * @author Sion Gang
 * May 16th, 2022
 * @version 1.0
 * Time: 10 minutes
 * Create template for splash screen
 */

/**
 * @author Aaron Zhu
 * May 30th, 2022
 * @version 3.0
 * Time: 30 minutes
 * - play a gif of the splash screen for some amount of time
 */

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    /**
     * Constructor for SplashScreen.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public SplashScreen(Stage stage) {
        this.stage = stage;
    }

    /**
     * sets up the GUI for the splash screen.
     */
    public void splashScreen() {
        ImageView logo = new ImageView(new Image("Assets/logo.gif"));
        logo.setPreserveRatio(true);
        logo.setFitWidth(400);
        logo.setX(200);
        logo.setY(100);

        // set scene
        Group root = new Group();
        Scene scene = new Scene(root, Color.rgb(252, 252, 255));
        root.getChildren().add(logo);
        stage.setScene(scene);
        PauseTransition delay = new PauseTransition(Duration.seconds(5)); // allow splash screen to play
        delay.setOnFinished(e -> ChangeScene.changeToMainMenu());
        delay.play();
    }
}
