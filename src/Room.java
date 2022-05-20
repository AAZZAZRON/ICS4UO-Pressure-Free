import javafx.stage.Stage;

public abstract class Room {
    /** The primary stage for this application. Passed by reference. */
    public final Stage stage;

    public Room(Stage stage) {
        this.stage = stage;
    }

    abstract void exit();
}
