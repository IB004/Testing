package org.s367118.whale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinatesTest {
    @Test
    void constructorTest() {
        Coordinates coordinates = new Coordinates(5, -100, 8);

        Assertions.assertEquals(5, coordinates.getX());
        Assertions.assertEquals(-100, coordinates.getY());
        Assertions.assertEquals(8, coordinates.getZ());
    }

    @Test
    void toStringTest() {
        Coordinates coordinates = new Coordinates(1, 2, -3);

        Assertions.assertEquals("(  1,   2,  -3)", coordinates.toString());
    }

    @Test
    void equalsTest() {
        Coordinates a = new Coordinates(1, 0, 3);
        Coordinates b = new Coordinates(5 - 4, 1 * 0, 2 + 1);
        Coordinates c = new Coordinates(-1, 0, -3);

        Assertions.assertEquals(a, b);
        Assertions.assertNotEquals(a, c);
    }

    @Test
    void cloneTest() {
        Coordinates a = new Coordinates(1, 2, 3);
        Coordinates b = a.clone();
        Assertions.assertEquals(a.getX(), b.getX());

        b.setX(10);
        Assertions.assertNotEquals(a.getX(), b.getX());
    }
}
