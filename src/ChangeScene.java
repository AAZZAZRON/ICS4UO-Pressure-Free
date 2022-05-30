/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Character.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 2 (Aaron)
 * - centralized scene changer
 */

/**
 * @author Aaron Zhu
 * May 24th, 2022
 * @version 2.0
 * Time: 30 minutes
 * Moved scene changing to ChangeScene.java
 */

/**
 * @author Sion Gang
 * May 26th, 2022
 * @version 2.0
 * Time: 5 minutes
 * Added changeToPanicRoom() method
 */

/**
 * @author Aaron Zhu
 * May 28th, 2022
 * @version 3.0
 * Time: 5 minutes
 * Added changeToSplashScreen() method
 */

/**
 * @author Aaron Zhu
 * May 29th, 2022
 * @version 3.0
 * Time: 30 minutes
 * change scenes without creating a new scene
 */

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ChangeScene {
    /** reference to the escape room school. Required so we don't reload the school and lose data */
    private static EscapeRoomSchool escapeRoomSchool;

    /** reference to the deficiency room school. Required so we don't reload the room and lose data */
    private static DeficiencyRoom deficiencyRoom;

    private static ScenarioRoom[] escapeRooms;

    /**
     * reinitializes the escape room and deficiency room for a new game
     * @param stage the primary stage for this application
     */
    public static void reinitialize(Stage stage) {
        // build the escape room school
        escapeRoomSchool = new EscapeRoomSchool(stage);
        escapeRoomSchool.buildRoom();

        // build the deficiency room (school)
        deficiencyRoom = new DeficiencyRoom(stage);
        deficiencyRoom.buildRoom();

        escapeRooms = new ScenarioRoom[10];
        escapeRooms[1] = new EscapeClassroom(stage);
        escapeRooms[1].buildRoom();
        escapeRooms[2] = new EscapeClassroom(stage);
        escapeRooms[2].buildRoom();
        escapeRooms[3] = new EscapeClassroom(stage);
        escapeRooms[3].buildRoom();
        escapeRooms[4] = new EscapeClassroom(stage);
        escapeRooms[4].buildRoom();
        escapeRooms[5] = new EscapeWashroom(stage);
        escapeRooms[5].buildRoom();
        escapeRooms[6] = new EscapeWashroom(stage);
        escapeRooms[6].buildRoom();
        escapeRooms[7] = new EscapeLibrary(stage);
        escapeRooms[7].buildRoom();
    }

    /**
     * Change to deficiency lesson
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToDeficiencyLesson(Stage stage) {
        DeficiencyLesson deficiencyRoom = new DeficiencyLesson(stage); // display deficiencies room
        System.out.println(3);
        deficiencyRoom.deficiencyRoom();
    }

    /**
     * Change to panic room
     * @param stage The primary stage for this application. Passed by reference.
     */

    public static void changeToPanicRoom (Stage stage) {
        ImageView next = Tools.displayIntermissionText(stage, "CONGRATULATIONS on learning amount peer pressure! Now, you will enter the PANIC ROOM, where your knowledge will be put to the test. Can you accurately recognize peer pressure in these scenarios?");
        next.onMouseClickedProperty().set(e -> {
            PanicRoom panicRoom = new PanicRoom(stage); // display panic room
            System.out.println(5);
            panicRoom.panicRoom();
        });
    }


    /**
     * Change to deficiency room
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToDeficiencyRoom(Stage stage) {
        deficiencyRoom.startScene();
    }

    /**
     * Change to instructions page
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToInstructions(Stage stage) {
        Instructions instructions = new Instructions(stage);
        instructions.instructions();
    }

    /**
     * Change to main menu
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToMainMenu(Stage stage) {
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.mainMenu();
    }

    /**
     * Change to splash screen
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToSplashScreen(Stage stage) {
        SplashScreen splashScreen = new SplashScreen(stage);
        splashScreen.splashScreen();
    }

    /**
     * change to escape room school
     */
    public static void changeToEscapeRoomSchool() {
        escapeRoomSchool.startScene();
    }

    
    public static void changeToEscapeRoomRoom(int val) {
        escapeRooms[val].startScene();
    }
}
