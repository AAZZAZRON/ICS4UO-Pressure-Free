import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Character {
    private ImageView character;
    private final Stage stage;
    private final int speed = 5;
    private boolean N, E, W, S;

    public Character(Stage stage) {
        this.stage = stage;
    }

    public void build(Group root, Scene scene) {
        character = new ImageView("Assets/Character/characterDown.png");
        character.setPreserveRatio(true);
        character.setFitWidth(100);
        root.getChildren().add(character);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    N = true;
                    break;
                case S:
                    S = true;
                    break;
                case A:
                    W = true;
                    break;
                case D:
                    E = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W:
                    N = false;
                    break;
                case S:
                    S = false;
                    break;
                case A:
                    W = false;
                    break;
                case D:
                    E = false;
                    break;
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (N) {
                    character.setY(character.getY() - speed);
                    changeCharacterDirection("Up");
                }
                if (S) {
                    character.setY(character.getY() + speed);
                    changeCharacterDirection("Down");
                }
                if (W) {
                    character.setX(character.getX() - speed);
                    changeCharacterDirection("Left");
                }
                if (E) {
                    character.setX(character.getX() + speed);
                    changeCharacterDirection("Right");
                }
            }
        };
        timer.start();
    }

    public void changeCharacterDirection(String direction) {
        character.setImage(new Image("Assets/Character/character" + direction + ".png"));
    }
}
