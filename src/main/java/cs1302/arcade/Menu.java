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

public class Menu extends Application{
    VBox menu;
    Button twentyFortyEight;
    Button frogger;
    Button exit;
    Text text;
    @Override
    public void start(Stage stage){
        menu = new VBox();
        twentyFortyEight = new Button("2048");
        frogger = new Button("Frogger");
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
}
