package org.example.organization;

import com.google.gson.annotations.Expose;

/**
 * Класс координат
 */
public class Coordinates {
    @Expose(serialize = true)
    private float x;
    @Expose(serialize = true)
    private long y; //Максимальное значение поля: 274

    public Coordinates(float x, long y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
