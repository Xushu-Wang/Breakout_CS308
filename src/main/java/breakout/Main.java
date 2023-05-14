package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private LevelScene currentScene;
    private int currentLevel = 0;
    private final int NUMLEVEL = 5;
    public final int FRAMES_PER_SECOND = 100;
    public final double SECOND_DELAY = 1.0/FRAMES_PER_SECOND;
    private LevelScene[] sceneGroup = new LevelScene[NUMLEVEL];
    private InitialScene opening;
    private EndScene end;

    // Create opening scene, end scene, and scene for each level
    public void initialization(){
        opening = new InitialScene();
        end = new EndScene();

        sceneGroup[0] = opening;
        sceneGroup[NUMLEVEL - 1] = end;

        for(int i=1; i<NUMLEVEL - 1; i++){
            GameScene g = new GameScene(i);
            sceneGroup[i] = g;
        }
        currentScene = sceneGroup[currentLevel];
    }

    // set the opening scene

    public void start (Stage stage) {
        initialization();

        Scene myScene = currentScene.getScene();
        stage.setScene(myScene);
        stage.show();

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> sceneController(stage)));
        animation.play();
    }

    // animation for each second

    public void step(double elaspedTime){
        sceneGroup[currentLevel].step(elaspedTime);
    }

    // scene controller, determine which scene should be switched to
    public void sceneController(Stage stage){
        if(sceneGroup[currentLevel].nextScene()){
            if(currentLevel == 3){
                end.checkSuccess();
                end.setUpScene();
                currentLevel ++;
            }
            else if(currentLevel == 4){
                currentLevel = 0;
                for(int i=0; i<NUMLEVEL; i++){
                    sceneGroup[currentLevel].setNextScene(false); // switch to first scene, reset the game
                }
            }
            else{
                currentLevel ++; // advance to next scene;
            }

            stage.setScene(sceneGroup[currentLevel].getScene());
            stage.show();
        }
        else{
            step(SECOND_DELAY); // same scene continues
        }

        if(sceneGroup[currentLevel].isLost()){
            currentLevel = 4;
            stage.setScene(sceneGroup[currentLevel].getScene()); // if lost, move to the last scene
            stage.show();
        }

    }
    public static void main (String[] args) {
        launch(args);
    }

}
