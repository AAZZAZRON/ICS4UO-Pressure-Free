/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Main.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - Starts the game
 * - Maintains starting game logic (splash, main menu)
 */

import javafx.application.Application;
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
     * Overall game flow logic.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        GameSetup game = new GameSetup(stage);
        SplashScreen splash = new SplashScreen(stage);
        MainMenu mainMenu = new MainMenu(stage);
        stage.show();
        game.screenSetup();
        splash.splashScreen();
        mainMenu.mainMenu();
    }
}
