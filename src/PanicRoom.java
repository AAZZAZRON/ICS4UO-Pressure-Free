/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * PanicRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 2 (Sion)
 * - framework of Panic Room
 * - create graphics and quizes
 * - implement user input using buttons
 * - implement transitions between quizes
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
 *
 */

/**
 * @author Sion Gang
 * May 29th, 2022
 * @version 2.0
 * Time: 2 hours
 * - Further implementation of Panic Room
 * - Updated textbox system - more efficient & organized
 *
 */

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;


public class PanicRoom extends Room {

    private FadeTransition fade = new FadeTransition();

    /** Stores counter for the quiz */
    private static int counter = 1;

    /** Stores answers*/
    private int [] answers;

    /** Stores Quizes*/
    private ImageView [] quizes;

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
        super(stage);
        answers = new int[]{2, 3, 1, 2, 2,  1, 2, 2, 2, 3};
        quizes = new ImageView [answers.length];
        for (int i = 1; i <= answers.length; i++)
          quizes [i-1] = new ImageView("Assets/Scenes/Quizes/PanicRoom " + i + ".png");
        attempt = false;

    }

    @Override
    void exit() {

    }

    /**
     * @return ImageView of the selected image
     */
    public ImageView displayScene () {
        ImageView image = quizes[counter];
        image.setPreserveRatio(true);
        image.setFitWidth(800);

        // Fade animation
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(image);
        fade.play();

        return image;
    }


    /**
     * sets up the GUI for panic room
     * sets up panic room logic flow
     */
    public void panicRoom() {
        ImageView bg =  Tools.createBackgroundImage("Assets/School/Rooms/ClassBg.png");
        // set scene
        root = new Group();
        // add background
        root.getChildren().add(bg);
        // add Lesson
        root.getChildren().add(displayScene());
        scene = new Scene(root);
        stage.setScene(scene);


        // tester code to get coordinates
        scene.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("Mouse clicked at: " + event.getX() + ", " + event.getY());
            }
        });

        attempt = false;
        correct = false;
        userInput();

    }

    /**
     * gets user input for multiple choice questions
     */
    public void userInput() {
        System.out.println("input");
        // buttons for multiple choice
        ImageView q1 = Tools.createButton(root, "Assets/MainMenu/Buttons/", "x", 90, 236, 20);
        ImageView q2 = Tools.createButton(root, "Assets/MainMenu/Buttons/", "x", 90, 299, 20);
        ImageView q3 = Tools.createButton(root, "Assets/MainMenu/Buttons/", "x", 90, 362, 20);

        final int[] guess = new int[1];

        // next button
        ImageView backButton = Tools.createButton(root, "Assets/Buttons/", "next", 550, 430, 180);

        // button conditions
        q1.setOnMouseClicked(e -> {
            guess[0] = 1;
            checkInput(guess);
            displayMessage(2);
            attempt = true;

        });
        q2.setOnMouseClicked(e -> {
            guess[0] = 2;
            checkInput(guess);
            displayMessage(2);
            attempt = true;

        });
        q3.setOnMouseClicked(e -> {
            guess[0] = 3;
            checkInput(guess);
            displayMessage(2);
            attempt = true;
        });

        // if next button is clicked
        backButton.setOnMouseClicked(e -> {
            displayMessage(1);
            if (attempt) {
                if (counter < answers.length-1) {
                    counter++;
                    System.out.println(score);
                    message.toggleOff();
                    panicRoom();
                } else ChangeScene.changeToDeficiencyRoom(stage);
            }
        });
    }

    /**
     * Checks user input for mutliple choice quiz
     * @param guess the user's guess
     */
    public void checkInput (int guess[]) {
        if (guess[0] == answers[counter]) {
            score++;
            correct = true;
        }
    }

    /**
     * displays message for each quiz result
     *
     * @param choice condition concerning which type of textbox to show
     */
    public void displayMessage (int choice) {

         if (choice == 1) {
             if (message != null) // deletes any previous instances of message
                 message.toggleOff();
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
        message.toggleOn(); // show message

    }
}
