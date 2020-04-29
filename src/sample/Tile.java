package sample;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile {
    private double height;
    private Rectangle rect;

    public Tile(Pane root, int x, int y, double height){
        this.height = height;
        rect = new Rectangle(2,2);
        Color colour = Color.RED;

        double normalisedHeight = (height +1)/2;

        colour = colour.interpolate(Color.GREEN,normalisedHeight);

        rect.setFill(colour);

        rect.setTranslateX(x * 2);
        rect.setTranslateY(y * 2);

        root.getChildren().add(rect);

    }

}
