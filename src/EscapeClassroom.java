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

    private final Coord[] itemCoords = new Coord[]{new Coord(360, 215)};

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
        fillCollisionGrid(356, 212, 484, 320);
        fillCollisionGrid(585, 212, 712, 320);
        fillCollisionGrid(105, 480, 232, 580);
        fillCollisionGrid(344, 480, 472, 580);
        fillCollisionGrid(53, 196, 230, 305); // teacher
        fillCollisionGrid(222, 203, 230, 308);
        fillCollisionGrid(53, 203, 60, 308);
        fillCollisionGrid(108, 160, 175, 200); // chair
        fillCollisionGrid(743, 343, 800, 483); // door

        // items
        addItem(root, "Assets/School/Items/scissors.png", itemCoords[0].getX(), itemCoords[0].getY(), 1);
        textBoxes[1] = new TextBox(stage, root, scene, "tmp", "Red");

        // borders collisions
        fillCollisionGrid(0, 0, 800, 168);
        fillCollisionGrid(0, 0, 8, 600);
        fillCollisionGrid(0, 592, 800, 600);
        fillCollisionGrid(792, 0, 800, 600);

        backpack.buildBackpack(root);

        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");

        buildCharacter(root, 100, 750, 395);
    }
}
