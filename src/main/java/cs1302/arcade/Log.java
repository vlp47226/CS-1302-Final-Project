package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Log  extends ImageView{
    public Log(){
        super();
        Image log = new Image("file:src/main/resources/Log.png",100.0,50.0,true,true);
        this.setImage(log);
    }
}
