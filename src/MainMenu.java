/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * MainMenu.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - set up main menu with buttons
 * - set up incomplete background image
 * - set up button isntructions and exit onClick events
 *
 * Version 1 (Sion)
 * - set up play button onClick event
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

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        ImageView image = Tools.createBackgroundImage("MainMenu/MainMenu.png");

        // set scene
        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // add buttons
        ImageView playBtn = Tools.createButton(root, "MainMenu/Buttons/", "play", 150, 150, 500);
        ImageView insBtn = Tools.createButton(root, "MainMenu/Buttons/", "ins", 150, 275, 500);
        ImageView exitBtn = Tools.createButton(root, "MainMenu/Buttons/", "exit", 150, 400, 500);

        // add onclick
        playBtn.setOnMouseClicked(e -> {
            System.out.println("Play");
            // loads deficiency room
           // DeficiencyRoom deficiencyRoom = new DeficiencyRoom(stage);

            ChangeScene.changeToPanicRoom(stage);
            //ChangeScene.changeToSchool(stage, 0);
        });

        insBtn.setOnMouseClicked(e -> {
            System.out.println("Instructions");
            ChangeScene.changeToInstructions(stage);
        });

        exitBtn.setOnMouseClicked(e -> {
            System.out.println("Exit");
            Platform.exit();
        });




//        Character character = new Character(stage, 100, new int[600][800]);
//        character.build(root, scene);
    }
}
