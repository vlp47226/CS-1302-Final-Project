package cs1302.arcade;

//imports
import javafx.event.ActionEvent;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;
import java.lang.Thread;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Frogger extends Stage{
    Group frogger;
    Group frog;
    LeftLane l;
    ImageView frogBoy;
    Rectangle r;
    public Frogger(){
        super();
    }
    public void start(){
        /* You are allowed to rewrite this start method, add other methods,
         * files, classes, etc., as needed. This currently contains some
         * simple sample code for mouse and keyboard interactions with a node
         * (rectangle) in a group.
         */
        frogger = new Group();
        l = new LeftLane();
        frog = new Group();
        Image frogg = new Image("file:src/main/resources/frog.png",50.0,50.0,true,true);
        frogBoy = new ImageView(frogg);
        frogBoy.setX(640);
        frogBoy.setY(670);                                
        frog.getChildren().add(frogBoy);
        frog.setOnKeyPressed(createKeyHandler());
        r = new Rectangle(1280.0,620.0);
        r.setX(0);
        r.setY(100);
        r.setFill(Color.GREEN);
        frogger.getChildren().addAll(frog, l, r);
        frog.toFront();
        
        Scene scene = new Scene(frogger, 1280, 720);
        this.setTitle("Frogger");
        this.setScene(scene);
        this.sizeToScene();
        frog.requestFocus();
    }
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            System.out.println(event);
            if (event.getCode() == KeyCode.LEFT){
                frogBoy.setX(frogBoy.getX() - 50.0);
                if(frogBoy.intersects(l.getCar().getBoundsInLocal())){
                    frogBoy.setX(640);
                    frogBoy.setY(670);
                }
            }
            if (event.getCode() == KeyCode.RIGHT){
                frogBoy.setX(frogBoy.getX() + 50.0);
                if(frogBoy.intersects(l.getCar().getBoundsInLocal())){
                    frogBoy.setX(640);
                    frogBoy.setY(670);
                }
            }
            if (event.getCode() == KeyCode.UP){
                frogBoy.setY(frogBoy.getY() - 50.0);
                if(frogBoy.intersects(l.getCar().getBoundsInLocal())){
                    frogBoy.setX(640);
                    frogBoy.setY(670);
                }
            }
            if (event.getCode() == KeyCode.DOWN){
                frogBoy.setY(frogBoy.getY() + 50.0);
                if(frogBoy.intersects(l.getCar().getBoundsInLocal())){
                    frogBoy.setX(640);
                    frogBoy.setY(670);
                }
            }
            // TODO bounds checking
        };
    } // createKeyHandler
}
