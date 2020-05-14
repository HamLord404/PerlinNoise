package sample;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Map {

    private static double riverThreshold = 0.52;
    private int octaves;
    double persistance;
    double lacunarity;
    int sizeX;
    int sizeY;
    private Tile[][] grid;

    public Map(Pane root, int sizeX, int sizeY, int octaves, double persistance, double lacunarity, int scale){
        grid = new Tile[sizeX][sizeY];
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        PerlinNoiseGenerator p = new PerlinNoiseGenerator(1001,1001);



        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){

                double amplitude = 1;
                double frequency = 1;
                double newTileheight = 0;


                for(int k = 0; k < octaves; k++){
                    double xVal = ((double)i)/scale * frequency;
                    double yVal = ((double)j)/scale * frequency;

                    newTileheight += p.perlinGen(xVal,yVal);

                    amplitude = amplitude * persistance;
                    frequency = frequency * lacunarity;
                }

                addTile(i,j,newTileheight,root);



            }
        }


    }

    private void generateLandmass(){

    }

    public void addTile(int x, int y, double height, Pane root){
        grid[x][y] = new Tile(root,x,y,height);
    }


    public void generateRivers(){
        ArrayList<Point2D> highEnough = new ArrayList<Point2D>();

        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){

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

    public void landmassalise(){
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                int distX = Math.abs(grid[i][j].getX() - 364);
                int distY = Math.abs(grid[i][j].getY() - 179);
                grid[i][j].setHeight(grid[i][j].getHeight());
                grid[i][j].reevaluateColor();
            }
        }

    }

    private void riverFlow(int x, int y){
        if(grid[x][y].isWater()){
            return;
        }

        if(x == 0 || x > sizeX-2 || y == 0 || y > sizeY-2){
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

    public Ship generateShips(){
        ArrayList<Point2D> waterEnough = new ArrayList<Point2D>();

        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 1000; j++){

                if(grid[i][j].isWater()) {
                    Point2D p = new Point2D(i, j);
                    waterEnough.add(p);
                }

            }
        }

        Random r = new Random();
        int gen = r.nextInt(waterEnough.size());

        grid[(int)waterEnough.get(gen).getX()][(int)waterEnough.get(gen).getY()].getRect().setFill(Color.CORAL);

        return new Ship((int)waterEnough.get(gen).getX(),(int)waterEnough.get(gen).getY());

    }

    public Settlement generateSettlement(){
        ArrayList<Point2D> landEnough = new ArrayList<Point2D>();

        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 1000; j++){

                if(!grid[i][j].isWater()) {
                    Point2D p = new Point2D(i, j);
                    landEnough.add(p);
                }

            }
        }

        Random r = new Random();
        int gen = r.nextInt(landEnough.size());

        grid[(int)landEnough.get(gen).getX()][(int)landEnough.get(gen).getY()].getRect().setFill(Color.DARKGRAY);

        return new Settlement((int)landEnough.get(gen).getX(),(int)landEnough.get(gen).getY());
    }






    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }
}
