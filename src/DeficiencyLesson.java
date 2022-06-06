/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * DeficiencyLesson.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
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
 *
 * Version 3 (Sion)
 * - move fade transition to Tools.java
 * - change lesson naming scheme
 *
 * Version 3 (Aaron)
 * - change lesson naming scheme
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
 */

/**
 * @author Sion Gang, Aaron Zhu
 * June 1st, 2022
 * @version 3.0
 * Time: 30 minutes
 * change lesson format from Lesson"slideNum" to Lesson "LessonNum"-"SlideNum"
 * - easier to read and understand for other programmers
 * getSlideNum() returns the ImageView of the slide to display
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DeficiencyLesson {

    /** The primary stage for this application. Passed by reference. */
    private Stage stage;

    /** current lesson to display */
    private final int lessonNum;

    /** current slide to display */
    private int slideNum = 1;

    /** how many slides the ith lesson has */
    private final int[] slideCount = {0, 1, 2, 2, 2};

    /**
     * Constructor for DeficiencyRoom.
     * @param stage The primary stage for this application. Passed by reference.
     * @param lessonNum The lesson to display.
     */
    public DeficiencyLesson(Stage stage, int lessonNum) {
        this.stage = stage;
        this.lessonNum = lessonNum;
    }

    /**
     * gets the slide to display
     * @param num The slide # (of lesson ${lessonNum}) to display
     * @return ImageView of the selected image
     */
    public ImageView getSlideNum(int num) {
        return Tools.createBackgroundImage("Assets/Scenes/Lessons/Lesson " + lessonNum + "-" + num + ".png");
    }

    /**
     * sets up the GUI for deficiency room
     * sets up deficiency room logic flow
     */
    public void deficiencyRoom() {
        ImageView bg = Tools.createBackgroundImage("Assets/School/Rooms/ClassBg.png");

        Group root = new Group(bg);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        ImageView slide = getSlideNum(slideNum);
        Tools.addFadeOn(slide);
        root.getChildren().add(slide);

        if (slideNum < slideCount[lessonNum]) { //
            ImageView nextButton = Tools.createButton(root, "Assets/Buttons/", "next", 550, 420, 180);
            Tools.addFadeOn(nextButton);
            nextButton.setOnMouseClicked(e -> {
                slideNum += 1;
                root.getChildren().remove(slide);
                root.getChildren().remove(nextButton);
                deficiencyRoom(); // recurse for the next slide
            });
        } else {
            // add button
            ImageView backButton = Tools.createButton(root, "Assets/Buttons/", "x", 690, 140, 40);
            Tools.addFadeOn(backButton);
            backButton.setOnMouseClicked(e -> {
                ChangeScene.changeToDeficiencyRoom();
            });
        }
    }
}
