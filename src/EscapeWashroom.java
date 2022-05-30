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
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/BoysWashroomBg.png");

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
        warningShown = false;

        buildCharacter(root, 100, 750, 395);
    }

    @Override
    public void setUpAnimationTimer() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // handle movement
                if (keyPressed['w'] && !isColliding()) {
                    character.moveUp(true);
                    if (isColliding()) character.moveDown(false); // if character is colliding, revert movement
                }
                if (keyPressed['s'] && !isColliding()) {
                    character.moveDown(true);
                    if (isColliding()) character.moveUp(false);
                }
                if (keyPressed['a'] && !isColliding()) {
                    character.moveLeft(true);
                    if (isColliding()) character.moveRight(false);
                }
                if (keyPressed['d'] && !isColliding()) {
                    character.moveRight(true);
                    if (isColliding()) character.moveLeft(false);
                }

                // handle prompt
                int prompt = getPrompt();
                // toggle textbox visibility
                if (true || (!warningShown && prompt != 0)) { // warning > prompts
                    textBoxOpen = prompt; // toggle on if not already
                    //textBoxes[textBoxOpen].toggleOn();

                    if (keyPressed['e']) { // if the user presses e to enter room
                        keyPressed['e'] = false; // set the key to false
                        stop(); // stop the timer
                        ChangeScene.changeToEscapeRoomSchool(); // change to escape room school (exit room)
                    }
                } else if (textBoxOpen != 0) {
                    //textBoxes[textBoxOpen].toggleOff();
                    textBoxOpen = 0;
                }
            }
        };
    }
}
