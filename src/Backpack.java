/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Backpack.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 3 (Sion):
 * - backpack tracks items that are found/still need to be found
 * - on backpack click, displays contents of backpack to CLI (will make it a GUI later)
 * - one backpack is used, and is passed into each room for simplicity's sake
 */

/**
 * @author Sion Gang
 * June 4th, 2022
 * @version 4.0
 * Time: 30 minutes
 * Change backpack contents accordingly
 */

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.HashMap;


public class Backpack {

    /** stage of the game */
    private Stage stage;

    /** current room character is in */
    private CollisionRoom room;

    /** hashmaps containing number of items found/need to be found */
    private HashMap<String, Integer> itemsFound, itemsNeeded;

    /**
     * Constructor for Backpack
     * @param stage the stage of the game
     */
    public Backpack(Stage stage) {
        this.stage = stage;
        itemsFound = new HashMap<>();
        itemsNeeded = new HashMap<>();

        itemsNeeded.put("Book", 5);
        itemsNeeded.put("Pencil Case", 1);
        itemsNeeded.put("Markers", 1);
        itemsNeeded.put("Pencil Crayons", 1);
        itemsNeeded.put("Scissors", 1);
        itemsNeeded.put("Laptop", 1);
        itemsNeeded.put("Glue Stick", 1);
        itemsNeeded.put("Clipboard", 1);

        itemsFound.put("Book", 0);
        itemsFound.put("Pencil Case", 0);
        itemsFound.put("Markers", 0);
        itemsFound.put("Pencil Crayons", 0);
        itemsFound.put("Scissors", 0);
        itemsFound.put("Laptop", 0);
        itemsFound.put("Glue Stick", 0);
        itemsFound.put("Clipboard", 0);
    }

    /**
     * change the current room of the character to room. Ensures that the backpack is added to the right scene
     * @param room the room to change to
     */
    public void changeRoom(CollisionRoom room) {
        this.room = room;
    }

    /**
     * builds the backpack in a certain scene
     * @param root the root of the scene
     */
    public void buildBackpack(Group root) {
        ImageView backpack = Tools.createButton(root, "Assets/School/Items/", "Backpack", 10, 500, 80);

        backpack.setOnMouseClicked(e -> {
            room.collisionTimer.stop();
            room.character.stopMovement();
            root.getChildren().add(root.getChildren().remove(0));
            contents(root);
        });
    }

    /**
     * update hashmaps with items found and items needed
     * @param name name of the item
     */
    public void foundItem(String name) {
        System.out.println(name);
        itemsFound.put(name, itemsFound.get(name) + 1);
        itemsNeeded.put(name, itemsNeeded.get(name) - 1);
    }

    /**
     * displays the contents of the backpack
     * @param root the root of the scene
     */
    public void contents(Group root) {
        System.out.println("contents"); // temporary
        System.out.println("ITEMS FOUND");
        for (String item : itemsFound.keySet()) {
            System.out.println(item + ": " + itemsFound.get(item));
        }
        System.out.println("ITEMS NEEDED");
        for (String item : itemsNeeded.keySet()) {
            System.out.println(item + ": " + itemsNeeded.get(item));
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            root.getChildren().add(0, root.getChildren().remove(root.getChildren().size() - 1));
            room.startScene();
        });
        pause.play();
    }
}
