package cs1302.arcade;

import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.concurrent.ThreadLocalRandom;

public class Block extends VBox{
    public boolean isCombined;
    public int valueInside;
    VBox block;
    ImageView iv;
    public final static int[] randomNum = {2, 4};
    public final static int[] playableNumbers = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192};
    Image[] i;

    public Block(){
        int random = ThreadLocalRandom.current().nextInt(0, 2);
        valueInside = randomNum[random];
        iv = new ImageView(i[random]);
        block = new VBox();
        block.getChildren().add(iv);
        isCombined = false;
    }
    
    public Block(int value, boolean combined){
        valueInside = value;
        int index = 0;
        for(int i = 0; i < playableNumbers.length;i++){
            if(playableNumbers[i]==valueInside){
                index = i;
            }
        }
        iv = new ImageView(i[index]);
        block = new VBox();
        block.getChildren().add(iv);
        isCombined = combined;
    }

    
    
}
