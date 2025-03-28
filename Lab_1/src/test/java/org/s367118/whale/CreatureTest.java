package org.s367118.whale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    @Test
    void nullConstructorParamsTest() {
        Creature a = new Creature(null, null);
        Assertions.assertEquals("", a.getName());
        Assertions.assertEquals("", a.getThought());
    }

    @Test
    void thinkTest() {
        Creature a = new Creature("Name", "Thought");
        Assertions.assertEquals("(  0,   0,   0) Name                     : Thought", a.think());
    }

    @Test
    void equalsLooksOnlyAtNameTest() {
        Creature a = new Creature("A", null);
        Creature b = new Creature("A", "Am I different?..");
        Assertions.assertEquals(a, b);
    }

    @Test
    void cloneTest() {
        Creature a = new Creature("A", "aaa");

        Creature b = a.clone();
        Assertions.assertEquals(a.getThought(), b.getThought());

        b.setThought("Different thought");
        Assertions.assertNotEquals(a.getThought(), b.getThought());
    }
}