package cs1302.arcade;

import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.concurrent.ThreadLocalRandom;

/**
 *This class holds the information for a Block in 2048. It extends a VBox.
 */
public class Block extends VBox{
    public boolean isEmpty;
    public boolean isCombined;
    public int valueInside;
    ImageView iv;
    public final static int[] randomNum = {2, 4};
    public final static int[] playableNumbers = {2,4,8,16,32,64,128,256,512,1024,2048};
    Image[] i;
    
    /**
     *This constructor make an empty block
     */
    public Block(){
        super();
        setImageArray();
        iv = new ImageView();
        isCombined = false;
        isEmpty = true;
    }
    
    /**
     *This constructor is used for creating a random Block of 2 or 4.
     *
     *@param isEmpty if the block is empty
     */
    public Block(boolean isEmpty){
        super();
        setImageArray();
        int random = ThreadLocalRandom.current().nextInt(0, 2);
        valueInside = randomNum[random];
        iv = new ImageView(i[random]);
        isCombined = false;
        this.isEmpty = isEmpty;
        this.getChildren().add(iv);
    }
    
    /**
     *This constructor is used for creating a Block of a given value.
     *
     *@param value the value of the block
     *@param combined if the block was combined
     */
    public Block(int value, boolean combined){
        super();
        setImageArray();
        valueInside = value;
        int index = 0;
        for(int i = 0; i < playableNumbers.length;i++){
            if(playableNumbers[i]==valueInside){
                index = i;
            }
        }
        iv = new ImageView(i[index]);
        isCombined = combined;
        isEmpty = false;
        this.getChildren().add(iv);
    }
    
    /**
     *This method sets the block as empty or not.
     *
     *@param isEmpty the to be set boolean
     */
    public void setIsEmpty(boolean isEmpty){
        this.isEmpty = isEmpty;
    }
    
    /**
     *This method gets the value of the emptiness of the block
     *
     *@return if the block is empty
     */
    public boolean getIsEmpty(){
        return isEmpty;
    }

    /**
     *This method sets the value of the block
     *
     *@param a the int to set
     */
    public void setValue(int a){
        valueInside = a;
    }
    
    /**
     *This method gets the value of the block
     *
     *@return the value
     */
    public int getValue(){
        return valueInside;
    }
    
    /**
     *This method gets if the block is combined
     *
     *@return if the block is combined
     */
    public boolean isCombinedBlock(){
        return isCombined;
    }
    
    /**
     *This method sets if the block is combined
     *
     *@param combined the boolean to set
     */
    public void setIsCombined(boolean combined){
        isCombined = combined;
    }
    
    /**
     *This method sets the images of the block.
     */
    public void setImageArray(){
        i = new Image[11];
        i[0] = new Image("file:src/main/resources/2.png");
        i[1] = new Image("file:src/main/resources/4.png");
        i[2] = new Image("file:src/main/resources/8.png");
        i[3] = new Image("file:src/main/resources/16.png");
        i[4] = new Image("file:src/main/resources/32.png");
        i[5] = new Image("file:src/main/resources/64.png");
        i[6] = new Image("file:src/main/resources/128.png");
        i[7] = new Image("file:src/main/resources/256.png");
        i[8] = new Image("file:src/main/resources/512.png");
        i[9] = new Image("file:src/main/resources/1024.png");
        i[10] = new Image("file:src/main/resources/2048.png");
    }
    
    
}
