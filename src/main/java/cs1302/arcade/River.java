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

public class River extends Group {

    Log[] logList;
    Color water;
    Rectangle r;

    public River(int x, int y, int logX) {
        super();
        water = Color.AQUA;
        r = new Rectangle(650.0, 50.0);
        logList = new Log[3];
         r.setX(x);
         r.setY(y);
        for (int i = 0; i < 3; i++) {
            logList[i] = new Log();   
        }
        logList[0].setX(logX);
        logList[0].setY(y);
        logList[1].setX(logX + 200.0);
        logList[1].setY(y);
        logList[2].setX(logX + 400.0);
        logList[2].setY(y);
         r.setFill(water);
         this.getChildren().addAll(r, logList[0], logList[1],logList[2]);
         for (int i = 0; i < 3; i++) {
             logList[i].toFront();
         }

        
    }


    public Log[] getLogs() {
        return logList;
    }

    
    public double getY(){
        return r.getY();
    }










}//River.java
