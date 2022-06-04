/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * EscapeWashroom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 3 (Sion)
 * - build room with collision detection
 */

/**
 * @author Sion Gang
 * May 20th, 2022
 * @version 3.0
 * Time: 15 minutes
 * implement Collision detection for washroom
 */


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EscapeWashroom extends ScenarioRoom {

    /**
     * Constructor for EscapeWashroom.
     * @param stage the primary stage for this application. Passed by reference.
     * @param backpack the backpack of the character. Passed by reference.
     * @param roomName the name of the room.
     */
    public EscapeWashroom(Stage stage, Backpack backpack, String roomName) {
        super(stage, backpack, roomName);
    }

    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/WashroomBg.png");

        // set scene
        root = new Group(image);
        scene = new Scene(root);


        // borders collisions
        fillCollisionGrid(0, 0, 800, 170);
        fillCollisionGrid(0, 0, 8, 600);
        fillCollisionGrid(0, 592, 800, 600);
        fillCollisionGrid(792, 0, 800, 600);

        fillCollisionGrid(0, 0, 523, 262); // top stalls

        fillCollisionGrid(0, 519, 521, 600); // bottom stall | bottom --> toilets
        fillCollisionGrid(84, 467,268, 600 ); // bottom 2 left stals
        fillCollisionGrid(339, 467, 358, 591); // bottom right stall wall
        fillCollisionGrid(427, 467, 522, 600); // bottom furthest right stall

        fillCollisionGrid(734, 347, 800, 481);

        textBoxes[1] = new TextBox(stage, root, scene, "Press e to leave the washroom.", "Green");
        fillPromptGrid(730, 330, 800, 500, 1);

        buildCharacter(100, 750, 395);

        backpack.buildBackpack(root);

        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");

    }
}
