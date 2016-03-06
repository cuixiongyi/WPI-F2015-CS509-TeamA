package com.wpi.cs509.teamA.util;

/**
 * This class handles the scale of the map
 * 
 */
public class LinearTransform {
    private double x = 0;
    private double y = 0;
    private double scale = 1.0;

    public LinearTransform() {

    }


    public static Coordinate backTransferCoorImpl(Coordinate origin, LinearTransform plt) {
        Coordinate result = new Coordinate();
        result.setX((int)Math.round((origin.getX() - plt.getX()) / plt.getScale()));
        result.setY((int)Math.round((origin.getY() - plt.getY()) / plt.getScale()));
        return result;
    }

    public static Coordinate transferCoorImpl(Coordinate origin, LinearTransform plt) {
        Coordinate result = new Coordinate();
        result.setX((int)Math.round(origin.getX() * plt.getScale() + plt.getX()));
        result.setY((int)Math.round(origin.getY() * plt.getScale() + plt.getY()));
        return result;
    }


    public Coordinate backTransferCoor(Coordinate origin) {
        return backTransferCoorImpl(origin, this);
    }

    public Coordinate transferCoor(Coordinate origin) {
        return transferCoorImpl(origin, this);
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

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
