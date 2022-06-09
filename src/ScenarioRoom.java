/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * ScenarioRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 3 (Aaron)
 * - extended CollisionRoom to create an abstract class for rooms in the school (escape level)
 * - adds items to the scene & allows them to be picked up
 */

/**
 * @author Aaron Zhu
 * May 30th, 2022
 * @version 3.0
 * Time: 30 minutes
 * Create ScanarioRoom.java
 * - extends CollisionRoom
 * - used for the rooms in the escape room that all have the same functions
 * Functions to be added:
 * - peer pressure scenario
 * - ability to pick up items from the rooms
 */

/**
 * @author Aaron Zhu
 * May 31st, 2022
 * @version 3.0
 * Time: 30 minutes
 * Make character restart at room entrance on load
 */

/**
 * @author Aaron Zhu
 * June 3rd, 2022
 * @version 3.0
 * Time: 1 hour
 * adding items for user to pick up
 * addItem() adds the item
 * removeItem() removes the item
 */

/**
 * @author Aaron Zhu
 * June 4th, 2022
 * @version 4.0
 * Time: 2 hours
 * parseItemData()
 * - reads the item data from the file
 * - adds items to scene accordingly
 */

/**
 * @author Aaron Zhu
 * June 5th, 2022
 * @version 4.0
 * Time: 2 hours
 * Dynamically render scenarios
 * - reads from text file
 * loadScenarios() loads the scenarios
 * playScenarios() plays the scenarios
 * createOptionText() --> helper method to create text for options
 * handleOptionOutcome() --> handles button presses based on status (read from text file)
 */

