package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;

/**
 * The EndScene Class, for the end Scene and winning (or losing) text of the Game.
 *
 * @author Andy Wang
 */

public class EndScene implements LevelScene{
    public boolean Winning = false;
    public boolean nextScene = false;
    public Scene endScene;
    public EndScene(){
        setUpScene();
    }

    @Override
    public Scene setUpScene(){
        Group root = new Group();
        root.getChildren().add(setWinnerTag());

        endScene = new Scene(root, SCENEWIDTH, SCENEHEIGHT);
        endScene.setOnKeyPressed(e -> moveToFirstScene(e.getCode()));

        Image img = new Image(this.getClass().getClassLoader().getResourceAsStream("Background.png"));
        ImagePattern background = new ImagePattern(img);
        endScene.setFill(background);

        return endScene;
    }

    // Set the winning text at the middle of the screen.
    public Text setWinnerTag(){
        Text win = new Text();
        if(Winning){
            win.setText("Congratulations, You Win");
        }
        else{
            win.setText("Game Over, Try Again!");
        }
        win.setFont(Font.font("Verdana", 30));
        win.setX((SCENEWIDTH - win.getLayoutBounds().getWidth())/2);
        win.setY(SCENEHEIGHT/2);
        win.setFill(fontColor);

        return win;
    }

    // Empty step function, no animation
    public void step(double elaspedTime){}

    // Press enter to reload the game

    public void moveToFirstScene(KeyCode code){
        if(code == KeyCode.ENTER){
            nextScene = true;
        }
    }

    // Mutator and Accessor

    public boolean nextScene(){
        return nextScene;
    }
    public boolean isLost(){
        return false;
    }
    public void checkSuccess(){
        Winning = true;
    }
    public Scene getScene(){
        return endScene;
    }

    public void setNextScene(boolean next){
        nextScene = next;
    }

}
