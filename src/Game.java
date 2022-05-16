/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Game.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 */

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game {
    /** main menu object */
    MainMenu mainMenu;

    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    /**
     * Constructor for Game. Initializes a splash screen and main menu.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public Game(Stage stage) {
        mainMenu = new MainMenu(stage);
        this.stage = stage;
    }

    /**
     * Sets up the screen for the game.
     * Screen dimensions, logo, title, etc.
     */
    public void screenSetup() {
        stage.setTitle("Parallel Studios - Pressure-Free");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setResizable(false);

        // add logo
        Image logo = new Image("Assets/logo.png");
        stage.getIcons().add(logo);
    }

    /**
     * Shows the splash screen.
     */
    public void splashScreen() {
        System.out.println("Splash Screen");
    }

    /**
     * Shows the main menu.
     */
    public void play() {
        mainMenu.mainMenu();
    }
}