/**
 * Aaron Zhu
 * June 7th, 2022
 * @version 4.0
 * Time: 30 minutes
 * Changed panic room buttons
 * - created hover buttons ABC and right/wrong
 * Implemented the buttons into escape room scenarios
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ScenarioRoom extends CollisionRoom {

    /** warning textbox if user tries to do a move that is not allowed */
    public TextBox warning;

    /** backpack of the character */
    public Backpack backpack;

    /** each index points to an item, used to remove items */
    public ArrayList<ImageView> items;

    /** if the character has not left the door */
    private boolean firstTimeAtDoor = true;

    /** room name of the room */
    private final String roomName;

    /** which scenario to play */
    private final int scenarioNum;

    /** if the scenario is complete */
    private boolean scenarioComplete = false;

    /** arraylist of scenes to iterate through for scenario */
    private ArrayList<Scene> scenes;

    /** name of bg image */
    public String bg;

    /**
     * Constructor for ScenarioRoom.
     *
     * @param stage the primary stage for this application. Passed by reference.
     * @param backpack the backpack of the character. Passed by reference.
     * @param roomName the name of the room.
     * @param scenarioNum the scenario number.
     */
    public ScenarioRoom(Stage stage, Backpack backpack, String roomName, int scenarioNum) {
        super(stage);
        this.backpack = backpack;
        this.textBoxes = new TextBox[10];
        this.items = new ArrayList<>();
        this.scenes = new ArrayList<>();
        this.roomName = roomName;
        this.scenarioNum = scenarioNum;
        if (this.scenarioNum == -1) this.scenarioComplete = true;
    }

    /**
     * sets up the animation timer for scenario rooms
     */
    @Override
    public void setUpAnimationTimer() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // handle prompt
                int prompt = getPrompt();
                // toggle textbox visibility
                if (prompt != 1 && firstTimeAtDoor) {
                    firstTimeAtDoor = false;
                }
                if (!firstTimeAtDoor && !warning.isVisible() && prompt != 0) { // warning > prompts
                    textBoxOpen = prompt; // toggle on if not already
                    textBoxes[textBoxOpen].toggleOn();
                    if (keyPressed['e'] && prompt == 1) { // if the user presses e
                        textBoxes[textBoxOpen].toggleOff();
                        stop(); // stop the timer
                        character.setPosition(750, 395);
                        character.stopMovement(); // stop the character's movement
                        ChangeScene.changeToEscapeRoomSchool(); // change to escape room school (exit room)
                    } else if (keyPressed['p'] && prompt != 1 && prompt != 2) {
                        textBoxes[textBoxOpen].toggleOff();
                        removeItem(prompt - 3);
                    }
                } else if (textBoxOpen != 0) {
                    textBoxes[textBoxOpen].toggleOff();
                    textBoxOpen = 0;
                }
            }
        };
    }

    /**
     * restarts a scene
     */
    @Override
    public void startScene() {
        // reset keypress
        Arrays.fill(keyPressed, false);

        if (!scenarioComplete) { // starts the scenario
            loadScenarios();
            playScenarios();
            // scenarioComplete = true;
        } else startRoom();

    }

    /**
     * starts the room
     */
    private void startRoom() {
        backpack.changeRoom(this);

        character.startMovement();
        collisionTimer.start();
        stage.setScene(scene);
    }

    /**
     * adds the backpack to the scene
     */
    public void addBackpack() {
        fillCollisionGrid(8, 510, 83, 585);
        fillPromptGrid(0, 490, 103, 600, 2);
        textBoxes[2] = new TextBox(stage, root, scene, "Press on the backpack to see which items you have collected and which items you still need to collect!", "Blue");
        backpack.buildBackpack(root);
    }

    /**
     * reads file to add items to the room
     */
    public void parseItemData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/ItemData/" +  roomName + ".txt"));
            String line;
            int ct = 3;
            while (!(line = br.readLine()).equals("END")) {
                String[] split = line.split(", ");
                String name = split[0];
                String id = split[1];
                int x = Integer.parseInt(split[2]);
                int y = Integer.parseInt(split[3]);
                int size = Integer.parseInt(split[4]);
                int rad = Integer.parseInt(split[5]);
                int val = ct++;
                String message = split[6];
                addItem("Assets/School/Items/" + name + ".png", id, x, y, size, rad, val);
                textBoxes[val] = new TextBox(stage, root, scene, message, "Blue");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * adds an item to the room
     * @param path the path to the image of the item
     * @param id the id of the item
     * @param x the x coordinate of the item
     * @param y the y coordinate of the item
     * @param size the size of the item
     * @param rad the radius of the item's hitbox (prompt box)
     * @param val what value to use in fillPromptGrid()
     */
    public void addItem(String path, String id, int x, int y, int size, int rad, int val) {
        ImageView item = new ImageView(path);
        item.preserveRatioProperty().set(true);
        item.setX(x);
        item.setY(y);
        item.setFitWidth(size);
        item.setId(id);
        fillPromptGrid(x - rad, y - rad, x + size + rad, y + size + rad, val);
        root.getChildren().add(item);
        items.add(item);
    }

    /**
     * removes an item from the room
     * @param index the index of the item (in items) to remove
     */
    public void removeItem(int index) {
        int x = (int) items.get(index).getX();
        int y = (int) items.get(index).getY();
        fillPromptGrid(x - 75, y - 75, x + 75, y + 75, 0);
        backpack.foundItem(items.get(index).getId());
        root.getChildren().remove(items.get(index));;
    }

    /**
     * loads the scenarios for the room
     */
    private void loadScenarios() {
        try {
            scenes.clear();
            BufferedReader br = new BufferedReader(new FileReader("Data/ScenarioData/Scenario" + scenarioNum + ".txt"));
            int numSlides = Integer.parseInt(br.readLine());
            for (int i = 1; i <= numSlides; i += 1) { // slides
                String message = br.readLine();

                ImageView slide = Tools.createBackgroundImage("Assets/School/Scenarios/Scenario" + scenarioNum + "-" + i + ".png");
                Group root = new Group(slide);
                Scene scene = new Scene(root);

                TextBox textBox = new TextBox(stage, root, scene, message, "Purple");
                textBox.toggleOn();

                ImageView nextButton = Tools.createButton(root, "Assets/Buttons/", "next", 550, 520, 180);
                int finalI = i;
                nextButton.setOnMouseClicked(e -> {
                    stage.setScene(scenes.get(finalI));
                });
                scenes.add(scene);
            }


            // what to do
            Group root = new Group(Tools.createBackgroundImage("Assets/School/Scenarios/Scenario" + scenarioNum + "-" + numSlides + ".png"));
            Scene scene = new Scene(root);

            ImageView template = Tools.createBackgroundImage("Assets/School/Scenarios/ScenarioOptions.png");
            template.setOpacity(0.9);
            root.getChildren().add(template);

            String caption = br.readLine();
            TextBox textBox = new TextBox(stage, root, scene, caption, "Purple");
            textBox.toggleOn();

            ImageView optionA = Tools.createButton(root, "Assets/Buttons/ChoiceButtons/", "a", 60, 200, 40);
            String[] splitA = br.readLine().split("; ");
            Text textBoxA = createOptionText(splitA[0],110, 227);
            root.getChildren().add(textBoxA);

            ImageView optionB = Tools.createButton(root, "Assets/Buttons/ChoiceButtons/", "b", 60, 320, 40);
            String[] splitB = br.readLine().split("; ");
            Text textBoxB = createOptionText(splitB[0],110, 347);
            root.getChildren().add(textBoxB);

            ImageView optionC = Tools.createButton(root, "Assets/Buttons/ChoiceButtons/", "c", 60, 440, 40);
            String[] splitC = br.readLine().split("; ");
            Text textBoxC = createOptionText(splitC[0],110, 467);
            root.getChildren().add(textBoxC);

            optionA.onMouseClickedProperty().set(e -> {
                showAnswers(root, optionA, optionB, optionC, splitA[1], splitB[1], splitC[1]);
                handleOptionOutcome(root, splitA[1], splitA[2]);
                Tools.createStaticImage(root, "Assets/pointer.png", 65, 170, 30);
                textBox.toggleOff();
            });
            optionB.onMouseClickedProperty().set(e -> {
                showAnswers(root, optionA, optionB, optionC, splitA[1], splitB[1], splitC[1]);
                handleOptionOutcome(root, splitB[1], splitB[2]);
                Tools.createStaticImage(root, "Assets/pointer.png", 65, 290, 30);
                textBox.toggleOff();
            });
            optionC.onMouseClickedProperty().set(e -> {
                showAnswers(root, optionA, optionB, optionC, splitA[1], splitB[1], splitC[1]);
                handleOptionOutcome(root, splitC[1], splitC[2]);
                Tools.createStaticImage(root, "Assets/pointer.png", 65, 410, 30);
                textBox.toggleOff();
            });

            scenes.add(scene);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * plays the scenario
     */
    private void playScenarios() {
        Scene next = Tools.displayIntermissionText(stage, "SCENARIO TIME!");
        next.onMouseClickedProperty().set(e -> {
            stage.setScene(scenes.get(0));
        });
    }

    /**
     * creates the text for each option
     * @param text the text to display
     * @param x the x coordinate of the text
     * @param y the y coordinate of the text
     * @return the Text object
     */
    private Text createOptionText(String text, int x, int y) {
        Text option = new Text(text);
        option.setX(x);
        option.setY(y);
        option.setWrappingWidth(600);
        option.setFill(Color.BLACK);
        option.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        return option;
    }

    /**
     * handles the outcome of an option
     * @param option the option to handle
     * @param message the message to display
     */
    private void handleOptionOutcome(Group root, String option, String message) {
        TextBox textBox = new TextBox(stage, root, scene, message, "Blue");
        switch (option) {
            case "SUCCESS":
                textBox.setTextboxColour("Green");
                break;
            case "MID":
                textBox.setTextboxColour("Yellow");
                break;
            case "FAIL":
                textBox.setTextboxColour("Red");
                break;
        }
        textBox.toggleOn();
        ImageView nextButton = Tools.createButton(root, "Assets/Buttons/", "next", 550, 520, 180);
        nextButton.setOnMouseClicked(e -> {
            if (!option.equals("FAIL")) {
                startRoom();
                warning.setMessage("Back to the game...");
                warning.setTextboxColour("Blue");
                warning.setWarning(1);
            }
            else ChangeScene.changeToMainMenu();
        });
    }

    /**
     * display the answer to the question
     * @param q1 the first answer
     * @param q2 the second answer
     * @param q3 the third answer
     */
    private void showAnswers(Group root, ImageView q1, ImageView q2, ImageView q3, String optionA, String optionB, String optionC) {
        root.getChildren().removeAll(q1, q2, q3);

        if (optionA.equals("SUCCESS")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/aButtonRight.png", 60, 200, 40);
        else if (optionA.equals("MID")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/aButtonMid.png", 60, 200, 40);
        else if (optionA.equals("FAIL")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/aButtonWrong.png", 60, 200, 40);

        if (optionB.equals("SUCCESS")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/bButtonRight.png", 60, 320, 40);
        else if (optionB.equals("MID")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/bButtonMid.png", 60, 320, 40);
        else if (optionB.equals("FAIL")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/bButtonWrong.png", 60, 320, 40);

        if (optionC.equals("SUCCESS")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/cButtonRight.png", 60, 440, 40);
        else if (optionC.equals("MID")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/cButtonMid.png", 60, 440, 40);
        else if (optionC.equals("FAIL")) Tools.createStaticImage(root, "Assets/Buttons/ChoiceButtons/cButtonWrong.png", 60, 440, 40);
    }
}
