package breakout;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Node;


/**
 * The Bouncer Class, for creating bouncers in the scene.
 *
 * @author Andy Wang
 */


public class Bouncer {

    // Size parameters, one for bouncer, one for the scene
    private int size = 15;
    private final int SCENEHEIGHT = 800;
    private final int SCENEWIDTH = 600;

    // Speed parameters
    private double xspeed;
    private double yspeed;

    // Position parameters, including initial position for reset
    private double xpos;
    private final double INITIAL_XPOS = (SCENEWIDTH)/2;
    private final double INITIAL_YPOS =  (SCENEHEIGHT - 120);
    private double ypos;

    // Bouncer Image
    private ImageView Ball;
    public static final String BOUNCER_IMAGE = "Bouncer.png";

    // Constructor
    public Bouncer(double x, double y, double xs, double ys){
        xspeed = xs;
        yspeed = ys;
        Ball = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE)));
        Ball.setFitWidth(size);
        Ball.setFitHeight(size);
        Ball.setX(x - Ball.getFitWidth() / 2);
        Ball.setY(y - Ball.getFitHeight() / 2);
    }

    // Checking whether two images are intersecting each other
    private boolean Intersecting (ImageView a, ImageView b) {
        return b.getBoundsInParent().intersects(a.getBoundsInParent());
    }

    // Move according to the time, including boundary check
    public void move(double elapsedTime){
        Ball.setX(Ball.getX() + xspeed * elapsedTime);
        Ball.setY(Ball.getY() + yspeed * elapsedTime);

        if(Ball.getX() + size >= SCENEWIDTH || Ball.getX() <= 0){
            xspeed = -xspeed;
        }

        if(Ball.getY() <= 50) {
            yspeed = -yspeed;
        }

    }

    // Check if the ball is dead, if so, move back to its original position
    public boolean isDead(){
        if(Ball.getY() + size >= SCENEHEIGHT){
            Ball.setX(INITIAL_XPOS);
            Ball.setY(INITIAL_YPOS);
            xspeed = 0;
            yspeed = 0;
            return true;
        }
        return false;
    }

    // Check whether hit a paddle, if so, reverse velocity at y direction
    public void hitPaddle(Paddle myPaddle){
        if(Intersecting(Ball, myPaddle.getImageView())){
            yspeed = -yspeed;
        }
    }

    // Mutator and Getter

    public void setPosition(double x, double y){
        Ball.setX(x);
        Ball.setY(y);
    }

    public void setSpeed(double xs, double ys){
        xspeed = xs;
        yspeed = ys;
    }

    public void setSize(int s){
        size = s;
        Ball.setFitHeight(size);
        Ball.setFitHeight(size);
    }

    public double getX(){
        return xpos;
    }

    public double getY(){ return ypos; }

    public int getSize(){
        return size;
    }

    public double getXspeed(){
        return xspeed;
    }

    public double getYspeed(){
        return yspeed;
    }

    public Node getNode(){
        return Ball;
    }
    public ImageView getImageView(){
        return Ball;
    }
}
