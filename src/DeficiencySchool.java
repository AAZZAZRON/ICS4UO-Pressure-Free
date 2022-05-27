/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * DeficiencySchool.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - set up 800 by 600 collision grid for the character + helper methods
 *
 * Version 1 (Sion)
 * - set up collision detection for the rooms and border (not for doors)
 * - build character using collision grid
 */

/**
 * @author Aaron Zhu
 * May 17th, 2022
 * @version 1.0
 * Time: 5 minutes
 * Create School.java
 */

/**
 * @author Aaron Zhu
 * May 20th, 2022
 * @version 1.0
 * Time: 5 minutes
 * fillCollisions()
 * - fill the collision grid with a number
 * create buildDeficienciesRoom(), which just sets a background image
 */

/**
 * @author Sion Gang
 * May 20th, 2022
 * @version 1.0
 * Time: 40 minutes
 * buildDeficienciesRoom()
 * - add coordinates of the door
 * - test deficiency implementation into school
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 1 hour
 * Separated collisionGrid and promptGrid
 * fillCollisionGrid()
 * - fill the collision grid with true
 * fillPromptGrid()
 * - fill the prompt grid with integers
 *
 * moved the door collision into promptGrid
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 30 minutes
 * build all the doors for deficiencies room in buildDeficienciesRoom()
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 5 minutes
 * buildDeficienciesRoom()
 * - create textBoxes
 */

/**
 * @author Aaron Zhu
 * May 26th, 2022
 * @version 2.0
 * Time: 1 hour
 * buildDeficienciesRoom() --> build()
 * - move collision/prompt helper methods to parent class CollisionRoom.java
 * - move AnimationTimer from Character.java to DeficiencySchool.java
 * - implement the animation timer
 */

/**
 * @author Aaron Zhu
 * May 27th, 2022
 * @version 2.0
 * Time: 15 minutes
 * allow user to exit school after watching lessons
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DeficiencySchool extends CollisionRoom {

    /**
     * stores the next room to enter to view a lesson
     */
    private static int roomNumber = 1;

    /**
     * stores the total number of lessons in the deficiency room
     */
    private final int NUMBER_OF_LESSONS = 4;

    /**
     * constructor for Deficiencies School
     * @param stage the primary stage for this application
     */
    public DeficiencySchool(Stage stage) {
        super(stage);
        textBoxes = new TextBox[10];
    }

    /**
     * builds the school for the deficiencies room
     */
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("School/Rooms/schoolBg.png");

        // set scene
        Group root = new Group(image);
        scene = new Scene(root);
        stage.setScene(scene);

        // room collisions
        fillCollisionGrid(160, 0, 800, 216);
        fillCollisionGrid(0, 296, 296, 600);
        fillCollisionGrid(384, 296, 800, 504);

        // borders collisions
        fillCollisionGrid(0, 0, 800, 100);
        fillCollisionGrid(0, 0, 8, 600);
        fillCollisionGrid(0, 592, 800, 600);
        fillCollisionGrid(792, 0, 800, 600);

        // door entrances
        fillPromptGrid(200, 216, 264, 232, 1); // room 1
        fillPromptGrid(296, 320, 312, 384, 2); // room 2
        fillPromptGrid(488, 504, 552, 520, 3); // room 3
        fillPromptGrid(688, 504, 752, 520, 4); // room 4
        fillPromptGrid(472, 216, 536, 232, 5); // washroom 1
        fillPromptGrid(704, 216, 768, 232, 6); // washroom 2
        fillPromptGrid(296, 496, 312, 560, 7); // library
        fillPromptGrid(0, 88, 24, 184, 8); // exit

        // textbox messages
//        textBoxes[1] = new TextBox(stage, root, scene, "Press e to enter room 101");
//        textBoxes[2] = new TextBox(stage, root, scene, "Press e to enter the washroom");
//        textBoxes[3] = new TextBox(stage, root, scene, "Press e to enter the washroom");
//        textBoxes[4] = new TextBox(stage, root, scene, "Press e to enter room 102");
//        textBoxes[5] = new TextBox(stage, root, scene, "Press e to enter the library");
//        textBoxes[6] = new TextBox(stage, root, scene, "Press e to enter room 103");
//        textBoxes[7] = new TextBox(stage, root, scene, "Press e to enter room 104");
//        textBoxes[8] = new TextBox(stage, root, scene, "Press e to exit the school");

        // textbox messages
        if (roomNumber <= NUMBER_OF_LESSONS) { // if net room to enter is a lesson
            for (int i = 1; i <= 7; i++) {
                textBoxes[i] = new TextBox(stage, root, scene, "Go to room " + (roomNumber + 100) + " to learn about peer pressure", "Red");
            }
            textBoxes[8] = new TextBox(stage, root, scene, "You cannot leave the school yet! Please finish watching all the lessons", "Red");
            textBoxes[roomNumber] = new TextBox(stage, root, scene, "Press e to enter the room and learn about peer pressure", "Green");
        } else { // if user should exit school
            for (int i = 1; i <= 7; i++) {
                textBoxes[i] = new TextBox(stage, root, scene, "You have viewed all the lessons! Exit the school to enter the panic room and test your knowledge on peer pressure.", "Red");
            }
            textBoxes[8] = new TextBox(stage, root, scene, "Press e to exit the school", "Green");
        }

        // create character
        character = new Character(root, scene, 100);
        character.build();
        setUpUserInput();
        setUpAnimationTimer();
    }

    /**
     * sets up the animation timer for the character in the room
     */
    @Override
    public void setUpAnimationTimer() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // handle movement
                if (keyPressed['w'] && !isColliding()) {
                    character.moveUp(true);
                    if (isColliding()) character.moveDown(false); // if character is colliding, revert movement
                }
                if (keyPressed['s'] && !isColliding()) {
                    character.moveDown(true);
                    if (isColliding()) character.moveUp(false);
                }
                if (keyPressed['a'] && !isColliding()) {
                    character.moveLeft(true);
                    if (isColliding()) character.moveRight(false);
                }
                if (keyPressed['d'] && !isColliding()) {
                    character.moveRight(true);
                    if (isColliding()) character.moveLeft(false);
                }

                // handle prompt
                int prompt = getPrompt();
                // toggle textbox visibility
                if (prompt != 0) {
                    textBoxOpen = prompt; // toggle on if not already
                    textBoxes[textBoxOpen].toggleOn();

                    if (keyPressed['e'] && prompt == roomNumber) { // if the user presses e to enter correct deficiency room
                        keyPressed['e'] = false; // set the key to false
                        stop(); // stop the timer
                        roomNumber += 1; // increment room number
                        ChangeScene.changeToDeficiencyRoom(stage); // change to deficiency room
                    } else if (keyPressed['e'] && prompt == NUMBER_OF_LESSONS + 1) { // if the user presses e to exit school
                        keyPressed['e'] = false; // set the key to false
                        stop(); // stop the timer
                        ChangeScene.changeToPanicRoom(stage); // change to panic room
                    }
                } else if (textBoxOpen != 0) {
                    textBoxes[textBoxOpen].toggleOff();
                    textBoxOpen = 0;
                }
            }
        };
        collisionTimer.start();
    }
}
