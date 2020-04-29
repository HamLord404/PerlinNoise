package sample;



public class PerlinNoiseGenerator {


    Vector[][] gradVectors;



    public PerlinNoiseGenerator(int sizeX, int sizeY){
        gradVectors = new Vector[sizeX][sizeY];
        for(int i =0; i < sizeX; i++){
            for(int j =0; j < sizeY; j++){
                gradVectors[i][j] = new Vector();
            }
        }
    }

    public double lerp(double num0, double num1, double weight){
        return (1.0 - weight) * num0 + weight * num1;
    }

    public double dotProduct(int ix, int iy, double x, double y){


        double distX = x - (double)ix;
        double distY = y - (double)iy;

        return (distX * gradVectors[ix][iy].getxComp() + distY * gradVectors[ix][iy].getyComp());
    }


    public double perlinGen(double x, double y){

        int x0 = (int)x;
        int y0 = (int)y;

        int x1 = (int)x + 1;
        int y1 = (int)y + 1;

        double wx = x - (double)x0;
        double wy = y - (double)y0;

        double dotProd0 = dotProduct(x0,y0,x,y);
        double dotProd1 = dotProduct(x1,y0,x,y);

        double thing0 = lerp(dotProd0,dotProd1,wx);


        dotProd0 = dotProduct(x0,y1,x,y);
        dotProd1 = dotProduct(x1,y1,x,y);
        double thing1 = lerp(dotProd0,dotProd1,wx);


        return lerp(thing0,thing1,wy);
    }
}
