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

import javafx.stage.Stage;

public class ChangeScene {
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
        PanicRoom panicRoom = new PanicRoom(stage); // display deficiencies room
        System.out.println(5);
        panicRoom.panicRoom();
    }


    /**
     * Change to deficiency room
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToDeficiencyRoom(Stage stage) {
        DeficiencyRoom school = new DeficiencyRoom(stage);
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

    /**
     * Change to splash screen
     * @param stage The primary stage for this application. Passed by reference.
     */
    public static void changeToSplashScreen(Stage stage) {
        SplashScreen splashScreen = new SplashScreen(stage);
        splashScreen.splashScreen();
    }
}
