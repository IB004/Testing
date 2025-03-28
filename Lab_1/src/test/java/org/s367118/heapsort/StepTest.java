package org.s367118.heapsort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StepTest {
    @Test
    public void toStringEmptyHistoryNullMessageTest(){
        Step<Integer> step = new Step<>(null, null);

        String toString = step.toString();

        Assertions.assertEquals("null", toString);
    }

    @Test
    public void toStringEmptyHistoryTest(){
        Step<Integer> step = new Step<>(new Integer[] {}, "history message");

        String toString = step.toString();

        Assertions.assertEquals("[] : history message", toString);
    }

    @Test
    public void toStringTest(){
        Step<Integer> step = new Step<>(new Integer[] {1, -2, 3}, "history message");

        String toString = step.toString();

        Assertions.assertEquals("[1, -2, 3] : history message", toString);
    }


}
