
/**
 * @author Sion Gang
 * May 20th, 2022
 * @version 3.0
 * Time: 15 minutes
 * implement Collision detection for washroom
 */


import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EscapeWashroom extends ScenarioRoom {

    /**
     * Constructor for EscapeWashroom.
     * @param stage the primary stage for this application. Passed by reference.
     */
    public EscapeWashroom(Stage stage) {
        super(stage);
    }

    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/WashroomBg.png");

        // set scene
        Group root = new Group(image);
        scene = new Scene(root);


        // borders collisions
        fillCollisionGrid(0, 0, 800, 170);
        fillCollisionGrid(0, 0, 1, 600);
        fillCollisionGrid(0, 599, 800, 600);
        fillCollisionGrid(799, 0, 800, 600);

        fillCollisionGrid(0, 0, 523, 262); // top stalls

        fillCollisionGrid(0, 519, 521, 600); // bottom stall | bottom --> toilets
        fillCollisionGrid(84, 467,268, 600 ); // bottom 2 left stals
        fillCollisionGrid(339, 467, 358, 591); // bottom right stall wall
        fillCollisionGrid(427, 467, 522, 600); // bottom furthest right stall

        fillCollisionGrid(734, 347, 800, 481);

        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");

        buildCharacter(root, 100, 750, 395);
    }
}
