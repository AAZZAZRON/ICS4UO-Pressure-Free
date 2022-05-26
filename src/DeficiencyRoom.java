/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * DeficiencyRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Sion)
 * - Deficiency Room that displays the different lessons
 * - create classroom background and deficiency room lessons (pixel art)
 * - fade in lessons
 * - mostly framework at this point
 */

/**
 * @author Sion Gang
 * May 20th, 2022
 * @version 1.0
 * Time: 1.5 hour
 *
 * displayLesson()
 * - add images to deficiency room
 * - add fade transition to the blackboard
 *
 * deficiencyRoom()
 * - implement fade transition
 */

/**
 * @author Sion Gang
 * May 24th, 2022
 * @version 2.0
 * Time: 30 minutes
 *
 * deficiencyRoom()
 * - implement multiple slide lesson
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class DeficiencyRoom extends Room {

    /** Stores transition */
    private FadeTransition fade = new FadeTransition();

    /** Stores counter for the lesson */
    private static int counter = 4;

    /** Stores array of deficiency rooms */
    private Room[] defRooms = new DeficiencyRoom[4];

    /**
     * Constructor for DeficiencyRoom.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public DeficiencyRoom(Stage stage) {
        super(stage);
    }

    @Override
    void exit() {

    }

    /**
     * @return ImageView of the selected image
     * @param path The path to the image
     */
    public ImageView displayLesson (String path) {
        ImageView image = new ImageView("Assets/School/Lessons/" +path+".png");
        image.setPreserveRatio(true);
        image.setFitWidth(786);

        // Fade animation
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(image);
        fade.play();
        return image;
    }

    /**
     * sets up the GUI for deficiency room
     * sets up deficiency room logic flow
     */
    public void deficiencyRoom () {
        ImageView bg = Tools.createBackgroundImage("Rooms/ClassBg.png");
        // set scene
        Group root = new Group();
        // add background
        root.getChildren().add(bg);
        // add Lesson
        root.getChildren().add(displayLesson("Lesson " + counter));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        if (counter == 4) { //
            ImageView nextButton = Tools.createButton(root, "MainMenu/Buttons/", "x", 650, 300, 40);
            nextButton.setOnMouseClicked(e -> {
                counter += 1;
                ChangeScene.changeToDeficiencyRoom(stage);
            });
        } else {
            // add button
            ImageView backButton = Tools.createButton(root, "MainMenu/Buttons/", "x", 650, 170, 40);
            backButton.setOnMouseClicked(e -> {
                // increments counter for the different rooms
                if (counter < defRooms.length + 1) counter++;
                else counter = 1;
                System.out.println(counter);
                ChangeScene.changeToSchool(stage, 0);
            });
        }

    }
}
