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
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

/**
 *This class holds the code for the Frogger Game. It extends Stage.
 */
public class Frogger extends Stage{
    //The frogger group to hold everything
    Group frogger;
    //The frog itself
    Group frog;
    //The first river
    River river;
    //The second river
    River river2;
    //The third river
    River river3;
    //The first lane
    LeftLane l;
    //The second lane
    RightLane l2;
    //The third lane
    LeftLane l3;
    //The fourth lane
    RightLane l4;
    //The frog image
    ImageView frogBoy;
    //The green rectangle
    Rectangle r;
    //The lives and levels
    public int lives;
    public int level;
    //The level and lives counter, and the text holder
    Text levelCounter;
    Text lifeCounter;
    HBox textHolder;
    //The score, score holder
    public int score;
    Text scoreCounter;
    //The timeline for the animations
    Timeline timeline;

    /**
     *This is simply a simple constructor to call super.
     */
    public Frogger(){
        super();
    }

    /**
     *This method sets up the game.
     */
    public void start(){
        //Set up the timeline, text holder, frogger, and a vbox
        timeline = new Timeline();
        textHolder = new HBox();
        frogger = new Group();
        VBox v = new VBox();
        //Set up the lives, score, level, and the holders of each
        lives = 3;
        level = 1;
        score = 150;
        lifeCounter = new Text("Lives: " + lives);
        levelCounter = new Text("Level: " + level);
        scoreCounter = new Text("Score: " + score);
        //Set up the frog group, the ImageView, set the x and y for the ImageView
        frog = new Group();
        Image frogg = new Image("file:src/main/resources/frog.png",50.0,50.0,true,true);
        frogBoy = new ImageView(frogg);
        frogBoy.setX(325);
        frogBoy.setY(640);
        //Set up the Rectangle
        r = new Rectangle(650.0, 430.0);
        r.setX(0);
        r.setY(0);
        r.setFill(Color.GREEN);
        //Set the lanes and rivers, add the ImageView to frog, and set on key pressed
        setLanesAndRivers();
        frog.getChildren().add(frogBoy);
        frog.setOnKeyPressed(createKeyHandler());
        //Add the children to the larger group, and the counters to the holder, with spacing
        frogger.getChildren().addAll(frog,l ,l2 ,l3 ,l4 ,r, river, river2, river3);
        textHolder.getChildren().addAll(lifeCounter,scoreCounter, levelCounter);
        textHolder.setSpacing(20.0);
        //Add the holder and group to the vbox
        v.getChildren().addAll(textHolder,frogger);
        //put the river to front, start the movement, and put the ImageView to the front
        river.toFront();
        startMovement();
        frog.toFront();
        //Set the scene, title, size, and resizable of the stage
        Scene scene = new Scene(v, 650, 720);
        this.setTitle("Frogger");
        this.setScene(scene);
        this.sizeToScene();
        this.setResizable(false);
        frog.requestFocus();
    }//start

