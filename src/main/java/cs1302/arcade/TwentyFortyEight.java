package cs1302.arcade;

//imports
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

public class TwentyFortyEight extends Application {

    Text gameName;
    Label label;
    public int score;
    VBox vbox;
    HBox hbox;
    TilePane tilePane;
    

    @Override
    public void start(Stage stage) {
        gameName = new Text("2048");
        gameName.setFont(Font.font("clear sans", 50));
        gameName.setFill(Color.BLACK);
        label = new Label("Score: 0");
        score = 0;
        vbox = new VBox();
        hbox = new HBox();
        tilePane = new TilePane(5, 5);
        tilePane.setPrefColumns(4);
        tilePane.setPrefRows(4);
        for(int i = 0; i < 16; i++){
            tilePane.getChildren().add(new VBox());
        }
        hbox.setSpacing(450.0);
        hbox.getChildren().addAll(gameName, label);
        tilePane.setOnKeyPressed(createKeyHandler()); // left-right key presses move the rectangle
        
        vbox.getChildren().addAll(hbox, tilePane);
        

        Scene scene = new Scene(vbox, 640, 480);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();



    }//start

    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            System.out.println(event);
            if (event.getCode() == KeyCode.LEFT){
                shiftToLeft();
                System.out.println("LEFT");
            }
            if (event.getCode() == KeyCode.RIGHT){
                shiftToRight();
                System.out.println("RIGHT");
            }
            if (event.getCode() == KeyCode.UP){
                shiftToUp();
                System.out.println("UP");
            }
            if (event.getCode() == KeyCode.DOWN){
                shiftToDown();
                System.out.println("DOWN");
            }
            // TODO bounds checking
        };
    } // createKeyHandler
    
    public void shiftToRight(){

    }

    public void shiftToLeft(){

    }

    public void shiftToUp(){

    }

    public void shiftToDown(){

    }

    public void addToScore(int x){
        score += x;
        label = new Label("Score: " + score);
        hbox.getChildren().set(1, label);
    }


}//TwentyFortyEight
