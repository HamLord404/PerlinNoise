package sample;

import java.util.Random;

public class Vector {
    private double xComp;
    private double yComp;
    private int magnitude;
    private int angle;


    public Vector(){

        magnitude = 1;

        Random r = new Random();
        angle = r.nextInt(360);

        xComp = Math.cos(Math.toRadians(angle));
        yComp = Math.sin(Math.toRadians(angle));


    }

    public Vector(int x, int y){
        xComp = x;
        yComp = y;

    }

    public double getxComp() {
        return xComp;
    }

    public void setxComp(double xComp) {
        this.xComp = xComp;
    }

    public double getyComp() {
        return yComp;
    }

    public void setyComp(double yComp) {
        this.yComp = yComp;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
