/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * PanicRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 2 (Sion)
 * - framework of Panic Room
 * - create graphics and quizes
 * - implement user input using buttons
 * - implement transitions between quizes
 *
 * Version 3 (Sion)
 * - Implemented better graphics + transitions
 * - Update textbox system - more efficient and organized
 */

/**
 * @author Sion Gang
 * May 26th, 2022
 * @version 2.0
 * Time: 5 hours
 * - create PanicRoom.java
 * - fade Animations
 * - buttons for multiple choice
 * - buttons for next question
 * - taking user input
 */

/**
 * @author Sion Gang
 * May 28th, 2022
 * @version 2.0
 * Time: 2 hours
 * - graphics for next button
 * - implement next button
 * - implement text messages for warnings and right/wrong answers
 */

/**
 * @author Sion Gang
 * May 29th, 2022
 * @version 3.0
 * Time: 2 hours
 * - further implementation of Panic Room
 * - updated textbox system - more efficient & organized
 * - configuring buttons
 */

/**
 * @author Sion Gang
 * May 31th, 2022
 * @version 3.0
 * Time: 5 minutes
 * Implement the usage of Tools.fadeImage() to transition
 */

/**
 * @author Sion Gang
 * May 31th, 2022
 * @version 3.0
 * Time: 15 minutes
 * Implement counter for quizes and general update for panic room
 */

/**
 * Aaron Zhu
 * June 7th, 2022
 * @version 4.0
 * Time: 30 minutes
 * Changed panic room buttons
 * - created hover buttons ABC and right/wrong
 * Implemented the buttons into panic room
 */

/**
 * @author Aaron Zhu
 * June 8th, 2022
 * @version 4.0
 * Time: 30 minutes
 * Added triangles on answer pressed
 */

