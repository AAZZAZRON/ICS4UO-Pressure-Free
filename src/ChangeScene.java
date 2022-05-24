import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeScene {
    public static void changeToDeficiencyRoom(Stage stage) {
        DeficiencyRoom deficiencyRoom = new DeficiencyRoom(stage); // display deficiencies room
        System.out.println(3);
        deficiencyRoom.deficiencyRoom();
    }

    public static void changeToSchool(Stage stage, int level) {
        School school = new School(stage);
        if (level == 0) school.buildDeficienciesRoom(); // deficiency room
    }

    public static void changeToInstructions(Stage stage) {
        Instructions instructions = new Instructions(stage);
        instructions.instructions();
    }

    public static void changeToMainMenu(Stage stage) {
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.mainMenu();
    }
}
