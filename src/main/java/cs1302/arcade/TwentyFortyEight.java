package cs1302.arcade;

//imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


public class TwentyFortyEight extends Application {


    Text gameName;

    

    @Override
    public void start(Stage stage) {
        gameName = new Text("2048");
        gameName.setfont(Font.font("clear sans", 20));
        gameName.setFill(Color.Black);
        




    }//start






















}//TwentyFortyEight
