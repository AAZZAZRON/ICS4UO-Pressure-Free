/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * DeficiencyRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Sion)
 * - Deficiency Room that displays the different lessons
 * - create classroom background and deficiency room lessons (pixel art)
 * - fade in lessons
 * - mostly framework at this point
 *
 * Version 2 (Sion)
 * - finalizing Deficiency Room
 * - further implementation to DeficiencySchool
 * - implement multiple slide lessons
 * - implmenet better fade transition
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
 * - implement [next button] to go to next slide
 */
/**
 * @author Sion Gang
 * May 27th, 2022
 * @version 2.0
 * Time: 30 minutes
 *
 * deficiencyRoom()
 * - implement addition slides
 */

/**
 * @author Sion Gang
 * May 31th, 2022
 * @version 3.0
 * Time: 5 minutes
 * Implement the usage of Tools.fadeImage() to transition
 *
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class DeficiencyLesson {

    /** The primary stage for this application. Passed by reference. */
    private Stage stage;

    /** Stores transition */
    private FadeTransition fade = new FadeTransition();

    /** Stores counter for the lesson */
    private static int counter = 1;

    /** Stores array of deficiency rooms */
    private DeficiencyLesson[] defRooms = new DeficiencyLesson[7];

    /**
     * Constructor for DeficiencyRoom.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public DeficiencyLesson(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return ImageView of the selected image
     * @param path The path to the image
     */
    public ImageView displayScene (String path) {
        System.out.println("Assets/Scenes/Lessons/" + path + ".png");

        ImageView image = Tools.createBackgroundImage("Assets/Scenes/Lessons/"+path+".png");
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
        ImageView bg = Tools.createBackgroundImage("Assets/School/Rooms/ClassBg.png");
        // set scene
        Group root = new Group();
        // add background
        root.getChildren().add(bg);
        // add Lesson
        root.getChildren().add(Tools.fadeImage(new ImageView("Assets/School/Rooms/Lesson " + counter)));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        if (counter == 2 || counter == 4 || counter == 6) { //
            ImageView nextButton = Tools.createButton(root, "Assets/Buttons/", "next", 550, 430, 180);
            nextButton.setOnMouseClicked(e -> {
                counter += 1;
                ChangeScene.changeToDeficiencyLesson(stage);
            });
        } else {
            // add button
            ImageView backButton = Tools.createButton(root, "Assets/Buttons/", "x", 600, 170, 40);
            backButton.setOnMouseClicked(e -> {
                // increments counter for the different rooms
                if (counter < defRooms.length +1) counter++;
                else counter = 1;
                ChangeScene.changeToDeficiencyRoom(stage);
            });
        }
    }
}
