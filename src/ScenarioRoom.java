import javafx.stage.Stage;

public abstract class ScenarioRoom extends CollisionRoom {

    /**
     * Constructor for CollisionRoom.
     *
     * @param stage the primary stage for this application. Passed by reference.
     */
    public ScenarioRoom(Stage stage) {
        super(stage);
    }


}
