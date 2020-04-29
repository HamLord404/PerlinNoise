package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {


    Pane root = new Pane();
    Map map = new Map();
    @Override
    public void start(Stage primaryStage) throws Exception{

        PerlinNoiseGenerator p = new PerlinNoiseGenerator(1001,1001);

        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 1000; j++){
                double xVal = ((double)i)/20;
                double yVal = ((double)j)/20;
                map.addTile(i,j,p.perlinGen(xVal,yVal),root);
            }
        }


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
