package breakout;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Node;

/**
 * The Paddle Class, for creating a user Paddle in the scene.
 *
 * @author Andy Wang
 */

public class Paddle {

    // Position Parameters
    private double xpos;
    private final double YPOS = 700;

    // Size parameters, mutable length, fixed height.
    private final int HEIGHT = 10;
    private final int sceneSize = 800;
    private double length;

    // Image Parameters

    private ImageView paddle;

    private static final String PADDLE_IMAGE = "Paddle.png";

    // Constructor
    public Paddle(double x, double len){
        xpos = x - length/2;
        length = len;
        paddle = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE)));
        paddle.setX(xpos);
        paddle.setY(YPOS);
        paddle.setFitWidth(length);
        paddle.setFitHeight(HEIGHT);
    }

    // Movement of Paddle, including Boundary Check
    public void setPosition(double x){
        if(x <= 0){
            paddle.setX(0);
            xpos = 0;
        }
        else if(x >= sceneSize - length){
            paddle.setX(sceneSize - length);
            xpos = sceneSize - length;
        }
        else{
            paddle.setX(x);
            xpos = x;
        }

    }

    private boolean Intersecting (ImageView a, ImageView b) {
        return b.getBoundsInParent().intersects(a.getBoundsInParent());
    }

    // determine if the paddle receives a power up.
    public boolean receivePowerUp(PowerUp power){
        if(Intersecting(paddle, power.getImageView())){
            return true;
        }
        return false;
    }

    // Mutator and Getter, for Power Up purposes
    public void setLength(double len){
        length = len;
    }
    public double getLength(){
        return length;
    }

    public double getX(){
        return xpos;
    }
    public Node getNode(){
        return paddle;
    }

    public ImageView getImageView(){
        return paddle;
    }
}
