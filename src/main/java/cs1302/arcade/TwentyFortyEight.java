
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

/**
 *This class represents a game of 2048. It extends stage so it can show up
 *seperately from the {@code Menu}.
 */
public class TwentyFortyEight extends Stage{

    //Instance variables
    Text gameName;
    Text direction;
    Label label;
    public int score;
    VBox vbox;
    HBox hbox;
    TilePane tilePane;
    Button newGame;
    public Block[] blocks;

    /**
     *This constructor is a basic constructor which calls super.
     */
    public TwentyFortyEight(){
        super();
    }

    /**
     *This method starts the game of 2048. It sets all of the instance variables, scores,
     *placements, and the tilepane.
     */
    public void start() {
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
        vbox.setOnKeyPressed(createKeyHandler());
        vbox.getChildren().addAll(hbox,newGame, tilePane, direction);
        Scene scene = new Scene(vbox, 450, 680);
        this.setTitle("2048");
        this.setScene(scene);
        this.sizeToScene();
        this.setResizable(false);
        tilePane.requestFocus();


    }//start

    /**
     *This method holdes the events for the KeyHandler for the game.
     *
     *@return the EventHandler for the keys.
     */
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            if (event.getCode() == KeyCode.LEFT){
                Thread t = new Thread(()->{
                        Platform.runLater(()->{shiftToLeft();});
                });
                t.setDaemon(true);
                t.start();
            }
            if (event.getCode() == KeyCode.RIGHT){
                Thread t = new Thread(()->{
                        Platform.runLater(()->{shiftToRight();});
                });
                t.setDaemon(true);
                t.start();
            }
            if (event.getCode() == KeyCode.UP){
                Thread t = new Thread(()->{
                        Platform.runLater(()->{shiftToUp();});
                });
                t.setDaemon(true);
                t.start();
            }
            if (event.getCode() == KeyCode.DOWN){
                Thread t = new Thread(()->{
                        Platform.runLater(()->{shiftToDown();});
                });
                t.setDaemon(true);
                t.start();
            }
            // TODO bounds checking
        };
    } // createKeyHandler

    /**
     *This method shifts the blocks to the right when right is pushed
     */
    public void shiftToRight(){
        direction.setText("Right");
        boolean possible = false;
        for(int i = 0; i<16; i++){ //checks if any of the blocks can shift to the right
            if (blocks[i].getIsEmpty()){possible = true;} //checks for any empty blocks
            else if (i < 3 && blocks[i+1].getValue()==blocks[i].getValue()){possible = true;}
            else if (i < 7 && blocks[i+1].getValue()==blocks[i].getValue()){possible = true;}
            else if (i < 11 && blocks[i+1].getValue()==blocks[i].getValue()){possible = true;}
            else if (i < 15 && blocks[i+1].getValue()==blocks[i].getValue()){possible = true;}
            //each if statement checks for if the block and the one to the right can merge
        }
        if(possible){
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

    /**
     *This method shifts the blocks right by specified indices.
     *
     *@param indexOne the smaller of the two indices
     *@param indexTwo the larger of the two indices
     */
    public void shiftRightHelper(int indexOne, int indexTwo){
        for(int i = indexTwo; i >= indexOne; i--){
            for (int j = i+1; j <= indexTwo+1; j++) {
                if(blocks[j-1].getIsEmpty()==false&&blocks[j].getIsEmpty()==true){
                    blocks[j] = blocks[j-1]; //moves the block to the right if the block
                    blocks[j-1] = new Block();  //next to is empty    
                }
                else if(blocks[j-1].getIsEmpty()==false&&blocks[j].getIsEmpty()==false
                        && blocks[j].getValue() == blocks[j-1].getValue()
                        && blocks[j-1].isCombinedBlock()==false
                        && blocks[j].isCombinedBlock()==false){ //if statement checks if the block
                    //in question and the block to the right of it are both the same value and have
                    // not previosuly combined given that they are both not empty
                    blocks[j] = new Block((blocks[j-1].getValue()+blocks[j].getValue()),true);
                    blocks[j-1] = new Block();
                    addToScore(blocks[j].getValue());
                }
            }
        }
    }

    /**
     *This method shifts the blocks to the left when left is pushed
     */
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
            //these if else statements check for the possibility of shifting to the left due to
            // the presence of an empty block or if two adjacent blocks are of the same value
        }
        if(possible){
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

    /**
     *This method shifts the blocks left by specified indices.
     *
     *@param indexOne the smaller of the two indices
     *@param indexTwo the larger of the two indices
     */
    public void shiftLeftHelper(int indexOne, int indexTwo){
        for(int i = indexOne; i <= indexTwo; i++){
            for(int j = i-1; j >= indexOne-1; j--) {
                if(blocks[j+1].getIsEmpty()==false&&blocks[j].getIsEmpty()==true){
                    blocks[j] = blocks[j+1]; //checks for empty blocks to the left
                    blocks[j+1] = new Block();      
                }
                else if(blocks[j+1].getIsEmpty()==false&&blocks[j].getIsEmpty()==false
                        && blocks[j].getValue() == blocks[j+1].getValue()
                        && blocks[j+1].isCombinedBlock()==false
                        && blocks[j].isCombinedBlock()==false){
                    //checks for identical block numbers in blocks adjacent to each other
                    //given that they are not empty
                    blocks[j] = new Block((blocks[j+1].getValue()+blocks[j].getValue()),true);
                    blocks[j+1] = new Block();
                    addToScore(blocks[j].getValue());
                }
            }
        }
    }

    /**
     *This method shifts the blocks up when up is pushed
     */
    public void shiftToUp(){
        direction.setText("Up");
        boolean possible = false;
        for(int i = 0; i<16; i++){
            if(blocks[i].getIsEmpty()){
                possible = true; //checks for empty blocks
            }
            else if (i > 4 && blocks[i-4].getValue()==blocks[i].getValue()){possible = true;}
        } //checks for identical block values adjacent to each other
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

    /**
     *This method shifts the blocks up by specified indices.
     *
     *@param indexOne the smaller of the two indices
     *@param indexTwo the larger of the two indices
     */
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
                    //checks for identical block numbers in blocks adjacent to each other
                    //given that they are not empty
                    blocks[j] = new Block((blocks[j+4].getValue()+blocks[j].getValue()),true);
                    blocks[j+4] = new Block();
                    addToScore(blocks[j].getValue());
                }
            }
        }
    }

    /**
     *This method shifts the blocks down when down is pushed
     */
    public void shiftToDown(){
        direction.setText("Down");
        boolean possible = false;
        for(int i = 0; i<16; i++){
            if(blocks[i].getIsEmpty()){//checks for empty blocks
                possible = true;
            }
            else if (i < 12 && blocks[i+4].getValue()==blocks[i].getValue()){possible = true;}
        } //checks for identical block values adjacent to each other
        if(possible){
            shiftDownHelper(0,8);
            shiftDownHelper(1,9);
            shiftDownHelper(2,10);
            shiftDownHelper(3,11);
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

    /**
     *This method shifts the blocks down by specified indices.
     *
     *@param indexOne the smaller of the two indices
     *@param indexTwo the larger of the two indices
     */
    public void shiftDownHelper(int indexOne, int indexTwo){
        for(int i = indexTwo; i >= indexOne; i = i-4){
            for (int j = i+4; j <= indexTwo+4; j = j+4) {
                if(blocks[j-4].getIsEmpty()==false&&blocks[j].getIsEmpty()==true){
                    blocks[j] = blocks[j-4];
                    blocks[j-4] = new Block();         
                }//checks for empty blocks below it
                else if(blocks[j-4].getIsEmpty()==false&&blocks[j].getIsEmpty()==false
                        && blocks[j].getValue() == blocks[j-4].getValue()
                        && blocks[j-4].isCombinedBlock()==false
                        && blocks[j].isCombinedBlock()==false){
                    //checks for identical block numbers in blocks adjacent to each other
                    //given that they are not empty
                    blocks[j] = new Block((blocks[j].getValue()+blocks[j-4].getValue()),true);
                    blocks[j-4] = new Block();
                    addToScore(blocks[j].getValue());
                }
            }
        }
    }

    /**
     *This method adds to the Score by some integer.
     *
     *@param x the integer to add
     */
    public void addToScore(int x){
        score += x;
        label = new Label("Score\n" + score);
        hbox.getChildren().set(1, label);
    }

    /**
     *This method sets the blocks for the beginning of the game
     */
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

    /**
     *This method randomly places a two or four
     */
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

    /**
     *This method alerts the user of their loss
     */
    public void youLoseXD(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Lose!\nYour Score: "+score+
                             "\nPress New Game for a new Game, Exit the game to go back to menu.");
        alert.getDialogPane().setPrefSize(400,200);
        alert.showAndWait();
    }

    /**
     *This method checks the possibility of the game.
     *
     *@return the boolean of it the game is possible
     */
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

    /**
     *This method checks the right of the block of the given index.
     *
     *@return the boolean of the check
     */
    public boolean checkRight(int i){
        return blocks[i].getValue()==blocks[i+1].getValue();
    }

    /**
     *This method checks the left of the block of the given index.
     *
     *@return the boolean of the check
     */
    public boolean checkLeft(int i){
        return blocks[i].getValue()==blocks[i-1].getValue();
    }

    /**
     *This method checks the up of the block of the given index.
     *
     *@return the boolean of the check
     */
    public boolean checkUp(int i){
        return blocks[i].getValue()==blocks[i-4].getValue();
    }

    /**
     *This method checks the down of the block of the given index.
     *
     *@return the boolean of the check
     */
    public boolean checkDown(int i){
        return blocks[i].getValue()==blocks[i+4].getValue();
    }

    /**
     *This method alerts the user of their win
     */
    public void youWin(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Win!\nYour Score: "+score+
                             "\nPress New Game for a new Game, Exit the game to go back to menu.");
        alert.getDialogPane().setPrefSize(400,200);
        alert.showAndWait();
    }
    /**
     *This method checks the game for a 2048, so the user can win
     *
     *@return the boolean of it they won
     */
    public boolean checkWin(){
        for(Block b : blocks){
            if(b.getValue()==2048){
                return true;
            }
        }
        return false;
    }

    /**
     *This method sets up a new game when the button is pushed.
     *
     *@param e the Action Event to help the method call.
     */
    public void newGame(ActionEvent e){
        score = 0;
        label = new Label("Score\n" + score);
        hbox.getChildren().set(1, label);
        tilePane.getChildren().clear();
        testAddBlocks();
        vbox.getChildren().set(2, tilePane);
    }
}//TwentyFortyEight
