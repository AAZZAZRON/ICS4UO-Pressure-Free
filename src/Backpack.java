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

/**
 * @author Sion Gang
 * June 6th, 2022
 * @version 4.0
 * Time: 3 hours
 * - backpack graphics implementation
 * - dynamic text display
 * - messge if user found all items
 *  */

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.text.*;

import java.util.ArrayList;
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
        ImageView backpack = Tools.createButton(root, "Assets/School/Items/", "Backpack", 8, 510, 75);

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
        ImageView image1 = Tools.createBackgroundImage("Assets/School/Items/BackpackBg.png");
        root.getChildren().add(image1);
        ImageView exit = Tools.createButton(root, "Assets/Buttons/", "x",690, 81, 50 );

        Group text = new Group(); // new group for texts

        int xCoord;
        int yCoord;
        int counter = 0; // counter that keep tracks of contents
        int countFound = 0; // if user found all items

        System.out.println("contents"); // temporary
        Text header1 = new Text("ITEMS FOUND");
        System.out.println("ITEMS FOUND");
        header1.setX(325);
        header1.setY(137);
        header1.setFill(Color.BLACK);
        header1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        text.getChildren().add(header1);

        xCoord = 325;
        yCoord = 200;

        for (String item : itemsFound.keySet()) {
            counter++;
            if (counter == 5) { // displays contents in two columns
                yCoord = 200;
                xCoord = 500;
            }
            System.out.println(item + ": " + itemsFound.get(item));
            if (itemsFound.get(item) != 0) {
                countFound++;
                Text message = new Text(item + ": " + itemsFound.get(item));
             //   message.setWrappingWidth(400);
                message.setX(xCoord);
                message.setY(yCoord);
                message.setFill(Color.BLACK);
                message.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                text.getChildren().add(message);
            }
            yCoord += 30;
        }

        System.out.println("ITEMS NEEDED");
        Text header2 = new Text("ITEMS NEEDED");
        header2.setX(325);
        header2.setY(329);
        header2.setFill(Color.BLACK);
        header2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        text.getChildren().add(header2);

        yCoord = 359;
        xCoord = 325;
        counter = 0;

        for (String item : itemsNeeded.keySet()) {
            counter++;
            if (counter == 5) { // splits contents to two column table
                yCoord = 359;
                xCoord = 500;
            }

            System.out.println(item + ": " + itemsNeeded.get(item));
            if (itemsNeeded.get(item) != 0) {
                Text message = new Text(item + ": " + itemsNeeded.get(item));
               // message.setWrappingWidth(400);
                message.setX(xCoord);
                message.setY(yCoord);
                message.setFill(Color.BLACK);
                message.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                text.getChildren().add(message);

            }
            yCoord += 30;
        }


        if (countFound == itemsNeeded.size()) {
            text.getChildren().removeAll();
           Text message = new Text("You have found all items.");
           message.setX(325);
           message.setY(300);
            message.setFill(Color.BLACK);
            message.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
            text.getChildren().add(message);
        }

        root.getChildren().add(text);
        exit.setOnMouseClicked(e -> {
            root.getChildren().remove(image1);
            root.getChildren().remove(exit);
            root.getChildren().remove(text);
            root.getChildren().add(0, root.getChildren().remove(root.getChildren().size() -1));
            room.startScene();

        });
    }

    /**
     * checks if the backpack is full
     * @return true if full, false if not
     */
    public boolean foundAllItems() {
        for (String item : itemsNeeded.keySet()) {
            if (itemsNeeded.get(item) != 0) {
                return false;
            }
        }
        return true;
    }
}
