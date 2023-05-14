package breakout;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Node;

public class PowerUp {

    private int type; // type of powerup

    // Dropping time/position/speed
    private double droppingTime;
    private double droppingPosition;
    private double droppingSpeed;

    // size parameter
    private final int sceneSize = 800;
    private final double SIZE = 20;

    // time paramter
    private double Timer = 0;
    private final int TIMELIMIT = 20;
    private String powerUpImage;
    private ImageView powerup;

    //Initialize type, power up speed, and dropping time.
    public PowerUp(int type){
        this.type = type;
        switch(type){
            case 1:
                powerUpImage = "PowerUpHeart.png";
                break;
            case 2:
                powerUpImage = "PowerUpKey.png";
                break;
            case 3:
                powerUpImage = "PowerUpBiggerBall.png";
                break;
        }
        powerup = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(powerUpImage)));

        droppingTime = TIMELIMIT * Math.random();
        droppingPosition = (sceneSize - SIZE) * Math.random();
        powerup.setFitHeight(SIZE);
        powerup.setFitWidth(SIZE);
        powerup.setX(droppingPosition);
        powerup.setY(-SIZE);
    }

    // Drop the power up when it reaches the time.

    public void move(double elaspedTime){
        Timer += elaspedTime;
        if(Timer > droppingTime){
            powerup.setY(powerup.getY() + elaspedTime * droppingSpeed);
        }
        if(powerup.getY() > sceneSize - SIZE){
            powerup.setX(1500);
            powerup.setY(1500);
        }
    }

    // remove the power up if it hits the ground
    public void destroy(){

        powerup.setX(1500);
        powerup.setY(1500);
    }

    // Mutator and Accessor

    public void setSpeed(double s){
        droppingSpeed = s;
    }

    public int getType(){
        return type;
    }
    public ImageView getImageView(){
        return powerup;
    }
    public Node getNode(){
        return powerup;
    }

}
