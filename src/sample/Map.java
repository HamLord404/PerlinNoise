package sample;

import javafx.scene.layout.Pane;

public class Map {
    private Tile[][] grid = new Tile[1000][1000];


    public void addTile(int x, int y, double height, Pane root){
        grid[x][y] = new Tile(root,x,y,height);
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }
}
