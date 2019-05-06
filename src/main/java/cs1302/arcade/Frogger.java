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

public class Frogger extends Stage{
    Group frogger;
    Group frog;
    River river;
    River river2;
    River river3;
    LeftLane l;
    RightLane l2;
    LeftLane l3;
    RightLane l4;
    ImageView frogBoy;
    Rectangle r;
    public int lives;
    public int level;
    Text levelCounter;
    Text lifeCounter;
    HBox textHolder;
    public int score;
    Text scoreCounter;
    public Frogger(){
        super();
    }
    public void start(){
        textHolder = new HBox();
        frogger = new Group();
        VBox v = new VBox();
        lives = 3;
        level = 1;
        score = 150;
        lifeCounter = new Text("Lives: " + lives);
        levelCounter = new Text("Level: " + level);
        scoreCounter = new Text("Score: " + score);
        frog = new Group();
        Image frogg = new Image("file:src/main/resources/frog.png",50.0,50.0,true,true);
        frogBoy = new ImageView(frogg);
        frogBoy.setX(325);
        frogBoy.setY(640);
        r = new Rectangle(650.0, 430.0);
        r.setX(0);
        r.setY(0);
        r.setFill(Color.GREEN);
        l = new LeftLane(0,((int)frogBoy.getY())-50, 0);
        l2 = new RightLane(0,((int)frogBoy.getY())-100, 650);
        l3 = new LeftLane(0,((int)frogBoy.getY())-150, 0);
        l4 = new RightLane(0,((int)frogBoy.getY())-200, 650);
        river = new River(0, ((int) frogBoy.getY())-300, 100);
        river2 = new River(0, ((int) frogBoy.getY())-350,100);
        river3 = new River(0, ((int) frogBoy.getY())-400,100);
        frog.getChildren().add(frogBoy);
        frog.setOnKeyPressed(createKeyHandler());
        frogger.getChildren().addAll(frog,l ,l2 ,l3 ,l4 ,r, river, river2, river3);
        textHolder.getChildren().addAll(lifeCounter,scoreCounter, levelCounter);
        v.getChildren().addAll(textHolder,frogger);
        river.toFront();
        Thread t = new Thread(()->{
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
        t.setDaemon(true);
        t.start();
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
                }
            }
            if (event.getCode() == KeyCode.RIGHT){
                if(!(frogBoy.getX()+50.0>=600.0)){
                    frogBoy.setX(frogBoy.getX() + 50.0);
                }
            }
            if (event.getCode() == KeyCode.UP){
                if(!(frogBoy.getY()-50.0<=0.0)){
                    frogBoy.setY(frogBoy.getY() - 50.0);
                    nextLevel();
                }
            }
            if (event.getCode() == KeyCode.DOWN){
                if(!(frogBoy.getY()+50.0>=670.0)){
                    frogBoy.setY(frogBoy.getY() + 50.0);
                }
            }
            reset();// TODO bounds checking
        };
    } // createKeyHandler
    public void reset(){
        for(Car c : l.getCar()){
            if(frogBoy.intersects(c.getBoundsInLocal())){
                resetFrog();
            }
        }
        for(Car c : l2.getCar()){
            if(frogBoy.intersects(c.getBoundsInLocal())){
                resetFrog();
            }
        }
        for(Car c : l3.getCar()){
            if(frogBoy.intersects(c.getBoundsInLocal())){
                resetFrog();
            }
        }
        for(Car c : l4.getCar()){
            if(frogBoy.intersects(c.getBoundsInLocal())){
                resetFrog();
            }
        }
        
    }
    public void nextLevel(){
        if(frogBoy.getY()-50.0 <= 0){
            if(level+1 == 4){youWin();}
            else{
                frogBoy.setX(325);
                frogBoy.setY(640);
                score += 100;
                scoreCounter.setText("Score: " + score);
                level += 1;
                levelCounter.setText("Level: " + level);
                moveLeft(l);
                moveRight(l2);
                moveLeft(l3);
                moveRight(l4);
                moveLogLeft(river);
                moveLogRight(river2);
                moveLogLeft(river3);
            }
        }
    }
    public void resetFrog(){
        frogBoy.setX(325);
        frogBoy.setY(640);
        lives -=1;
        lifeCounter.setText("Lives: " + lives);
        score -= 50;
        scoreCounter.setText("Score: " + score);
        if(lives == 0){
            youLoseXD();
        }
    }
    public ImageView getFrogBoy(){
        return frogBoy;
    }

    public void moveLeft(LeftLane left){
        EventHandler<ActionEvent> handler = event ->{
            for(Car c : left.getCar()){
                if(c.getX()+1>=0&&(!c.isVisible())){c.setVisible(true);}
                c.setX(c.getX()+1);
                if(c.getX()==650){
                    c.setX(r.getX()-50);
                    c.setVisible(false);
                }
                if(c.intersects(frogBoy.getBoundsInLocal())){
                    resetFrog();
                }
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
        if(level == 2){
            keyFrame = new KeyFrame(Duration.millis(1000/90), handler);
        }
        if(level == 3){
            keyFrame = new KeyFrame(Duration.millis(1000/120), handler);
        }
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    public void moveRight(RightLane right){
        EventHandler<ActionEvent> handler = event ->{
            for(Car c : right.getCar()){
                if(c.getX()-1<550&&(!c.isVisible())){c.setVisible(true);}
                c.setX(c.getX()-1);
                if(c.getX()== 0){
                    c.setX(650+50);
                    c.setVisible(false);
                }
                if(c.intersects(frogBoy.getBoundsInLocal())){
                    resetFrog();
                }
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
        if(level == 2){
            keyFrame = new KeyFrame(Duration.millis(1000/90), handler);
        }
        if(level == 3){
            keyFrame = new KeyFrame(Duration.millis(1000/120), handler);
        }
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    public void moveLogLeft(River left){
        EventHandler<ActionEvent> handler = event ->{
            for(Log l : left.getLogs()){
                if(l.getX()+1>=0&&(!l.isVisible())){l.setVisible(true);}
                l.setX(l.getX()+1);
                if(l.getX()==650){
                    l.setX(r.getX()-50);
                    l.setVisible(false);
                }
                if(l.intersects(frogBoy.getBoundsInLocal())){
                    frogBoy.setX(frogBoy.getX()+1);
                }
            }
            int numOfLogsIntersect = 0;
            for(Log l : left.getLogs()){
                if(l.intersects(frogBoy.getBoundsInLocal())){
                    numOfLogsIntersect++;
                }
            }
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
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    public void moveLogRight(River right){
        EventHandler<ActionEvent> handler = event ->{
            for(Log l : right.getLogs()){
                if(l.getX()-1<550&&(!l.isVisible())){l.setVisible(true);}
                l.setX(l.getX()-1);
                if(l.getX()== 0){
                    l.setX(650+50);
                    l.setVisible(false);
                }
                if(l.intersects(frogBoy.getBoundsInLocal())){
                    frogBoy.setX(frogBoy.getX()-1);
                }
            }
            int numOfLogsIntersect = 0;
            for(Log l : right.getLogs()){
                if(l.intersects(frogBoy.getBoundsInLocal())){
                    numOfLogsIntersect++;
                }
            }
            if(numOfLogsIntersect == 0 && frogBoy.getY() == right.getY()){
                resetFrog();
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/(60+(30*(level-1)))), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void youWin(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Win!\nYour Score: "+score+
                             "\nPress New Game for a new Game, Exit the game to go back to menu.");
        alert.showAndWait();
        start();
    }

    public void youLoseXD(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Lose!\nYour Score: "+score+
                             "\nPress New Game for a new Game, Exit the game to go back to menu.");
        alert.showAndWait();
        start();
    }
}
