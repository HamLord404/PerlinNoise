package sample;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private Tile[][] grid = new Tile[1000][1000];
    private static double riverThreshold = 0.4;


    public void addTile(int x, int y, double height, Pane root){
        grid[x][y] = new Tile(root,x,y,height);
    }


    public void generateRivers(){
        ArrayList<Point2D> highEnough = new ArrayList<Point2D>();

        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 1000; j++){

                if(grid[i][j].getHeight() > riverThreshold) {
                    Point2D p = new Point2D(i, j);
                    highEnough.add(p);
                }

            }
        }

        Random r = new Random();
        int gen = r.nextInt(highEnough.size());
        riverFlow((int)highEnough.get(gen).getX(),(int)highEnough.get(gen).getY());




    }

    private void riverFlow(int x, int y){
        if(grid[x][y].isWater()){
            return;
        }

        if(x == 0 || x > 998 || y == 0 || y > 998){
            return;
        }

        double d0 = -grid[x][y].getHeight() + grid[x][y-1].getHeight();
        double d1 = -grid[x][y].getHeight() + grid[x][y+1].getHeight();
        double d2 = -grid[x][y].getHeight() + grid[x-1][y].getHeight();
        double d3 = -grid[x][y].getHeight() + grid[x+1][y].getHeight();



        //noise
        Random r = new Random();
        double noise = r.nextDouble()/12;
        d0 += -noise;
        noise = r.nextDouble()/12;
        d1 += -noise;
        noise = r.nextDouble()/12;
        d2 += -noise;
        noise = r.nextDouble()/12;
        d3 += -noise;




        double[] diffArray = {d0,d1,d2,d3};




        int minIndex = 0;
        for (int i = 0; i < diffArray.length; i++) {

            if(diffArray[i] < diffArray[minIndex]){
                minIndex = i;
            }
        }


        grid[x][y].setWater(true);
        grid[x][y].getRect().setFill(Color.LIGHTBLUE);


        if(minIndex == 0){
            riverFlow(x, y-1);
        }
        if(minIndex == 1){
            riverFlow(x, y+1);
        }
        if(minIndex == 2){
            riverFlow(x-1, y);
        }
        if(minIndex == 3){
            riverFlow(x+1, y);
        }


    }




    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }
}
