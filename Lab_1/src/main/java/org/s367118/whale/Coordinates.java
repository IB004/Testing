package org.s367118.whale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinates implements Cloneable{
    public Coordinates(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private int x;
    private int y;
    private int z;

    @Override
    public String toString() {
        return String.format("(%3d, %3d, %3d)", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y && z == that.z;
    }

    @Override
    public Coordinates clone() {
        try {
            return (Coordinates) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

}
