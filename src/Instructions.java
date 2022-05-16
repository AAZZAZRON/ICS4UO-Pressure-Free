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
        // https://www.tutorialspoint.com/javafx/javafx_images.htm#:~:text=You%20can%20load%20an%20image%20in%20JavaFX%20by,object%20of%20the%20image%20to%20be%20loaded%20or%2C
        ImageView image = new ImageView("Assets/MainMenu/Instructions.png");
        image.setPreserveRatio(true);
        image.setFitWidth(786);

        stage.setScene(new Scene(new Group(image)));
    }
}
