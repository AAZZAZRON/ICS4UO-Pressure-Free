/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Character.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 2 (Aaron)
 * - centralized scene changer
 *
 * Version 3 (Aaron)
 * - front load deficiency room and escape room scenes
 * - use one scene for certain rooms so program does not need to use static variables/methods
 * - adds backpack to each escape room scene (room)
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

/**
 * @author Aaron Zhu
 * May 30th, 2022
 * @version 3.0
 * Time: 30 minutes
 * front-load the rooms in the school for escape room
 * change scenes in escape room using indexes in an array
 * reorder methods for organization
 */

/**
 * @author Sion Gang
 * June 2nd, 2022
 * @version 3.0
 * Time: 30 minutes
 * pass the backpack by reference to the rooms
 */

/**
 * @author Aaron Zhu
 * June 4th, 2022
 * @version 4.0
 * Time: 30 minutes
 * Add room name to escape rooms in reinitialize()
 */

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeScene {
    /** The current stage */
    public static Stage stage;

    /** reference to the escape room school. Required so we don't reload the school and lose data */
    private static EscapeRoomSchool escapeRoomSchool;

    /** reference to the deficiency room school. Required so we don't reload the room and lose data */
    private static DeficiencyRoom deficiencyRoom;

    /** rooms inside the school */
    private static ScenarioRoom[] escapeRooms;

    /**
     * reinitializes the escape room and deficiency room for a new game
     */
    public static void reinitialize() {
        // build the deficiency room (school)
        deficiencyRoom = new DeficiencyRoom(stage);
        deficiencyRoom.buildRoom();

        // build backpack
        Backpack backpack = new Backpack();

        // build the escape room school
        escapeRoomSchool = new EscapeRoomSchool(stage, backpack);
        escapeRoomSchool.buildRoom();

        // build escape room rooms
        escapeRooms = new ScenarioRoom[10];
        escapeRooms[1] = new EscapeClassroom(stage, backpack, "Room101", 1);
        escapeRooms[1].buildRoom();
        escapeRooms[2] = new EscapeClassroom(stage, backpack, "Room102", 6);
        escapeRooms[2].buildRoom();
        escapeRooms[3] = new EscapeClassroom(stage, backpack, "Room103", 5);
        escapeRooms[3].buildRoom();
        escapeRooms[4] = new EscapeClassroom(stage, backpack, "Room104", 4);
        escapeRooms[4].buildRoom();
        escapeRooms[5] = new EscapeWashroom(stage, backpack, "Washroom1", 2);
        escapeRooms[5].buildRoom();
        escapeRooms[6] = new EscapeWashroom(stage, backpack, "Washroom2", -1);
        escapeRooms[6].buildRoom();
        escapeRooms[7] = new EscapeLibrary(stage, backpack, "Library", 3);
        escapeRooms[7].buildRoom();
    }



    /**
     * Change to deficiency room
     */
    public static void changeToDeficiencyRoom() {
        deficiencyRoom.startScene();
    }

    /**
     * Change to deficiency room
     */
    public static void changeToDeficiencyRoom(boolean firstTime) {
        Scene next1 = Tools.displayIntermissionText(stage, "Thank you for playing Pressure-Free, a game made to educate students on how to overcome peer pressure. We (the developers) hope you enjoy the game!");
        Scene next2 = Tools.displayIntermissionText(stage, "LEVEL ONE: DEFICIENCY ROOM\n\nWalk through the school and enter different rooms to learn about peer pressure!");
        Scene next3 = Tools.displayIntermissionText(stage, "Remember to control the character using WASD.");

        stage.setScene(next1);

        next1.onMouseClickedProperty().set(e -> stage.setScene(next2));

        next2.onMouseClickedProperty().set(e -> stage.setScene(next3));

        next3.onMouseClickedProperty().set(e -> deficiencyRoom.startScene());
    }

    /**
     * Change to deficiency lesson
     * @param lessonNum the lesson to display
     */
    public static void changeToDeficiencyLesson(int lessonNum) {
        DeficiencyLesson deficiencyRoom = new DeficiencyLesson(stage, lessonNum); // display deficiencies room
        deficiencyRoom.deficiencyRoom();
    }



    /**
     * Change to panic room
     */
    public static void changeToPanicRoom () {
        Scene next1 = Tools.displayIntermissionText(stage, "CONGRATULATIONS on learning about peer pressure!");
        Scene next2 = Tools.displayIntermissionText(stage, "LEVEL TWO: PANIC ROOM\n\nTest your knowledge of peer pressure through a quiz! To move on to level three, a score of 5/10 or above must be acquired. Can you accurately answer the questions?");

        stage.setScene(next1);

        next1.onMouseClickedProperty().set(e -> stage.setScene(next2));
        next2.onMouseClickedProperty().set(e -> {
            PanicRoom panicRoom = new PanicRoom(stage); // display panic room
            panicRoom.panicRoom();
        });
    }



    /**
     * Change to escape room
     */
    public static void changeToEscapeRoom(int score) {
        if (score >= 5) {
            Scene next1 = Tools.displayIntermissionText(stage, "CONGRATULATIONS on completing the quiz!\n\nYou scored " + score + " out of 10.");
            Scene next2 = Tools.displayIntermissionText(stage, "LEVEL THREE: ESCAPE ROOM\n\nSchool is over and you are going to your friend's house to complete an assignment. But, you must first acquire some resources to complete the assignment. Items you need to collect are listed in your backpack.");
            Scene next3 = Tools.displayIntermissionText(stage, "LEVEL THREE: ESCAPE ROOM\n\nRemember to control the character using WASD.\nAlso, click on the backpack icon (bottom left corner) to view its contents.");
            Scene next4 = Tools.displayIntermissionText(stage, "LEVEL THREE: ESCAPE ROOM\n\nBeware of peer pressure as you are collecting resources.\nDo not let your friends influence your decisions!");
            stage.setScene(next1);

            next1.onMouseClickedProperty().set(e -> stage.setScene(next2));

            next2.onMouseClickedProperty().set(e -> stage.setScene(next3));

            next3.onMouseClickedProperty().set(e -> stage.setScene(next4));

            next4.onMouseClickedProperty().set(e -> escapeRoomSchool.startScene());
        } else {
            Scene next1 = Tools.displayIntermissionText(stage, "UNFORTUNATE\n\nYou scored " + score + " out of 10.");
            Scene next2 = Tools.displayIntermissionText(stage, "Please play again. Better luck next time!");

            stage.setScene(next1);

            next1.onMouseClickedProperty().set(e -> stage.setScene(next2));

            next2.onMouseClickedProperty().set(e -> ChangeScene.changeToMainMenu());
        }
    }

    /**
     * change to escape room school
     */
    public static void changeToEscapeRoomSchool() {
        escapeRoomSchool.startScene();
    }

    /**
     * change to room in escape room school
     * @param val the room index to display
     */
    public static void changeToEscapeRoomRoom(int val) {
        escapeRooms[val].startScene();
    }



    /**
     * Change to splash screen
     */
    public static void changeToSplashScreen() {
        SplashScreen splashScreen = new SplashScreen(stage);
        splashScreen.splashScreen();
    }

    /**
     * Change to main menu
     */
    public static void changeToMainMenu() {
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.mainMenu();
    }

    /**
     * Change to instructions page
     */
    public static void changeToInstructions() {
        Instructions instructions = new Instructions(stage);
        instructions.instructions();
    }
}
