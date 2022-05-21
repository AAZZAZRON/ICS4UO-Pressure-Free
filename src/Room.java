
/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Room.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Sion)
 * - abstract class for rooms
 * - 
 */


import javafx.stage.Stage;


public abstract class Room {
    /** The primary stage for this application. Passed by reference. */
    public final Stage stage;

    /** constructor for Room
     * @param stage the stage of the room
     * */
    public Room(Stage stage) {
        this.stage = stage;
    }


}
