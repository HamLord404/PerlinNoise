package sample;

import javafx.scene.paint.Color;

import java.util.Random;

public class Ship {
    private int x;
    private int y;


    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void makeAction(Tile[][] grid){
        if(x == 0 || x > 998 || y == 0 || y > 998){
            return;
        }

        Random r = new Random();
        int direction = r.nextInt(4);

        if(direction == 0 && grid[x][y-1].isWater()){
            moveTo(x, y-1,grid);
        }
        if(direction == 1 && grid[x][y+1].isWater()){
            moveTo(x, y+1,grid);
        }
        if(direction == 2 && grid[x-1][y].isWater()){
            moveTo(x-1, y,grid);
        }
        if(direction == 3 && grid[x+1][y].isWater()){
            moveTo(x+1, y,grid);
        }

    }

    public void moveTo(int x, int y,Tile[][] grid){

        grid[this.x][this.y].getRect().setFill(Color.LIGHTBLUE);


        this.x = x;
        this.y = y;

        grid[x][y].getRect().setFill(Color.CORAL);

    }

}
