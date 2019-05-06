package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Log  extends ImageView{
    public Log(){
        super();
        Image log = new Image("file:src/main/resources/log.png",100.0,90.0,true,true);
        this.setImage(log);
    }
}
