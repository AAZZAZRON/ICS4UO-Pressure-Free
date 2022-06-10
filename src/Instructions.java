/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Instructions.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 4.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Sion):
 * - Added incomplete instructions page
 *
 * Version 4 (Sion):
 * - created and implemented instructions page
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

/**
 * @author Sion Gang
 * June 9th, 2022
 * @version 4.0
 * Time: 1 hour
 * - redesign introduction graphics
 * - redesign buttons
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Instructions {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    /** Counter for the instruction slides */
    private static int counter;

    /**
     * Constructor for MainMenu.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public Instructions(Stage stage) {
        this.stage = stage;
        counter = 1;
    }

    /**
     * sets up the GUI for the instructions` screen.
     */
    public void instructions() {
        ImageView image = Tools.createBackgroundImage("Assets/MainMenu/MainMenuBackground.png");
        image.setOpacity(0.3);
        ImageView instructions = Tools.createBackgroundImage("Assets/Instructions/Instructions"+counter+".png");

        Group root = new Group(image);
        Text title = new Text("Instructions");
        title.setY(100);
        title.setX(50);
        title.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.REGULAR, 70));
        title.setWrappingWidth(700);
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        root.getChildren().add(instructions);
        root.getChildren().add(title);


        Scene scene = new Scene(root);
        stage.setScene(scene);

        if (counter < 2) {
            ImageView nextButton = Tools.createButton(root, "Assets/Buttons/", "next", 515, 515, 145);
            nextButton.setOnMouseClicked(e -> {
                counter++;
                instructions();
            });

        } else {
            ImageView backButton = Tools.createButton(root, "Assets/Buttons/MainMenu/", "toMainMenu", 515, 515, 145);
            backButton.setOnMouseClicked(e -> ChangeScene.changeToMainMenu());
        }
    }
}
