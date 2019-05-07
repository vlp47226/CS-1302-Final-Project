package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 *This class holds the car for Frogger. It extends ImageView.
 */
public class Car extends ImageView{
    /**
     *This contructor sets up the car.
     */
    public Car(){
        super();
        Image hot = new Image("file:src/main/resources/car.png",100.0,50.0,true,true);
        this.setImage(hot);
    }
}
