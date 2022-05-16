/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Main.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * The main method, entry point for all JavaFX applications.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overrides the start method of the Application class.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        Game game = new Game(stage);
        stage.show();
        game.screenSetup();
        game.splashScreen();
        game.play();
    }
}