/**
 * @author Sion Gang
 * June 8th, 2022
 * @version 4.0
 * Time: 5 minutes
 * Fix counter bug that did not reset after quitting panic room
 */

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PanicRoom {
    /** stage for the application */
    private Stage stage;

    /** Stores counter for the quiz */
    private static int counter = 0;

    /** Stores answers*/
    private int [] answers;

    /** Stores Quizes*/
    private ImageView [] quizzes;

    /** Player's score*/
    private static int score;

    /** The Scene of the Panic Room*/
    private Scene scene;

    /** The root of the room*/
    private Group root;

    /** Boolean that dictates if user finished the question*/
    private boolean attempt;

    /** Boolean that stores if the user answered the quiz correctly */
    private boolean correct;

    /** Textbox of message that is displayed at the end of every question */
    private TextBox message;

    /**
     * constructor for Room
     *
     * @param stage the stage of the room
     */
    public PanicRoom(Stage stage) {
        this.stage = stage;
        answers = new int[]{2, 3, 1, 2, 2,  1, 2, 2, 2, 3};
        quizzes = new ImageView [answers.length];
        for (int i = 1; i <= answers.length; i++)
          quizzes[i-1] = new ImageView("Assets/Scenes/Quizzes/PanicRoom " + i + ".png");
        attempt = false;
        score = 0;
        counter = 0;

    }




    /**
     * sets up the GUI for panic room
     * sets up panic room logic flow
     */
    public void panicRoom() {
        ImageView bg =  Tools.createBackgroundImage("Assets/School/Rooms/FadedClassBg.png");

        Text roomCount = new Text("Q " + (counter+1) +"/10");
        roomCount.setX(628);
        roomCount.setY(168);
        roomCount.setFill(Color.WHITE);
        roomCount.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));

        // set scene
        root = new Group();
        // add background
        root.getChildren().add(bg);
        // add Lesson
        root.getChildren().add(Tools.addFadeOn(quizzes[counter]));
        // add quiz counter text
        root.getChildren().add(roomCount);

        scene = new Scene(root);
        stage.setScene(scene);

        attempt = false;
        correct = false;
        userInput();

    }

    /**
     * gets user input for multiple choice questions
     */
    public void userInput() {
        // buttons for multiple choice
        ImageView q1 = Tools.createButton(root, "Assets/Buttons/ChoiceButtons/", "a", 90, 236, 20);
        ImageView q2 = Tools.createButton(root, "Assets/Buttons/ChoiceButtons/", "b", 90, 299, 20);
        ImageView q3 = Tools.createButton(root, "Assets/Buttons/ChoiceButtons/", "c", 90, 362, 20);

        final int[] guess = new int[1];

        // next button
        ImageView backButton = Tools.createButton(root, "Assets/Buttons/", "next", 550, 420, 180);
        root.getChildren().remove(backButton);

        Tools.addFadeOn(q1);
        Tools.addFadeOn(q2);
        Tools.addFadeOn(q3);
        Tools.addFadeOn(backButton);

        // button conditions
        q1.setOnMouseClicked(e -> {
            guess[0] = 1;
            checkInput(guess);
            displayMessage(2);
            attempt = true;
            showAnswers(q1, q2, q3);
            root.getChildren().add(backButton);
            Tools.createStaticImage(root, "Assets/pointer.png", 90, 216, 20);
        });

        q2.setOnMouseClicked(e -> {
            guess[0] = 2;
            checkInput(guess);
            displayMessage(2);
            attempt = true;
            showAnswers(q1, q2, q3);
            root.getChildren().add(backButton);
            Tools.createStaticImage(root, "Assets/pointer.png", 90, 279, 20);
        });

        q3.setOnMouseClicked(e -> {
            guess[0] = 3;
            checkInput(guess);
            displayMessage(2);
            attempt = true;
            showAnswers(q1, q2, q3);
            root.getChildren().add(backButton);
            Tools.createStaticImage(root, "Assets/pointer.png", 90, 342, 20);
        });

        // if next button is clicked
        backButton.setOnMouseClicked(e -> {
            displayMessage(1);
            if (attempt) {
                if (counter < answers.length-1) {
                    counter++;
                    message.toggleOff();
                    panicRoom();
                } else ChangeScene.changeToEscapeRoom(score);
            }
        });
    }

    /**
     * Checks user input for mutliple choice quiz
     * @param guess the user's guess
     */
    private void checkInput (int[] guess) {
        if (guess[0] == answers[counter]) {
            score++;
            correct = true;
        }
    }

    /**
     * display the answer to the question
     * @param q1 the first answer
     * @param q2 the second answer
     * @param q3 the third answer
     */
    private void showAnswers(ImageView q1, ImageView q2, ImageView q3) {
        root.getChildren().removeAll(q1, q2, q3);

        if (answers[counter] == 1) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/aButtonRight.png", 90, 236, 20);
        else Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/aButtonWrong.png", 90, 236, 20);

        if (answers[counter] == 2) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/bButtonRight.png", 90, 299, 20);
        else Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/bButtonWrong.png", 90, 299, 20);

        if (answers[counter] == 3) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/cButtonRight.png", 90, 362, 20);
        else Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/cButtonWrong.png", 90, 362, 20);
    }

    /**
     * displays message for each quiz result
     *
     * @param choice condition concerning which type of textbox to show
     */
    private void displayMessage (int choice) {
        if (message != null) // deletes any previous instances of message
            message.toggleOff();
         if (choice == 1) {
            if (!attempt) // if user did not select an answer
                message = new TextBox(stage, root, scene, "Select an answer", "Red");
        }
        if (choice == 2) {
            if (attempt) { // if user selected answer
                message.toggleOff();
                message = new TextBox(stage, root, scene, "You have already chosen your answer", "Red");
            } else if (correct) // if user answered correctly
                message = new TextBox(stage, root, scene, "You selected the correct answer!", "Green");
            else
                message = new TextBox(stage, root, scene, "You selected the wrong answer." +
                        "\nThe answer was option " + answers[counter], "Blue");
           }
        message.setWarning(4); // show message
    }
}
