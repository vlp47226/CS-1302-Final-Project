package cs1302.arcade;

import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.concurrent.ThreadLocalRandom;

public class Block extends VBox{
    public boolean isCombined;
    public int valueInside;
    ImageView iv;
    public final static int[] randomNum = {2, 4};
    public final static int[] playableNumbers = {2,4,8,16,32,64,128,256,512,1024,2048};
    Image[] i;

    public Block(){
        super();
        setImageArray();
        int random = ThreadLocalRandom.current().nextInt(0, 2);
        valueInside = randomNum[random];
        iv = new ImageView(i[random]);
        isCombined = false;
        this.getChildren().add(iv);
    }
    
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
        this.getChildren().add(iv);
    }

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
