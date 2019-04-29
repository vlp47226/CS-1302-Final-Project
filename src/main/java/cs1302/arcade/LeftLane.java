package cs1302.arcade;

import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class LeftLane extends Group{
    Rectangle r;
    Car c;
    Color special;
    public LeftLane(int x, int y){
        super();
        special = new Color(0.3451,0.3451,0.3451,1);
        r = new Rectangle(1280.0,50.0);
        r.setX(x);
        r.setY(y);
        c = new Car();
        c.setX(r.getX());
        c.setY(r.getY());
        r.setFill(special);
        this.getChildren().addAll(r,c);
    }
    public Car getCar(){
        return c;
    }
}
