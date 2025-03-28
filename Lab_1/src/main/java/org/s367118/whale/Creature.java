package org.s367118.whale;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Creature implements Cloneable{

    public Creature(String name, String thought){
        if (name != null)
            this.name = name;
        if (thought != null)
            this.thought = thought;
    }

    @Getter
    @Setter
    private String name = "";

    @Getter
    @Setter
    private String thought = "";

    @Getter
    @Setter
    private Coordinates coordinates = new Coordinates(0,0,0);

    public void copyCoordinates(Coordinates coordinates) {
        this.coordinates.setX(coordinates.getX());
        this.coordinates.setY(coordinates.getY());
        this.coordinates.setZ(coordinates.getZ());
    }

    public String think(){
        return String.format("%s %-25s: %s", coordinates.toString(), name, thought);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Creature creature = (Creature) o;
        return creature.name.equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public Creature clone() {
        try {
            return (Creature) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }
}
