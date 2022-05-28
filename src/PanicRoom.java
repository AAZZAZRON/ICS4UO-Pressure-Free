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

    /** The root of the room*/
    private Group root;

    /** Boolean that dictates if user finished the question*/
    private boolean attempt;

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
        Scene scene = new Scene(root);
        stage.setScene(scene);


        // tester code to get coordinates
        scene.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("Mouse clicked at: " + event.getX() + ", " + event.getY());
            }
        });

        attempt = false;
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
        ImageView backButton = Tools.createButton(root, "Assets/MainMenu/Buttons/", "x", 650, 170, 40);

        // button conditions

        System.out.println("Button");
        q1.setOnMouseClicked(e -> {
            if (attempt == false) {
                guess[0] = 1;
                attempt = true;
                System.out.println(guess[0] + " " + answers[counter]);
            }
        });
        q2.setOnMouseClicked(e -> {
            if (attempt == false) {
                guess[0] = 2;
                attempt = true;
                System.out.println(guess[0] + " " + answers[counter]);
            }
        });
        q3.setOnMouseClicked(e -> {
            if (attempt == false) {
                guess[0] = 3;
                attempt = true;
                System.out.println(guess[0] + " " + answers[counter]);
            }
        });



        // if next button is clicked
        backButton.setOnMouseClicked(e -> {
            if (attempt) {
                System.out.println("Guess"+guess[0]+"Answer "+answers[counter]);

                if (guess[0] == answers[counter]) {
                    score++;
                    System.out.println("RIGHT");
                }
                else displayMessage();

                if (counter < answers.length-1) {
                    counter++;
                    System.out.println(score);
                    panicRoom();
                } else ChangeScene.changeToDeficiencyRoom(stage);
            } else {
                System.out.println("Please solve the question");
            }
        });
    }

    /** displays message for each quiz result */
    public void displayMessage () {
        System.out.println("Wrong Answer.");
    }

}
