
/**
 * @author Sion Gang
 * May 20th, 2022
 * @version 3.0
 * Time: 15 minutes
 * implement Collision detection
 */


import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EscapeLibrary extends ScenarioRoom {

    /**
     * Constructor for EscapeLibrary.
     * @param stage the primary stage for this application. Passed by reference.
     */
    public EscapeLibrary(Stage stage) {
        super(stage);
    }

    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/LibraryBg.png");

        // set scene
        Group root = new Group(image);
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


        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");

        buildCharacter(root, 100, 750, 395);
    }
}
