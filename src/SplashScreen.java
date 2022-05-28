import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * SplashScreen.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Sion)
 * - does nothing
 */

/**
 * @author Sion Gang
 * May 16th, 2022
 * @version 1.0
 * Time: 10 minutes
 * Create template for splash screen
 */

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
        ImageView logo = new ImageView(new Image("Assets/LogoAnimated.gif"));
        logo.setPreserveRatio(true);
        logo.setFitWidth(400);
        logo.setX(200);
        logo.setY(100);

        // set scene
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(logo);
        stage.setScene(scene);
    }
}
