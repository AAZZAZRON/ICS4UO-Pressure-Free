/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * EscapeLibrary.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 */

/**
 * @author Sion Gang
 * May 20th, 2022
 * @version 3.0
 * Time: 15 minutes
 * implement Collision detection
 */

/**
 * @author Aaron Zhu
 * June 3rd, 2022
 * @version 3.0
 * Time: 15 minutes
 * add items for user to pick up
 */


import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;

public class EscapeLibrary extends ScenarioRoom {

    /**
     * Constructor for EscapeLibrary.
     * @param stage the primary stage for this application. Passed by reference.
     * @param backpack the backpack of the character. Passed by reference.
     */
    public EscapeLibrary(Stage stage, Backpack backpack) {
        super(stage, backpack);
        itemCoords.add(new Point(373, 300));
        itemCoords.add(new Point(400, 448));
        itemCoords.add(new Point(537, 302));
        itemCoords.add(new Point(545, 448));

        itemCoords.add(new Point(148, 160)); // book points
        itemCoords.add(new Point(182, 440));
    }

    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/LibraryBg.png");

        // set scene
        root = new Group(image);
        scene = new Scene(root);


        // borders collisions
        fillCollisionGrid(0, 0, 800, 210);
        fillCollisionGrid(0, 0, 1, 600);
        fillCollisionGrid(0, 599, 800, 600);
        fillCollisionGrid(799, 0, 800, 600);

        fillCollisionGrid(0, 0, 39, 373);
        fillCollisionGrid(39, 373, 230, 486);

        fillCollisionGrid(369, 301, 604, 368);
        fillCollisionGrid(369, 445, 604, 515);

        fillCollisionGrid(734, 347, 800, 481);

        textBoxes[1] = new TextBox(stage, root, scene, "Press e to leave the library.", "Green");
        fillPromptGrid(730, 330, 800, 500, 1);

        // items
        for (int i = 0; i < itemCoords.size() - 2; i++) {
            addItem("Assets/School/Items/scissors.png", "Scissors", itemCoords.get(i).x, itemCoords.get(i).y, 30, 20, i + 2);
            textBoxes[i + 2] = new TextBox(stage, root, scene, "Press p to pick up the scissors", "Red");
        }

        for (int i = itemCoords.size() - 2; i < itemCoords.size(); i++) {
            addItem("Assets/School/Items/book.png", "Books", itemCoords.get(i).x, itemCoords.get(i).y, 14, 50, i + 2);
            textBoxes[i + 2] = new TextBox(stage, root, scene, "Press p to pick up the book", "Red");
        }


        backpack.buildBackpack(root);

        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");

        buildCharacter(100, 750, 395);
    }
}
