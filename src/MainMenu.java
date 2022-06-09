/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * MainMenu.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - set up main menu with buttons
 * - set up incomplete background image
 * - set up button isntructions and exit onClick events
 *
 * Version 1 (Sion)
 * - set up play button onClick event
 *
 * Version 3 (Aaron, Sion)
 * - organize assets
 */

/**
 * @author Aaron Zhu
 * May 16th, 2022
 * @version 1.0
 * Time: 30 minutes
 * createButton() was a template to make a button
 * mainMenu() added 3 buttons to the main menu
 * - on click, buttons would print what they do
 */

/**
 * @author Aaron Zhu
 * May 17th, 2022
 * @version 1.0
 * Time: 15 minutes
 * fix buttons so that they do not have a border around them
 * - use ImageView instead of Button
 */

/**
 * @author Aaron Zhu, Sion Gang
 * May 30th, 2022
 * @version 3.0
 * Time: 15 minutes
 * refactor assets to be more organized
 */

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenu {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    /**
     * Constructor for MainMenu.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    /**
     * sets up the GUI for the main menu.
     * sets up main menu logic flow
     */
    public void mainMenu() {
        ImageView image = Tools.createBackgroundImage("Assets/MainMenu/MainMenuBackground.png");
        image.setOpacity(0.5);

        // set scene
        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // add buttons
        ImageView playBtn = Tools.createButton(root, "Assets/Buttons/MainMenu/", "play", 150, 150, 500);
        ImageView insBtn = Tools.createButton(root, "Assets/Buttons/MainMenu/", "ins", 150, 275, 500);
        ImageView exitBtn = Tools.createButton(root, "Assets/Buttons/MainMenu/", "exit", 150, 400, 500);

        // add onclick
        playBtn.setOnMouseClicked(e -> {
            ChangeScene.reinitialize();
            ChangeScene.changeToDeficiencyRoom(true);
            ChangeScene.changeToPanicRoom();
            ChangeScene.changeToEscapeRoom(10);
        });

        insBtn.setOnMouseClicked(e -> {
            ChangeScene.changeToInstructions();
        });

        exitBtn.setOnMouseClicked(e -> {
            Scene next = Tools.displayIntermissionText(stage, "Thank you for playing Pressure-Free!");
            next.onMouseClickedProperty().set(e2 -> {
                Platform.exit();
                System.exit(0);
            });
        });
    }
}
