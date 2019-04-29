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
    LeftLane l2;
    LeftLane l3;
    LeftLane l4;
    ImageView frogBoy;
    Rectangle r;
    public int lives;
    Text lifeCounter;
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
        VBox v = new VBox();
        lives = 3;
        lifeCounter = new Text("Lives: " + lives);
        
        frog = new Group();
        Image frogg = new Image("file:src/main/resources/frog.png",50.0,50.0,true,true);
        frogBoy = new ImageView(frogg);
        frogBoy.setX(325);
        frogBoy.setY(640);
        l = new LeftLane(0,((int)frogBoy.getY())-50);
        l2 = new LeftLane(0,((int)frogBoy.getY())-100);
        l3 = new LeftLane(0,((int)frogBoy.getY())-150);
        l4 = new LeftLane(0,((int)frogBoy.getY())-200);
        frog.getChildren().add(frogBoy);
        frog.setOnKeyPressed(createKeyHandler());
        r = new Rectangle(650.0,700.0);
        r.setX(0);
        r.setY(0);
        r.setFill(Color.GREEN);
        frogger.getChildren().addAll(frog, l,l2,l3,l4, r);
        v.getChildren().addAll(lifeCounter,frogger);
        l.toFront();
        l2.toFront();
        l3.toFront();
        l4.toFront();
        frog.toFront();
        
        Scene scene = new Scene(v, 650, 720);
        this.setTitle("Frogger");
        this.setScene(scene);
        this.sizeToScene();
        frog.requestFocus();
    }
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            System.out.println(event);
            if (event.getCode() == KeyCode.LEFT){
                if(!(frogBoy.getX()-50.0<=0.0)){
                    frogBoy.setX(frogBoy.getX() - 50.0);
                    reset();
                }
            }
            if (event.getCode() == KeyCode.RIGHT){
                if(!(frogBoy.getX()+50.0>=600.0)){
                    frogBoy.setX(frogBoy.getX() + 50.0);
                    reset();
                }
            }
            if (event.getCode() == KeyCode.UP){
                if(!(frogBoy.getY()-50.0<=0.0)){
                    frogBoy.setY(frogBoy.getY() - 50.0);
                    reset();
                }
            }
            if (event.getCode() == KeyCode.DOWN){
                if(!(frogBoy.getY()+50.0>=670.0)){
                    frogBoy.setY(frogBoy.getY() + 50.0);
                    reset();
                }
            }
            // TODO bounds checking
        };
    } // createKeyHandler
    public void reset(){
        if(frogBoy.intersects(l.getCar().getBoundsInLocal())){
            frogBoy.setX(325);
            frogBoy.setY(640);
            lives -=1;
            lifeCounter.setText("Lives: " + lives);
        }
        if(frogBoy.intersects(l2.getCar().getBoundsInLocal())){
            frogBoy.setX(325);
            frogBoy.setY(640);
            lives -=1;
            lifeCounter.setText("Lives: " + lives);
        }
        if(frogBoy.intersects(l3.getCar().getBoundsInLocal())){
            frogBoy.setX(325);
            frogBoy.setY(640);
            lives -=1;
            lifeCounter.setText("Lives: " + lives);
        }
        if(frogBoy.intersects(l4.getCar().getBoundsInLocal())){
            frogBoy.setX(325);
            frogBoy.setY(640);
            lives -=1;
            lifeCounter.setText("Lives: " + lives);
        }
    }
}
