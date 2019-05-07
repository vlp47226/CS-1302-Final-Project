package cs1302.arcade;

import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 *This class is the menu for the arcade.
 */
public class Menu extends Application{
    //The instance variables
    VBox menu;
    Button twentyFortyEight;
    Button frogger;
    Button exit;
    Text text;
    TwentyFortyEight one;
    Frogger two;
    HBox twentyLine;
    HBox froggerLine;
    ImageView frog;
    ImageView twenty;

    /**
     *{@inheritDoc}
     */
    @Override
    public void start(Stage stage){
        menu = new VBox();
        twentyLine = new HBox();
        froggerLine = new HBox();
        twentyFortyEight = new Button("2048");
        twentyFortyEight.setOnAction((ActionEvent e)->{
                one = new TwentyFortyEight();
                one.start();
                one.show();
            });
        frogger = new Button("Frogger");
        frogger.setOnAction((ActionEvent e)->{
                showDirections();
                two = new Frogger();
                two.start();
                two.show();
            });
        Image twentyFour = new Image("file:src/main/resources/2048.png",25.0,25.0,true,true);
        twenty = new ImageView(twentyFour);
        twentyLine.getChildren().addAll(twentyFortyEight, twenty);
        twentyLine.setAlignment(Pos.CENTER);
        Image frogg = new Image("file:src/main/resources/frog.png",25.0,25.0,true,true);
        frog = new ImageView(frogg);
        froggerLine.getChildren().addAll(frogger, frog);
        froggerLine.setAlignment(Pos.CENTER);
        text = new Text("MobaXterm Penguins\nArcade Games!");
        exit = new Button("Exit");
        exit.setOnAction((ActionEvent a)->{System.exit(0);});
        menu.getChildren().addAll(text,froggerLine,twentyLine,exit);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(50.0);
        Scene scene = new Scene(menu, 400, 400);
        stage.setTitle("Arcade!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
    
    /**
     *This method shows the directions for Frogger.
     */
    public void showDirections(){
        //Make the alert, set the text, size, then show it
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Frogger:\nWelcome to Frogger!\nTo Play, use the arrow keys."
                             +"\nYou start with 150 points. Every level gives you 100 points."
                             + "Get as close to 450 as you can! Don't get hit by cars, you'll lose"
                             + "50 points!");
        alert.getDialogPane().setPrefSize(400,400);
        alert.showAndWait();
    }
}
