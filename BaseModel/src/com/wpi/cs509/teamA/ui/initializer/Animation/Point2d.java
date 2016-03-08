package com.wpi.cs509.teamA.ui.initializer.Animation;

/**
 * Created by cuixi on 12/12/2015.
 */
public class Point2d {
    private double x = 0;
    private double y = 0;

    public Point2d (double px, double py) {
        x = px;
        y = py;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
