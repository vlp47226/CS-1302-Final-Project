package cs1302.arcade;

import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class LeftLane extends Group{
    Rectangle r;
    Car c;
    Color special;
    public LeftLane(){
        super();
        special = new Color(0.3451,0.3451,0.3451,1);
        r = new Rectangle(1280.0,100.0);
        r.setX(0);
        r.setY(0);
        c = new Car();
        c.setX(0);
        c.setY(0);
        r.setFill(special);
        this.getChildren().addAll(r,c);
    }
    public Car getCar(){
        return c;
    }
}
