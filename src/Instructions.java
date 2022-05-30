/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Instructions.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Sion)
 * - Added incomplete instructions page
 */

/**
 * @author Sion Gang
 * May 16th, 2022
 * @version 1.0
 * Time: 5 minutes
 * create instructions(), which would display an image
 * - show that event listeners in main menu worked
 */

/**
 * @author Sion Gang
 * May 17th, 2022
 * @version 1.0
 * Time: 30 minutes
 * Design and integrate X button to exit instructions page
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
     * sets up the GUI for the instructions` screen.
     */
    public void instructions() {
        ImageView image = Tools.createBackgroundImage("Assets/MainMenu/Instructions.png");

        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        ImageView backButton = Tools.createButton(root, "Assets/Buttons/", "x", 650, 170, 40);

        backButton.setOnMouseClicked(e -> {
            ChangeScene.changeToMainMenu(stage);
        });
    }
}
