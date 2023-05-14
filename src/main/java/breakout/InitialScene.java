package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



/**
 * The InitialScene Class, for the Opening Scene and instruction of the Game.
 *
 * @author Andy Wang
 */

public class InitialScene implements LevelScene{
    private Scene initialScene;
    public boolean nextScene = false;

    public InitialScene(){
        setUpScene();
    }

    @Override
    public Scene setUpScene(){
        Group root = new Group();

        root.getChildren().add(setInstruction());
        root.getChildren().add(setTitle());
        root.getChildren().add(setAuthor());

        Image img = new Image(this.getClass().getClassLoader().getResourceAsStream("Background.png"));
        ImagePattern background = new ImagePattern(img);

        initialScene = new Scene(root, SCENEWIDTH, SCENEHEIGHT);
        initialScene.setFill(background);

        initialScene.setOnKeyPressed(e -> moveForward(e.getCode()));

        return initialScene;
    }

    //Instruction

    public Text setInstruction(){
        Text instruction = new Text();
        String text = "Press Enter to Start, Use <-/-> to control the Paddle";
        instruction.setFont(Font.font(fontFamily, FontPosture.ITALIC, 20));
        instruction.setText(text);
        instruction.setFill(fontColor);
        instruction.setX((SCENEWIDTH - instruction.getLayoutBounds().getWidth())/2);
        instruction.setY(300);
        return instruction;
    }

    //Title
    public Text setTitle(){
        Text title = new Text("BreakOut");

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        title.setEffect(is);

        title.setFont(Font.font(fontFamily, 45));

        title.setX((SCENEWIDTH - title.getLayoutBounds().getWidth())/2);
        title.setY(100);
        title.setFill(fontColor);

        return title;
    }

    //Author Tag

    public Text setAuthor(){
        Text author = new Text("Andy Wang");
        author.setFont(Font.font(fontFamily, FontWeight.BOLD, FontPosture.ITALIC,20));
        author.setX((SCENEWIDTH - author.getLayoutBounds().getWidth())/2);
        author.setY(150);
        author.setFill(fontColor);

        return author;
    }

    // Emoty step function, no animation

    public void step(double elapsedTime){}

    // Press enter to start the game

    public void moveForward(KeyCode code){
        switch(code){
            case ENTER -> nextScene = true;

        }
    }

    // Mutator and Accessor

    public boolean nextScene(){
        return nextScene;
    }
    public void setNextScene(boolean next){
        nextScene = next;
    }

    @Override
    public Scene getScene(){
        return initialScene;
    }
    public boolean isLost(){
        return false;
    }

}
