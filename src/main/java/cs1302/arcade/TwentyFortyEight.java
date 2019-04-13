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
        gameName.setFont(Font.font("clear sans", 20));
        gameName.setFill(Color.BLACK);
        




    }//start






}//TwentyFortyEight
