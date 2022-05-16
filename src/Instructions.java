/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Instructions.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Instructions {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    /**
     * Constructor for MainMenu.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public Instructions(Stage stage) {
        this.stage = stage;
    }

    /**
     * sets up the GUI for the instructions screen.
     */
    public void instructions() {
        ImageView image = new ImageView("Assets/MainMenu/Instructions.png");
        image.setPreserveRatio(true);
        image.setFitWidth(786);

        stage.setScene(new Scene(new Group(image)));
    }
}
