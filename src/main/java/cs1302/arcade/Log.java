package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 *This class holds the log for Frogger.It extends ImageView.
 */
public class Log  extends ImageView{
    /**
     *This constructor sets up the Log
     */
    public Log(){
        super();
        Image log = new Image("file:src/main/resources/log.png",100.0,90.0,true,true);
        this.setImage(log);
    }
}
