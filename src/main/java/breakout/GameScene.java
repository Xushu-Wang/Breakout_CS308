package breakout;

import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.scene.Group;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The GameScene Class, including the scenes of three levels of the game.
 *
 * @author Andy Wang
 */

public class GameScene implements LevelScene{
    private Scene myScene;
    private int level;
    private final String backgroundImage = "Background.png";

    public List<PowerUp> powerUpList;
    private int numPowerUp;
    private Random Dice;
    public List<Bouncer> bouncerList;
    public List<Brick> brickList;
    public final String RESOURCE = "src/main/resources/";
    private String BrickStructure;
    public Bouncer myBouncer;
    public Paddle myPaddle;
    public double BouncerSpeed;
    public double PaddleSpeed;
    private double TimeLimit;
    private int score;
    private int life;

    public Text lifeLeft;
    public Text scoreText;
    public Text levelText;
    private boolean nextLevel = false;
    private boolean Lost = false;


    public GameScene(int level){
        this.level = level;
        initialization();
        switch (level){
            case 1:
                setParameters(5, 200, Integer.MAX_VALUE, 50, "level1.txt", 5);
                break;
            case 2:
                setParameters(3, 250, Integer.MAX_VALUE, 30,"level2.txt", 3);
                break;
            case 3:
                setParameters(1, 300, 5, 15, "level3.txt", 1);
                break;
        }
        setUpScene();
    }

    // Initialize score and different lists
    private void initialization(){
        score = 0;
        powerUpList = new ArrayList<>();
        brickList = new ArrayList<>();
        bouncerList = new ArrayList<>();
    }

    // Initialize parameters for each level

    private void setParameters(int numPowerUp, double BouncerSpeed, double TimeLimit, double PaddleSpeed, String BrickStructure, int life){
        this.numPowerUp = numPowerUp;
        this.BouncerSpeed = BouncerSpeed;
        this.TimeLimit = TimeLimit;
        this.PaddleSpeed = PaddleSpeed;
        this.BrickStructure = RESOURCE + BrickStructure;
        this.life = life;
    }

    //Set up Scene, including bouncer, paddle, brick, and powerup
    @Override
    public Scene setUpScene(){
        Group root = new Group();

        myBouncer = new Bouncer((SCENEWIDTH)/2, (SCENEHEIGHT - 100 - 10), 0, 0);
        bouncerList.add(myBouncer);
        myPaddle = new Paddle((SCENEWIDTH - 80)/2, 80);

        for(int i=0; i<numPowerUp; i++){
            Dice = new Random();

            PowerUp p = new PowerUp(Dice.nextInt(3) + 1);
            powerUpList.add(p);
            root.getChildren().add(p.getNode());
        }

        createBrickStructure(root, BrickStructure);

        root.getChildren().add(myBouncer.getNode());
        root.getChildren().add(myPaddle.getNode());
        root.getChildren().add(setLife());
        root.getChildren().add(setScore());
        root.getChildren().add(setLevel());

        myScene = new Scene(root, SCENEWIDTH, SCENEHEIGHT);

        Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(backgroundImage));
        ImagePattern background = new ImagePattern(img);
        myScene.setFill(background);

        myScene.setOnKeyPressed(e -> KeyInput(e.getCode()));

