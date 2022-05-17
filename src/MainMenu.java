/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * MainMenu.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
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
        ImageView image = new ImageView("Assets/MainMenu/mainMenu.png");
        image.setPreserveRatio(true);
        image.setFitWidth(786);

        // set scene
        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // add buttons
        ImageView playBtn = HoverButton.createButton(root, "mm", 150, 150, 484);
        ImageView insBtn = HoverButton.createButton(root, "mm", 150, 275, 484);
        ImageView exitBtn = HoverButton.createButton(root, "mm", 150, 400, 484);

        // add onclick
        playBtn.setOnMouseClicked(e -> {
            System.out.println("Play");
        });

        insBtn.setOnMouseClicked(e -> {
            System.out.println("Instructions");
            Instructions instructions = new Instructions(stage);
            instructions.instructions();
        });

        exitBtn.setOnMouseClicked(e -> {
            System.out.println("Exit");
            Platform.exit();
        });

        Character character = new Character(stage);
        character.build(root, scene);
    }
}
