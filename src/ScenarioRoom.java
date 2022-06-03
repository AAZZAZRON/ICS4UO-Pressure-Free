/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * ScenarioRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 3 (Aaron)
 * - extended CollisionRoom to create an abstract class for rooms in the school (escape level)
 * - adds items to the scene & allows them to be picked up
 */

/**
 * @author Aaron Zhu
 * May 30th, 2022
 * @version 3.0
 * Time: 30 minutes
 * Create ScanarioRoom.java
 * - extends CollisionRoom
 * - used for the rooms in the escape room that all have the same functions
 * Functions to be added:
 * - peer pressure scenario
 * - ability to pick up items from the rooms
 */

/**
 * @author Aaron Zhu
 * May 31st, 2022
 * @version 3.0
 * Time: 30 minutes
 * Make character restart at room entrance on load
 */

/**
 * @author Aaron Zhu
 * June 3rd, 2022
 * @version 3.0
 * Time: 1 hour
 * adding items for user to pick up
 * addItem() adds the item
 * removeItem() removes the item
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ScenarioRoom extends CollisionRoom {

    /** warning textbox if user tries to do a move that is not allowed */
    public TextBox warning;

    /** backpack of the character */
    public Backpack backpack;

    /** each index points to an item, used to remove items */
    public ArrayList<ImageView> items;

    /** stores where items can be placed */
    public ArrayList<Point> itemCoords;

    /** if the character has not left the door */
    private boolean firstTimeAtDoor = true;

    /**
     * Constructor for ScenarioRoom.
     *
     * @param stage the primary stage for this application. Passed by reference.
     */
    public ScenarioRoom(Stage stage, Backpack backpack) {
        super(stage);
        this.backpack = backpack;
        this.textBoxes = new TextBox[10];
        this.items = new ArrayList<>();
        this.itemCoords = new ArrayList<>();
    }

    /**
     * sets up the animation timer for scenario rooms
     */
    @Override
    public void setUpAnimationTimer() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // handle prompt
                int prompt = getPrompt();
                // toggle textbox visibility
                if (prompt != 1 && firstTimeAtDoor) {
                    firstTimeAtDoor = false;
                }
                if (!firstTimeAtDoor && !warning.isVisible() && prompt != 0) { // warning > prompts
                    textBoxOpen = prompt; // toggle on if not already
                    textBoxes[textBoxOpen].toggleOn();
                    if (keyPressed['e'] && prompt == 1) { // if the user presses e
                        textBoxes[textBoxOpen].toggleOff();
                        stop(); // stop the timer
                        character.setPosition(750, 395);
                        character.stopMovement(); // stop the character's movement
                        ChangeScene.changeToEscapeRoomSchool(); // change to escape room school (exit room)
                    } else if (keyPressed['p'] && prompt != 1) {
                        textBoxes[textBoxOpen].toggleOff();
                        removeItem(prompt - 2);
                    }
                } else if (textBoxOpen != 0) {
                    textBoxes[textBoxOpen].toggleOff();
                    textBoxOpen = 0;
                }
            }
        };
    }

    /**
     * restarts a scene
     */
    @Override
    public void startScene() {
        // reset keypress
        Arrays.fill(keyPressed, false);

        backpack.changeRoom(this);

        character.startMovement();
        collisionTimer.start();
        stage.setScene(scene);
    }

    /**
     * adds an item to the room
     * @param path the path to the image of the item
     * @param id the id of the item
     * @param x the x coordinate of the item
     * @param y the y coordinate of the item
     * @param size the size of the item
     * @param rad the radius of the item's hitbox (prompt box)
     * @param val what value to use in fillPromptGrid()
     */
    public void addItem(String path, String id, int x, int y, int size, int rad, int val) {
        ImageView item = new ImageView(path);
        item.preserveRatioProperty().set(true);
        item.setX(x);
        item.setY(y);
        item.setFitWidth(size);
        item.setId(id);
        fillPromptGrid(x - rad, y - rad, x + size + rad, y + size + rad, val);
        root.getChildren().add(item);
        items.add(item);
    }

    /**
     * removes an item from the room
     * @param index the index of the item (in items) to remove
     */
    public void removeItem(int index) {
        fillPromptGrid(itemCoords.get(index).x - 20, itemCoords.get(index).y - 20, itemCoords.get(index).x + 70, itemCoords.get(index).y + 70, 0);
        backpack.foundItem(items.get(index).getId());
        root.getChildren().remove(items.get(index));;
    }
}