        return myScene;
    }

    // read the corresponding level file and create brick structure

    public void createBrickStructure(Group root, String brickStructure){

        File file = new File(brickStructure);

        try{
            Scanner scan = new Scanner(file);

            while (scan.hasNextInt()) {
                int type = scan.nextInt();
                int xPosition = scan.nextInt();
                int yPosition = scan.nextInt();

                Brick brick = new Brick(type, xPosition, yPosition);

                brickList.add(brick);
                root.getChildren().add(brick.getNode());
            }
            scan.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Score tag

    public Text setScore(){
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        scoreText = new Text();
        scoreText.setText("Score: " + score);

        scoreText.setEffect(is);

        scoreText.setFont(Font.font(fontFamily, FontWeight.BOLD, 15));
        scoreText.setFill(Color.ANTIQUEWHITE);
        scoreText.setX(SCENEWIDTH - scoreText.getLayoutBounds().getWidth() - 10);
        scoreText.setY(50);

        return scoreText;
    }

    public void updateScore(){
        scoreText.setText("Score: " + score);
    }

    // Life tag
    public Text setLife(){
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        lifeLeft = new Text();
        lifeLeft.setText("Life: " + life);

        lifeLeft.setEffect(is);

        lifeLeft.setFont(Font.font(fontFamily, FontWeight.BOLD, 15));
        lifeLeft.setFill(Color.ANTIQUEWHITE);
        lifeLeft.setX((SCENEWIDTH - lifeLeft.getLayoutBounds().getWidth())/2);
        lifeLeft.setY(50);

        return lifeLeft;
    }

    public void updateLife(){
        lifeLeft.setText("Life: " + life);
    }

    //Level tag
    public Text setLevel(){
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        levelText = new Text();
        levelText.setText("Level: " + level);

        levelText.setEffect(is);

        levelText.setFont(Font.font(fontFamily, FontWeight.BOLD, 15));
        levelText.setFill(Color.ANTIQUEWHITE);
        levelText.setX(10);
        levelText.setY(50);

        return levelText;
    }

    // Animation

    @Override
    public void step(double elapsedTime){
        for(Bouncer ball: bouncerList){
            ball.move(elapsedTime);
            ball.hitPaddle(myPaddle); // check whether hits the paddle
            if(ball.isDead()){ // check whether is below the paddle
                life --;
                updateLife();

                if(life <= 0){
                    Lost = true;
                }
            }
        }
        for(PowerUp power: powerUpList){
            power.move(elapsedTime);
            if(myPaddle.receivePowerUp(power)){ // check whether the paddle receives the powerup
                switch(power.getType()){
                    case 1:
                        life ++;
                        updateLife();
                        break;
                    case 2:
                        score ++;
                        updateScore();
                        break;
                    case 3:
                        myBouncer.setSize(myBouncer.getSize() + 5);
                        break;
                }
                power.destroy();
            }
        }
        for(Brick b: brickList){
            for(Bouncer bouncer: bouncerList) {
                if(b.isHit(bouncer)){ // check whether a bouncer hit the brick.
                    if(b.getHit() == 0){
                        b.destroy();
                        score ++;
                        updateScore();
                    }
                    bouncer.setSpeed(bouncer.getXspeed(), -bouncer.getYspeed());
                }
            }
        }

        checkNextLevel(); // check whether the game is finished

    }

    public void checkNextLevel(){
        boolean next = true;

        for(Brick b: brickList){
            if(b.getHit() > 0) next = false; // if any one of the bricks have positive hit (not destroyed yet), we stay on the game
        }

        nextLevel = next;
    }

    // Cheat key add power up
    public void addPowerUp(){
        Dice = new Random();
        int type = Dice.nextInt(3) + 1;
        PowerUp p = new PowerUp(type);
        powerUpList.add(p);
    }

    private void KeyInput (KeyCode code){
        switch (code) {
            // Control
            case R:
                myBouncer.setPosition(SCENEWIDTH/2, SCENEHEIGHT - 120);
                break;
            case ENTER:
                enterStart();
                break;
            case LEFT:
                myPaddle.setPosition(myPaddle.getX() - PaddleSpeed);
                break;
            case RIGHT:
                myPaddle.setPosition(myPaddle.getX() + PaddleSpeed);
                break;
            //Cheat Key
            case H:
                life ++;
                updateLife();
                break;
            case S:
                score *= 2;
                updateScore();
                break;
            case P:
                addPowerUp();
                break;
            case T:
                TimeLimit ++;
                break;
            case N:
                nextLevel = true;
                break;
        }
    }

    private void enterStart(){
        myBouncer.setSpeed(BouncerSpeed, -BouncerSpeed);
        for(PowerUp power: powerUpList){
            power.setSpeed(200);
        }
    }

    // Accessor and Mutator

    public boolean isLost(){
        return Lost;
    }

    public boolean nextScene(){
        return nextLevel;
    }
    public void setNextScene(boolean next){
        nextLevel = next;
    }

    public Scene getScene(){
        return myScene;
    }
}
