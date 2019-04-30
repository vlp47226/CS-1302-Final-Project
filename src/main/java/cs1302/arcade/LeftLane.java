package cs1302.arcade;

import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class LeftLane extends Group{
    Rectangle r;
    Car[] cars;
    Color special;
    Frogger f;
    public LeftLane(int x, int y){
        super();
        f = new Frogger();
        special = new Color(0.3451,0.3451,0.3451,1);
        r = new Rectangle(1280.0,50.0);
        r.setX(x);
        r.setY(y);
        cars = new Car[3];
        for(int i = 0; i < 3; i++){
            cars[i] = new Car();
        }
        cars[0].setX(r.getX());
        cars[0].setY(r.getY());
        cars[1].setX(r.getX()-200);
        cars[1].setY(r.getY());
        cars[2].setX(r.getX()-400);
        cars[2].setY(r.getY());
        for(Car c: cars){
            if(c.getX()<0){c.setVisible(false);}
        }
        r.setFill(special);
        this.getChildren().addAll(r,cars[0],cars[1],cars[2]);
    }
    public Car[] getCar(){
        return cars;
    }
    public void move(){
        EventHandler<ActionEvent> handler = event ->{
            for(Car c : cars){
                if(c.getX()+1>=0&&(!c.isVisible())){c.setVisible(true);}
                c.setX(c.getX()+1);
                if(c.getX()==650){
                    c.setX(r.getX()-50);
                    c.setVisible(false);
                }   
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
