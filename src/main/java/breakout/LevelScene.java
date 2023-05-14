package breakout;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * The LevelScene Interface, abstraction for GameScene, InitialScene, and EndScene.
 *
 * @author Andy Wang
 */

public interface LevelScene {

    // Scene parameter
    public final int SCENEHEIGHT = 800;
    public final int SCENEWIDTH = 600;

    // Font paramter used in the scene
    public final String fontFamily = "Comic Sans MS";
    public final Color fontColor = Color.ORANGERED;

    public Scene setUpScene(); // set up each scene in each level
    public Scene getScene();
    public void step(double elapsedTime);
    public boolean nextScene(); // determine whether we should advance to next scene
    public boolean isLost(); // determine whether the game is lost
    public void setNextScene(boolean next); // mutator
}
