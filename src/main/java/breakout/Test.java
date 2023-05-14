package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;

/**
 * The testing class for each of the components, including initial setting, testing bricks, paddle, etc.
 * Remark: This is just a TESTING CLASS, not related to the final display. The game controller is in MAIN.
 * This Class is not deleted since the author wants to keep track of all of the previous thoughts and code.
 *
 * @author Andy Wang
 * Date: Jan 12th 2023
 */
public class Test extends Application {
    // useful names for constant values used
    public static final String TITLE = "BreakOut";
    public static final int SIZE = 600;
    private Scene myScene;
    public static final Paint BACKGROUND = Color.BLACK;
    public static final int FRAMES_PER_SECOND = 60;

    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    public Bouncer myBouncer;
    public Paddle myPaddle;
    public PowerUp p;

    public Brick myBrick;
    public static int BOUNCER_SPEED = -200;
    public int life = 3;
    public int level = 0;
    public List<Bouncer> bouncerList;
    public List<PowerUp> powerUpList;
    public List<Brick> brickList;


    @Override
    public void start (Stage stage) {

        myScene = setupScene(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    public Scene setupScene (int width, int height, Paint background) {
        myBouncer = new Bouncer(300, 485, 0, 0);
        myPaddle = new Paddle(250, 100);
        myBrick = new Brick(1, 100, 100);
        p = new PowerUp(2);

        Group root = new Group();
        root.getChildren().add(myBouncer.getNode());
        root.getChildren().add(myPaddle.getNode());
        root.getChildren().add(myBrick.getNode());
        root.getChildren().add(p.getNode());

        myScene = new Scene(root, width, height, background);

        myScene.setOnKeyPressed(e -> KeyInput(e.getCode()));
        return myScene;
    }

    private void step (double elapsedTime) {
        // update "actors" attributes a little bit at a time and at a "constant" rate (no matter how many frames per second)
        myBouncer.move(elapsedTime);
        p.move(elapsedTime);

        if(myBrick.isHit(myBouncer)){
            myBouncer.setSpeed(myBouncer.getXspeed(), -myBouncer.getYspeed());
            myBrick.destroy();
        }
        myBouncer.hitPaddle(myPaddle);
        if(myPaddle.receivePowerUp(p)){
            p.destroy();
        }
    }

    private void KeyInput (KeyCode code){
        switch (code) {
            case R -> myBouncer.setPosition(0, 0);
            case ENTER -> myBouncer.setSpeed(BOUNCER_SPEED, BOUNCER_SPEED);
            case LEFT -> myPaddle.setPosition(myPaddle.getX() - 10);
            case RIGHT -> myPaddle.setPosition(myPaddle.getX() + 10);
        }
    }

    private boolean Intersecting (ImageView a, ImageView b) {
        return b.getBoundsInParent().intersects(a.getBoundsInParent());
    }

    public static void main (String[] args) {
        launch(args);
    }

}
