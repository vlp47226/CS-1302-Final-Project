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
import javafx.scene.control.Button;
import java.lang.Thread;
import javafx.application.Platform;

public class TwentyFortyEight extends Application {

    Text gameName;
    Label label;
    public int score;
    VBox vbox;
    HBox hbox;
    TilePane tilePane;
    Button newGame;
    public Block[] blocks;
    

    @Override
    public void start(Stage stage) {
        gameName = new Text("2048");
        gameName.setFont(Font.font("clear sans", 50));
        gameName.setFill(Color.BLACK);
        label = new Label("Score\n0");
        score = 0;
        vbox = new VBox();
        hbox = new HBox();
        blocks = new Block[16];
        newGame = new Button("New Game");
        tilePane = new TilePane();
        tilePane.setPrefColumns(4);
        tilePane.setPrefRows(4);
        tilePane.setPrefTileWidth(100.0);
        tilePane.setPrefTileHeight(100.0);
        tilePane.setHgap(5.0);
        tilePane.setVgap(5.0);
        testAddBlocks();
        hbox.setSpacing(200.0);
        hbox.getChildren().addAll(gameName, label);
        vbox.setOnKeyPressed(createKeyHandler()); // left-right key presses move the rectangle
        
        vbox.getChildren().addAll(hbox,newGame, tilePane);
        

        Scene scene = new Scene(vbox, 450, 580);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
        tilePane.requestFocus();


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
        boolean possible = false;
        for(int i = 0; i<16; i++){
            System.out.println(blocks[i].blockEmpty());
            if(blocks[i].blockEmpty()){
                possible = true;
            }
        }
        if(possible){
            System.out.println("Worked Here lol");
            for(int i = 2; i >= 0; i--){
                for (int j = i+1; j <= 3; j++) {
                    if(blocks[j-1].blockEmpty()==false&&blocks[j].blockEmpty()==true){
                        blocks[j] = blocks[j-1];
                        blocks[j-1] = new Block();      
                    }
                }
            }
            for(int i = 6; i >= 4; i--){
                for (int j = i+1; j <= 7; j++) {
                    if(blocks[j-1].blockEmpty()==false&&blocks[j].blockEmpty()==true){
                        blocks[j] = blocks[j-1];
                        blocks[j-1] = new Block();          
                    }
                }
            }
            for(int i = 10; i >= 8; i--){
                for (int j = i+1; j <= 11; j++) {
                    if(blocks[j-1].blockEmpty()==false&&blocks[j].blockEmpty()==true){
                        System.out.println(i);
                        blocks[j] = blocks[j-1];
                        blocks[j-1] = new Block();         
                    }
                }
            }
            for(int i = 14; i >= 12; i--){
                for (int j = i+1; j <= 15; j++) {
                    if(blocks[j-1].blockEmpty()==false&&blocks[j].blockEmpty()==true){
                        blocks[j] = blocks[j-1];
                        blocks[j-1] = new Block();         
                    }
                }
            }
        }
        tilePane = new TilePane();
        tilePane.setPrefColumns(4);
        tilePane.setPrefRows(4);
        tilePane.setPrefTileWidth(100.0);
        tilePane.setPrefTileHeight(100.0);
        tilePane.setHgap(5.0);
        tilePane.setVgap(5.0);
        for (int i = 0; i < 16; i++) {
            tilePane.getChildren().add(blocks[i]);
        }
        vbox.getChildren().set(2, tilePane);

    }

    public void shiftToLeft(){
        boolean possible = false;
        for(int i = 0; i<16; i++){
            System.out.println(blocks[i].blockEmpty());
            if(blocks[i].blockEmpty()){
                possible = true;
            }
        }
        if(possible){
            System.out.println("Worked Here lol");
            for(int i = 1; i <= 3; i++){
                for (int j = i-1; j >= 0; j--) {
                    if(blocks[j+1].blockEmpty()==false&&blocks[j].blockEmpty()==true){
                        blocks[j] = blocks[j+1];
                        blocks[j+1] = new Block();      
                    }
                }
            }
            for(int i = 5; i <= 7; i++){
                for (int j = i-1; j >= 4; j--) {
                    if(blocks[j+1].blockEmpty()==false&&blocks[j].blockEmpty()==true){
                        blocks[j] = blocks[j+1];
                        blocks[j+1] = new Block();          
                    }
                }
            }
            for(int i = 9; i <= 11; i++){
                for (int j = i-1; j >= 8; j--) {
                    if(blocks[j+1].blockEmpty()==false&&blocks[j].blockEmpty()==true){
                        System.out.println(i);
                        blocks[j] = blocks[j+1];
                        blocks[j+1] = new Block();         
                    }
                }
            }
            for(int i = 13; i <= 15; i++){
                for (int j = i-1; j >= 12; j--) {
                    if(blocks[j+1].blockEmpty()==false&&blocks[j].blockEmpty()==true){
                        blocks[j] = blocks[j+1];
                        blocks[j+1] = new Block();         
                    }
                }
            }
        }
        tilePane = new TilePane();
        tilePane.setPrefColumns(4);
        tilePane.setPrefRows(4);
        tilePane.setPrefTileWidth(100.0);
        tilePane.setPrefTileHeight(100.0);
        tilePane.setHgap(5.0);
        tilePane.setVgap(5.0);
        for (int i = 0; i < 16; i++) {
            tilePane.getChildren().add(blocks[i]);

        }
        vbox.getChildren().set(2,tilePane);

    }
    
    public void shiftToUp(){

    }

    public void shiftToDown(){

    }

    public void addToScore(int x){
        score += x;
        label = new Label("Score\n" + score);
        hbox.getChildren().set(1, label);
    }
    public void testAddBlocks(){
        blocks = new Block[]{new Block(2,false),new Block(),new Block(8,false),
                  new Block(16,false),new Block(),new Block(64,false),
                  new Block(128,false),new Block(256,false),new Block(),
                  new Block(1024,false),new Block(),new Block(2,false),
                             new Block(),new Block(2, false),new Block(),
                  new Block()};
        tilePane.getChildren().addAll(new Block(2,false),new Block(),new Block(8,false),
                                      new Block(16,false),new Block(),new Block(64,false),
                                      new Block(128,false),new Block(256,false),new Block(),
                                      new Block(1024,false),new Block(),new Block(2,false),
                                      new Block(),new Block(2,false),new Block(),new Block());
    }
}//TwentyFortyEight
