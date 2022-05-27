/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Character.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
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

import javafx.stage.Stage;

public class ChangeScene {
    /**
     * Change to deficiency room
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToDeficiencyRoom(Stage stage) {
        DeficiencyRoom deficiencyRoom = new DeficiencyRoom(stage); // display deficiencies room
        System.out.println(3);
        deficiencyRoom.deficiencyRoom();
    }

    /**
     * Change to panic room
     * @param stage The primary stage for this application. Passed by reference.
     */

    public static void changeToPanicRoom (Stage stage) {
        PanicRoom panicRoom = new PanicRoom(stage); // display deficiencies room
        System.out.println(5);
        panicRoom.panicRoom();
    }


    /**
     * Change to school
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToDeficiencySchool(Stage stage) {
        DeficiencySchool school = new DeficiencySchool(stage);
        school.buildRoom(); // deficiency room
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
}
