package breakout;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


/**
 * The Brick Class, for creating bricks in the scene.
 *
 * @author Andy Wang
 */

public class Brick {

    // Type and Remaining health of the brick
    private int brickType;

    private int hit;

    // Position Parameter
    private double xpos;
    private double ypos;

    // Size Parameters

    private double brickLength = 25;
    private double brickHeight = 10;

    // Image Parameters
    private ImageView brick;
    public static String BRICK_IMAGE = "Brick.png";

    // Constructor for three different types of bricks, with different level of hardness.

    public Brick(int type, double x, double y){
        brickType = type;
        xpos = x;
        ypos = y;

        switch (type){
            case 1:
                hit = 1; // Normal Brick
                break;
            case 2:
                hit = 2; // Thicker Brick
                break;
            case 3:
                hit = -1; // Wall Brick, non-destroyable
                break;
        }
        brick = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE)));
        brick.setFitHeight(brickHeight);
        brick.setFitWidth(brickLength);
        brick.setX(x - brickLength/2);
        brick.setY(y - brickHeight/2);
    }

    // Same intersecting Method
    private boolean Intersecting (ImageView a, ImageView b) {
        return b.getBoundsInParent().intersects(a.getBoundsInParent());
    }

    // Check whether the brick is hit by a bouncer

    public boolean isHit(Bouncer Ball){
        if(Intersecting(Ball.getImageView(), brick)){
            hit --;
            return true;
        }
        return false;
    }

    // Destroy the Ball

    public void destroy(){
        brick.setX(1500);
        brick.setY(1500);
    }

    // Accessor

    public int getHit(){
        return hit;
    }

    public Node getNode(){
        return brick;
    }

    public double getHeight() {
        return brickHeight;
    }

    public double getLength() {
        return brickLength;
    }
}
