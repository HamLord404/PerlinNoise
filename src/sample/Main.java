package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;

public class Main extends Application {


    Pane root = new Pane();


    int mapSizeX = 300;
    int mapSizeY = 250;

    Map map = new Map(root,mapSizeX,mapSizeY,4,0.5,4.75,1000);

    ArrayList<Ship> ships = new ArrayList<>();
    ArrayList<Settlement> settlements = new ArrayList<>();
    Group mapGroup = new Group();

    @Override
    public void start(Stage primaryStage) throws Exception{

        PerlinNoiseGenerator p = new PerlinNoiseGenerator(mapSizeX +1 ,mapSizeY + 1);

        for(int i = 0; i < mapSizeX; i++){
            for(int j = 0; j < mapSizeY; j++){
                map.getGrid()[i][j].getRect().setOnMouseClicked(this::click);
                mapGroup.getChildren().add(map.getGrid()[i][j].getRect());
            }
        }

        root.getChildren().add(mapGroup);




        for(int i = 0; i < 100; i++){
            //map.generateRivers();
        }

        for(int i = 0; i < 150; i++){
            //ships.add(map.generateShips());
        }



        for(int i = 0; i < 200; i++){
            //settlements.add(map.generateSettlement());
        }





        tick();
        Scene scene = new Scene(root, 300, 275);
        scene.setOnKeyPressed(this::keyPress);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void click(MouseEvent event){
        Tile t = null;
        for(int i = 0; i < mapSizeX; i++){
            for(int j = 0; j < mapSizeY; j++){
                if(map.getGrid()[i][j].getRect() == event.getSource()){
                    t = map.getGrid()[i][j];
                }
            }
        }

        System.out.println("x: " + t.getX() + " y: " + t.getY());

    }

    public void keyPress(KeyEvent event){
        if(event.getCode().equals(KeyCode.Z)){
            mapGroup.setScaleX(mapGroup.getScaleX() + 0.1);
            mapGroup.setScaleY(mapGroup.getScaleY() + 0.1);
        }
        if(event.getCode().equals(KeyCode.X)){
            mapGroup.setScaleX(mapGroup.getScaleX() - 0.1);
            mapGroup.setScaleY(mapGroup.getScaleY() - 0.1);
        }

        if(event.getCode().equals(KeyCode.RIGHT)){
            mapGroup.setTranslateX(mapGroup.getTranslateX() - 30);

        }
        if(event.getCode().equals(KeyCode.LEFT)){
            mapGroup.setTranslateX(mapGroup.getTranslateX() + 30);

        }

        if(event.getCode().equals(KeyCode.UP)){
            mapGroup.setTranslateY(mapGroup.getTranslateY() + 30);

        }
        if(event.getCode().equals(KeyCode.DOWN)){
            mapGroup.setTranslateY(mapGroup.getTranslateY() - 30);

        }
    }


    public void tick(){
        //this is complicated, i don't fully understand it, but this code runs every .01 seconds
        Timeline tick = new Timeline(new KeyFrame(Duration.seconds(.1), new EventHandler<ActionEvent>() {

            @Override
            //this happens every tick
            public void handle(ActionEvent event) {

                for (Ship s:ships) {
                    s.makeAction(map.getGrid());
                }



            }
        }));

        tick.setCycleCount(Timeline.INDEFINITE);
        tick.play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