    /**
     *This method holdes the events for the KeyHandler for the game.
     *
     *@return the EventHandler for the keys.
     */
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            //If left is hit
            if (event.getCode() == KeyCode.LEFT){
                //If it is possible, move the frog 50 points left
                if(!(frogBoy.getX()-50.0<=0.0)){
                    frogBoy.setX(frogBoy.getX() - 50.0);
                }//if
            }//if
            //If right is hit
            if (event.getCode() == KeyCode.RIGHT){
                //If it is possible, move the frog 50 points right
                if(!(frogBoy.getX()+50.0>=600.0)){
                    frogBoy.setX(frogBoy.getX() + 50.0);
                }//if
            }//if
            //If up is hit
            if (event.getCode() == KeyCode.UP){
                //If it is possible, move the frog 50 points up
                if(!(frogBoy.getY()-50.0<=0.0)){
                    frogBoy.setY(frogBoy.getY() - 50.0);
                    nextLevel();
                }//if
            }//if
            //If down it hit
            if (event.getCode() == KeyCode.DOWN){
                //If it is possible, move the frog 50 points down
                if(!(frogBoy.getY()+50.0>=670.0)){
                    frogBoy.setY(frogBoy.getY() + 50.0);
                }//if
            }//if
            reset();// TODO bounds checking
        };
    } // createKeyHandler

    /**
     *This method resets the frog is it is hit by a car.
     */
    public void reset(){
        //For every car in lane one
        for(Car c : l.getCar()){
            //if the frog hits, reset it. If you lose, call you lose
            if(frogBoy.intersects(c.getBoundsInLocal())){
                resetFrog();
                if(lives == 0){ youLoseXD();}
            }//if
        }//for
        //For every car in lane two
        for(Car c : l2.getCar()){
            //if the frog hits, reset it. If you lose, call you lose
            if(frogBoy.intersects(c.getBoundsInLocal())){
                resetFrog();
                if(lives == 0){youLoseXD();}
            }//if
        }//for
        //For every car in lane three
        for(Car c : l3.getCar()){
            //if the frog hits, reset it. If you lose, call you lose
            if(frogBoy.intersects(c.getBoundsInLocal())){
                resetFrog();
                if(lives == 0){youLoseXD();}
            }//if
        }//for
        //For every car in lane four
        for(Car c : l4.getCar()){
            //if the frog hits, reset it. If you lose, call you lose
            if(frogBoy.intersects(c.getBoundsInLocal())){
                resetFrog();
                if(lives == 0){youLoseXD();}
            }//if
        }//for
    }//reset

    /**
     *This method sets up the next level of the game.
     */
    public void nextLevel(){
        //If moving up more would put it at or below 0
        if(frogBoy.getY()-50.0 <= 0){
            //Set the frog back
            frogBoy.setX(325);
            frogBoy.setY(640);
            //Add 100 to the score, and change the score counter
            score += 100;
            scoreCounter.setText("Score: " + score);
            //If the next level would be four, you win
            if(level+1 == 4){youWin();}
            //else
            else{
                //Add to the level and change the counter
                level += 1;
                levelCounter.setText("Level: " + level);
                //Start the movement of all the lanes and rivers again
                moveLeft(l);
                moveRight(l2);
                moveLeft(l3);
                moveRight(l4);
                moveLogLeft(river);
                moveLogRight(river2);
                moveLogLeft(river3);
            }//else
        }//if
    }//nextLevel

    /**
     *This method resets the frog.
     */
    public void resetFrog(){
        //Set the frog back to the beginning
        frogBoy.setX(325);
        frogBoy.setY(640);
        //Remove one life and 50 points. If you are out of lives, you lose
        lives -=1;
        lifeCounter.setText("Lives: " + lives);
        score -= 50;
        scoreCounter.setText("Score: " + score);
        if(lives == 0){youLoseXD();}
    }//resetFrog

    /**
     *This method allows one to get the frog
     *
     *@return the frog
     */
    public ImageView getFrogBoy(){
        return frogBoy;
    }

    /**
     *This method takes a lane which starts on the left and moves it right.
     *
     *@param left the LeftLane.
     */
    public void moveLeft(LeftLane left){
        EventHandler<ActionEvent> handler = event ->{
            //For every car in the left
            for(Car c : left.getCar()){
                //Make it visible if it moves into frame
                if(c.getX()+1>=0&&(!c.isVisible())){c.setVisible(true);}
                //Move it and adjust it for frame
                c.setX(c.getX()+1);
                if(c.getX()==650){
                    c.setX(r.getX()-50);
                    c.setVisible(false);
                }
                //If it hits a frog, reset it
                if(c.intersects(frogBoy.getBoundsInLocal())){
                    resetFrog();
                }
            }
        };
        //Set up the level, then add it to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
        if(level == 2){
            keyFrame = new KeyFrame(Duration.millis(1000/90), handler);
        }
        if(level == 3){
            keyFrame = new KeyFrame(Duration.millis(1000/120), handler);
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     *This method takes a lane which starts on the right and moves it left.
     *
     *@param left the RightLane.
     */
    public void moveRight(RightLane right){
        EventHandler<ActionEvent> handler = event ->{
            //for every car in right
            for(Car c : right.getCar()){
                //Make it visible if it moves into frame
                if(c.getX()-1<550&&(!c.isVisible())){c.setVisible(true);}
                //Move it and adjust it for frame
                c.setX(c.getX()-1);
                if(c.getX()== 0){
                    c.setX(650+50);
                    c.setVisible(false);
                }
                //If it hits a frog, reset it
                if(c.intersects(frogBoy.getBoundsInLocal())){
                    resetFrog();
                }
            }
        };
        //Set up level one, two, and three, then add it to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
        if(level == 2){
            keyFrame = new KeyFrame(Duration.millis(1000/90), handler);
        }
        if(level == 3){
            keyFrame = new KeyFrame(Duration.millis(1000/120), handler);
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     *This method takes a river which starts on the left and moves it right.
     *
     *@param left the River.
     */
    public void moveLogLeft(River left){
        //Set up the handler
        EventHandler<ActionEvent> handler = event ->{
            //For every log in left
            for(Log l : left.getLogs()){
                if(l.getX()+1>=0&&(!l.isVisible())){l.setVisible(true);}
                l.setX(l.getX()+1);
                if(l.getX()==650){
                    l.setX(r.getX()-50);
                    l.setVisible(false);
                }
                //If it intersects the frog, move the frog as well
                if(l.intersects(frogBoy.getBoundsInLocal())){
                    frogBoy.setX(frogBoy.getX()+1);
                }
            }
            int numOfLogsIntersect = 0;
            //For every log in left
            for(Log l : left.getLogs()){
                //if it intersects, add to the integer
                if(l.intersects(frogBoy.getBoundsInLocal())){
                    numOfLogsIntersect++;
                }
            }
            //If the frog intersects none and the y is the same, reset it.
            if(numOfLogsIntersect == 0 && frogBoy.getY() == left.getY()){
                resetFrog();
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
        if(level == 2){
            keyFrame = new KeyFrame(Duration.millis(1000/90), handler);
        }
        if(level == 3){
            keyFrame = new KeyFrame(Duration.millis(1000/120), handler);
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     *This method takes a river which starts on the right and moves it left.
     *
     *@param left the River.
     */
    public void moveLogRight(River right){
        //Make a new Event handler
        EventHandler<ActionEvent> handler = event ->{
            //For every log in right
            for(Log l : right.getLogs()){
                //If it comes into frame, set it to visible
                if(l.getX()-1<550&&(!l.isVisible())){l.setVisible(true);}
                //Move it, and adjust if it moves out of frame
                l.setX(l.getX()-1);
                if(l.getX()== 0){
                    l.setX(650+50);
                    l.setVisible(false);
                }//if
                //If it intersects the frog, move the frog as well
                if(l.intersects(frogBoy.getBoundsInLocal())){
                    frogBoy.setX(frogBoy.getX()-1);
                }//if
            }//for
            //Set the int
            int numOfLogsIntersect = 0;
            //For every log in right
            for(Log l : right.getLogs()){
                //if it intersects, add to the integer
                if(l.intersects(frogBoy.getBoundsInLocal())){
                    numOfLogsIntersect++;
                }//if
            }//for
            //If the frog intersects none and the y is the same, reset it.
            if(numOfLogsIntersect == 0 && frogBoy.getY() == right.getY()){
                resetFrog();
            }
        };
        //Set up the keyframe, then add it to the timeline and play
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/(60+(30*(level-1)))), handler);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }//moveLogRight

    /**
     *This method sets up the winning of the user.
     */
    public void youWin(){
        //Stop the timeline, clear the keyFrames
        timeline.stop();
        timeline.getKeyFrames().clear();
        //Make a new Alert, show it, then call start again
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Win!\nYour Score: "+score+
                             "\nPress New Game for a new Game, Exit the game to go back to menu.");
        alert.getDialogPane().setPrefSize(400,200);
        alert.showAndWait();
        start();
    }//youWin

    /**
     *This method sets up the losing of the user.
     */
    public void youLoseXD(){
        //Stop the timelinne, clear the keyFrames
        timeline.stop();
        timeline.getKeyFrames().clear();
        //Make a new Alert, show it, then call start again
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Lose!\nYour Score: "+score+
                             "\nPress Ok for a new Game, Exit the game to go back to menu.");
        alert.getDialogPane().setPrefSize(400,200);
        alert.showAndWait();
        start();
    }//youLoseXD

    /**
     *This method sets the lanes and rivers for the start method
     */
    public void setLanesAndRivers(){
        //Set the lanes and rivers
        l = new LeftLane(0,((int)frogBoy.getY())-50, 0);
        l2 = new RightLane(0,((int)frogBoy.getY())-100, 650);
        l3 = new LeftLane(0,((int)frogBoy.getY())-150, 0);
        l4 = new RightLane(0,((int)frogBoy.getY())-200, 650);
        river = new River(0, ((int) frogBoy.getY())-300, 100);
        river2 = new River(0, ((int) frogBoy.getY())-350,100);
        river3 = new River(0, ((int) frogBoy.getY())-400,100);
    }//setLanesAndRivers

    /**
     *This method starts the movement of the lanes and rivers.
     */
    public void startMovement(){
        //Set up the thread
        Thread t = new Thread(()->{
                //Move every lane and river
                Platform.runLater(()->{
                        moveLeft(l);
                        moveRight(l2);
                        moveLeft(l3);
                        moveRight(l4);
                        moveLogLeft(river);
                        moveLogRight(river2);
                        moveLogLeft(river3);
                    });
        });
        //Start the thread
        t.setDaemon(true);
        t.start();
    }//startMovement
}//Frogger
