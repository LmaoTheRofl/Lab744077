package org.example.organization;

import com.google.gson.annotations.Expose;

public class Location {
    @Expose(serialize = true)
    private double x;
    @Expose(serialize = true)
    private double y;
    @Expose(serialize = true)
    private int z;

    public Location(double x, double y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
