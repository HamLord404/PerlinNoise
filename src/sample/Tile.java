package sample;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Tile {
    private static double seaLevel = 0.50;
    private static double mountianLevel = 0.8;
    private static double snowLevel = 0.9;
    private double height;
    private Rectangle rect;
    private boolean water = false;
    private int x;
    private int y;
    private static int rectangleSize = 5;
    private static int riverChance = 97;

    public Tile(Pane root, int x, int y, double height){
        this.x = x;
        this.y = y;
        this.height = height;
        rect = new Rectangle(rectangleSize,rectangleSize);
        Color colour = Color.GREEN;

        double normalisedHeight = (height +1)/2;

        colour = colour.interpolate(Color.BROWN,normalisedHeight);

        colour = chooseTerrain(colour,normalisedHeight);

        rect.setFill(colour);

        rect.setTranslateX(x * rectangleSize);
        rect.setTranslateY(y * rectangleSize);

        root.getChildren().add(rect);

    }


    private Color chooseTerrain(Color color, double normHeight){
        if(normHeight < seaLevel){
            color = Color.LIGHTBLUE;
            water = true;
        }
        else if(normHeight > mountianLevel && normHeight < snowLevel){
            color = Color.GRAY;
            water = false;
        } else if(normHeight > snowLevel){
            color = Color.WHITE;
            water = false;
        }
        else {
            water = false;
        }

        return color;
    }

    public void reevaluateColor(){
        Color colour = Color.GREEN;



        colour = colour.interpolate(Color.BROWN,height);

        colour = chooseTerrain(colour,height);

        rect.setFill(colour);

    }

    public static double getSeaLevel() {
        return seaLevel;
    }

    public static void setSeaLevel(double seaLevel) {
        Tile.seaLevel = seaLevel;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static int getRectangleSize() {
        return rectangleSize;
    }

    public static void setRectangleSize(int rectangleSize) {
        Tile.rectangleSize = rectangleSize;
    }

    public static int getRiverChance() {
        return riverChance;
    }

    public static void setRiverChance(int riverChance) {
        Tile.riverChance = riverChance;
    }
}
