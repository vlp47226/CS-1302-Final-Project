package cs1302.arcade;

import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;

/**
 *This method holds a RightLane for Frogger. This lane starts on the right. It extends Group.
 */
public class RightLane extends Group{
    Rectangle r;
    Car[] cars;
    Color special;

    /**
     *This constructor sets up the RightLane.
     *
     *@param x the x value of the lane.
     *@param y the y value of the lane.
     *@param carX the x value of the first Car
     */
    public RightLane(int x, int y,int carX){
        super();
        special = new Color(0.3451,0.3451,0.3451,1);
        r = new Rectangle(1280.0,50.0);
        r.setX(x);
        r.setY(y);
        cars = new Car[3];
        for(int i = 0; i < 3; i++){
            cars[i] = new Car();
        }
        cars[0].setX(carX);
        cars[0].setY(y);
        cars[1].setX(carX+200);
        cars[1].setY(y);
        cars[2].setX(carX+400);
        cars[2].setY(y);
        for(Car c: cars){
            if(c.getX()>650){c.setVisible(false);}
        }
        r.setFill(special);
        this.getChildren().addAll(r,cars[0],cars[1],cars[2]);
    }

    /**
     *This method gets the array of Cars in the lane.
     *
     *@return the array of Cars.
     */
    public Car[] getCar(){
        return cars;
    }
}
