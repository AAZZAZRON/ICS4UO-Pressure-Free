/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Main.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron):
 * - Starts the game
 * - Maintains starting game logic (splash, main menu)
 *
 * Version 3 (Aaron):
 * - make splash screen last one repeat
 */

/**
 * @author Aaron Zhu
 * May 16th, 2022
 * @version 1.0
 * Time: 10 minutes
 * create the setup of the game GUI, including stage title, size, company logo
 */

/**
 * @author Aaron Zhu
 * May 16th, 2022
 * @version 1.0
 * Time: 10 minutes
 * Call screenSetup()
 */

/**
 * @author Aaron Zhu
 * May 28th, 2022
 * @version 3.0
 * Time: 1 hour
 * Splash screen does not loop (using PauseTransition)
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * The main method, entry point for all JavaFX applications.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overrides the start method of the Application class.
     * Overall game flow logic.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        // set up screen
        Tools.screenSetup(stage);
        stage.show();
        // ChangeScene.changeToMainMenu();

        // show splash screen once
        ChangeScene.changeToSplashScreen();
    }
}
