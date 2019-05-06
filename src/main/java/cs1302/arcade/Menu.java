package cs1302.arcade;

import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;

public class Menu extends Application{
    VBox menu;
    Button twentyFortyEight;
    Button frogger;
    Button exit;
    Text text;
    TwentyFortyEight one;
    Frogger two;
    @Override
    public void start(Stage stage){
        menu = new VBox();
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
        text = new Text("MobaXterm Penguins\nArcade Games!");
        exit = new Button("Exit");
        exit.setOnAction((ActionEvent a)->{System.exit(0);});
        menu.getChildren().addAll(text,frogger,twentyFortyEight,exit);
        Scene scene = new Scene(menu, 400, 400);
        stage.setTitle("Arcade!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
    public void showDirections(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Frogger:\nWelcome to Frogger!\nTo Play, use the arrow keys."
                             +"\nYou start with 150 points. Every level gives you 100 points."
                             + "Get as close to 450 as you can! Don't get hit by cars, you'll lose"
                             + "50 points!");
        alert.getDialogPane().setPrefSize(400,400);
        alert.showAndWait();
    }
}
