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

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EscapeClassroom extends ScenarioRoom {

    /**
     * Constructor for EscapeClassroom.
     * @param stage the primary stage for this application. Passed by reference.
     */
    public EscapeClassroom(Stage stage) {
        super(stage);
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
        fillCollisionGrid(0, 0, 800, 160);
        fillCollisionGrid(0, 0, 1, 600);
        fillCollisionGrid(0, 599, 800, 600);
        fillCollisionGrid(799, 0, 800, 600);

        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");
        warningShown = false;

        buildCharacter(root, 100, 750, 395);
    }
}
