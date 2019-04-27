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

public class TwentyFortyEight extends Application {

    Text gameName;
    Text direction;
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
        direction = new Text("Let's Move!");
        direction.setFont(Font.font("clear sans", 40));
        direction.setFill(Color.BLACK);
        label = new Label("Score\n0");
        score = 0;
        vbox = new VBox();
        hbox = new HBox();
        blocks = new Block[16];
        newGame = new Button("New Game");
        newGame.setOnAction(this::newGame);
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
        
        vbox.getChildren().addAll(hbox,newGame, tilePane, direction);
        

        Scene scene = new Scene(vbox, 450, 680);
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
        direction.setText("Right");
        boolean possible = false;
        for(int i = 0; i<16; i++){
            if (blocks[i].getIsEmpty()){possible = true;}
            else if (i < 3 && blocks[i+1].getValue()==blocks[i].getValue()){possible = true;}
            else if (i < 7 && blocks[i+1].getValue()==blocks[i].getValue()){possible = true;}
            else if (i < 11 && blocks[i+1].getValue()==blocks[i].getValue()){possible = true;}
            else if (i < 15 && blocks[i+1].getValue()==blocks[i].getValue()){possible = true;}
        }
        if(possible){
            System.out.println("Worked Here lol");
            shiftRightHelper(0,2);
            shiftRightHelper(4,6);
            shiftRightHelper(8,10);
            shiftRightHelper(12,14);
            randomPlace();
        }
        for(Block b: blocks){
            b.setIsCombined(false);
        }
        tilePane.getChildren().clear();
        for (int i = 0; i < 16; i++) {
            tilePane.getChildren().add(blocks[i]);
        }
        vbox.getChildren().set(2, tilePane);
        if(!checkPossible()){
            youLoseXD();
        }
        if(checkWin()){
            youWin();
        }

    }
    public void shiftRightHelper(int indexOne, int indexTwo){
        for(int i = indexTwo; i >= indexOne; i--){
            for (int j = i+1; j <= indexTwo+1; j++) {
                if(blocks[j-1].getIsEmpty()==false&&blocks[j].getIsEmpty()==true){
                    blocks[j] = blocks[j-1];
                    blocks[j-1] = new Block();      
                }
                else if(blocks[j-1].getIsEmpty()==false&&blocks[j].getIsEmpty()==false
                        && blocks[j].getValue() == blocks[j-1].getValue()
                        && blocks[j-1].isCombinedBlock()==false
                        && blocks[j].isCombinedBlock()==false){
                    blocks[j] = new Block((blocks[j-1].getValue()+blocks[j].getValue()),true);
                    blocks[j-1] = new Block();
                    addToScore(blocks[j].getValue());
                }
            }
        }
    }

    public void shiftToLeft(){
        direction.setText("Left");
        boolean possible = false;
        for(int i = 0; i<16; i++){
            if(blocks[i].getIsEmpty()){
                possible = true;
            }
            else if (i < 4 && i > 1 && blocks[i-1].getValue()==blocks[i].getValue()){
                possible = true;
            }
            else if (i < 8 && i > 5 && blocks[i-1].getValue()==blocks[i].getValue()){
                possible = true;
            }
            else if (i < 12 && i > 9 && blocks[i-1].getValue()==blocks[i].getValue()){
                possible = true;
            }
            else if (i < 16 && i > 13 && blocks[i-1].getValue()==blocks[i].getValue()){
                possible = true;
            }
        }
        if(possible){
            System.out.println("Worked Here lol");
            shiftLeftHelper(1,3);
            shiftLeftHelper(5,7);
            shiftLeftHelper(9,11);
            shiftLeftHelper(13,15);
            randomPlace();
        }
        for(Block b: blocks){
            b.setIsCombined(false);
        }
        tilePane.getChildren().clear();
        for (int i = 0; i < 16; i++){tilePane.getChildren().add(blocks[i]);}
        vbox.getChildren().set(2,tilePane);
        if(!checkPossible()){
            youLoseXD();
        }
        if(checkWin()){
            youWin();
        }
        
    }

    public void shiftLeftHelper(int indexOne, int indexTwo){
        for(int i = indexOne; i <= indexTwo; i++){
            for(int j = i-1; j >= indexOne-1; j--) {
                if(blocks[j+1].getIsEmpty()==false&&blocks[j].getIsEmpty()==true){
                    blocks[j] = blocks[j+1];
                    blocks[j+1] = new Block();      
                }
                else if(blocks[j+1].getIsEmpty()==false&&blocks[j].getIsEmpty()==false
                        && blocks[j].getValue() == blocks[j+1].getValue()
                        && blocks[j+1].isCombinedBlock()==false
                        && blocks[j].isCombinedBlock()==false){
                    blocks[j] = new Block((blocks[j+1].getValue()+blocks[j].getValue()),true);
                    blocks[j+1] = new Block();
                    addToScore(blocks[j].getValue());
                }
            }
        }
    }
    
    public void shiftToUp(){
        direction.setText("Up");
        boolean possible = false;
        for(int i = 0; i<16; i++){
            if(blocks[i].getIsEmpty()){
                possible = true;
            }
            else if (i > 4 && blocks[i-4].getValue()==blocks[i].getValue()){possible = true;}
        }
        if(possible){
            shiftUpHelper(4,12);
            shiftUpHelper(5,13);
            shiftUpHelper(6,14);
            shiftUpHelper(7,15);
            randomPlace();
        }
        for(Block b: blocks){
            b.setIsCombined(false);
        }
        tilePane.getChildren().clear();
        for (int i = 0; i < 16; i++) {
            tilePane.getChildren().add(blocks[i]);

        }
        vbox.getChildren().set(2,tilePane);
        if(!checkPossible()){
            youLoseXD();
        }
        if(checkWin()){
            youWin();
        }
    }

    public void shiftUpHelper(int indexOne, int indexTwo){
        for(int i = indexOne; i <= indexTwo; i=i+4){
            for (int j = i-4; j >= indexOne-4; j=j-4) {
                if(blocks[j+4].getIsEmpty()==false&&blocks[j].getIsEmpty()==true){
                    blocks[j] = blocks[j+4];
                    blocks[j+4] = new Block();         
                }
                else if(blocks[j+4].getIsEmpty()==false&&blocks[j].getIsEmpty()==false
                        && blocks[j].getValue() == blocks[j+4].getValue()
                        && blocks[j+4].isCombinedBlock()==false
                        && blocks[j].isCombinedBlock()==false){
                    blocks[j] = new Block((blocks[j+4].getValue()+blocks[j].getValue()),true);
                    blocks[j+4] = new Block();
                    addToScore(blocks[j].getValue());
                }
            }
        }
    }

    public void shiftToDown(){
        direction.setText("Down");
        boolean possible = false;
        for(int i = 0; i<16; i++){
            System.out.println(blocks[i].getIsEmpty());
            if(blocks[i].getIsEmpty()){
                possible = true;
            }
            else if (i < 12 && blocks[i+4].getValue()==blocks[i].getValue()){possible = true;}
        }
        if(possible){
            System.out.println("Worked Here lol");
            shiftDownHelper(0,8);
            shiftDownHelper(1,9);
            shiftDownHelper(2,10);
            shiftDownHelper(3,11);
            randomPlace();
        }
        for(Block b: blocks){
            System.out.println(b.getValue());
            b.setIsCombined(false);
        }
        tilePane.getChildren().clear();
        for (int i = 0; i < 16; i++) {
            tilePane.getChildren().add(blocks[i]);
        }
        vbox.getChildren().set(2, tilePane);
        if(!checkPossible()){
            youLoseXD();
        }
        if(checkWin()){
            youWin();
        }
    }

    public void shiftDownHelper(int indexOne, int indexTwo){
        for(int i = indexTwo; i >= indexOne; i = i-4){
            for (int j = i+4; j <= indexTwo+4; j = j+4) {
                if(blocks[j-4].getIsEmpty()==false&&blocks[j].getIsEmpty()==true){
                    blocks[j] = blocks[j-4];
                    blocks[j-4] = new Block();         
                }
                else if(blocks[j-4].getIsEmpty()==false&&blocks[j].getIsEmpty()==false
                        && blocks[j].getValue() == blocks[j-4].getValue()
                        && blocks[j-4].isCombinedBlock()==false
                        && blocks[j].isCombinedBlock()==false){
                    blocks[j] = new Block((blocks[j].getValue()+blocks[j-4].getValue()),true);
                    blocks[j-4] = new Block();
                    addToScore(blocks[j].getValue());
                }
            }
        }
    }

    public void addToScore(int x){
        score += x;
        label = new Label("Score\n" + score);
        hbox.getChildren().set(1, label);
    }
    public void testAddBlocks(){
        blocks = new Block[16];
        for(int i = 0; i < 16; i++){
            blocks[i]= new Block();
        }
        randomPlace();
        randomPlace();
        for(Block b: blocks){
            tilePane.getChildren().add(b);
        }
    }
    public void randomPlace(){
        boolean isPlaced = false;
        while(!isPlaced){
            int random = ThreadLocalRandom.current().nextInt(0, 16);
            if(blocks[random].getIsEmpty()){
                blocks[random] = new Block(2,false);
                isPlaced = true;
            }
        }
    }

    public void youLoseXD(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Lose!\nYour Score: "+score+
                             "\nPress New Game for a new Game, Exit the game to go back to menu.");
        alert.showAndWait();
    }
    public boolean checkPossible(){
        for(int i = 0; i < 16; i++){
            if(i >= 0 && i < 3){
                if(checkRight(i)||checkDown(i)||blocks[i].getValue()==0){return true;}
            }
            else if(i==3){if(checkDown(i)||blocks[i].getValue()==0){return true;}}
            else if(i >= 4 && i < 7){
                if(checkRight(i)||checkDown(i)||blocks[i].getValue()==0){return true;}
            }
            else if(i==7){if(checkDown(i)||blocks[i].getValue()==0){return true;}}
            else if(i >= 8 && i < 11){
                if(checkRight(i)||checkDown(i)||blocks[i].getValue()==0){return true;}
            }
            else if(i==11){if(checkDown(i)||blocks[i].getValue()==0){return true;}}
            else if(i >= 12 && i < 15){
                if(checkRight(i)||blocks[i].getValue()==0){return true;}
            }
            else if(i==15){if(blocks[i].getValue()==0){return true;}}
        }
        return false;
    }
    public boolean checkRight(int i){
        return blocks[i].getValue()==blocks[i+1].getValue();
    }
    public boolean checkLeft(int i){
        return blocks[i].getValue()==blocks[i-1].getValue();
    }
    public boolean checkUp(int i){
        return blocks[i].getValue()==blocks[i-4].getValue();
    }
    public boolean checkDown(int i){
        return blocks[i].getValue()==blocks[i+4].getValue();
    }
    public void youWin(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Win!\nYour Score: "+score+
                             "\nPress New Game for a new Game, Exit the game to go back to menu.");
        alert.showAndWait();
    }
    public boolean checkWin(){
        for(Block b : blocks){
            if(b.getValue()==2048){
                return true;
            }
        }
        return false;
    }

    public void newGame(ActionEvent e){
        score = 0;
        label = new Label("Score\n" + score);
        hbox.getChildren().set(1, label);
        tilePane.getChildren().clear();
        testAddBlocks();
        vbox.getChildren().set(2, tilePane);
    }
}//TwentyFortyEight
