/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * EscapeClassroom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 */

/**
 * @author Aaron Zhu
 * May 29th, 2022
 * @version 3.0
 * Time: 30 minutes
 * build the room with collision
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EscapeClassroom extends ScenarioRoom {

    /**
     * Constructor for EscapeClassroom.
     * @param stage the primary stage for this application. Passed by reference.
     * @param backpack the backpack of the character. Passed by reference.
     */
    public EscapeClassroom(Stage stage, Backpack backpack) {
        super(stage, backpack);
    }

    /**
     * builds the classroom
     */
    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/ClassBg.png");

        // set scene
        Group root = new Group(image);
        scene = new Scene(root);

        // room collisions
        fillCollisionGrid(360, 203, 490, 315);
        fillCollisionGrid(590, 203, 720, 315);
        fillCollisionGrid(105, 477, 234, 585);
        fillCollisionGrid(348, 477, 477, 585);
        fillCollisionGrid(53, 196, 230, 305); // teacher
        fillCollisionGrid(222, 203, 230, 308);
        fillCollisionGrid(53, 203, 60, 308);
        fillCollisionGrid(108, 160, 175, 200); // chair
        fillCollisionGrid(743, 343, 800, 483); // door

        // borders collisions
        fillCollisionGrid(0, 0, 800, 100);
        fillCollisionGrid(0, 0, 8, 600);
        fillCollisionGrid(0, 592, 800, 600);
        fillCollisionGrid(792, 0, 800, 600);

        backpack.buildBackpack(root);

        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");

        buildCharacter(root, 100, 750, 395);
    }
}
