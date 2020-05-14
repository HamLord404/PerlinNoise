package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;


public class Settlement {
    private String name;
    private ImageView sprite = new ImageView("Settlement.png");
    private int x;
    private int y;

    private Color colour;

    public Settlement(int x, int y) {
        this.x = x;
        this.y = y;
        Random r = new Random();
        colour = Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255));
    }




    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
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
}
