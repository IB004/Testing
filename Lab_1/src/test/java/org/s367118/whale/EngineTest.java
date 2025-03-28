package org.s367118.whale;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EngineTest {

    private final Engine engine = new Engine();

    private final Creature a = new Creature("A", "aaa");
    private final Creature oldA = a.clone();
    private final Creature b = new Creature("B", "bbb");
    private final Creature oldB = b.clone();
    private final Creature c = new Creature("C", "ccc");
    private final Creature oldC = c.clone();

    private final List<Creature> world = new ArrayList<>(3);
    Set<Creature> shielded = new HashSet<>(3);

    @BeforeEach
    public void initWorld(){
        world.add(a);
        world.add(b);
        world.add(c);
    }

    @Test
    public void nullWorldTest(){
        Assertions.assertThrows(NullPointerException.class,
                () -> engine.generateWorld(null, new HashSet<>(), null));
    }

    @Test
    public void emptyWorldTest(){
        Assertions.assertDoesNotThrow(
                () -> engine.generateWorld(new ArrayList<>(), new HashSet<>(), null));
    }


    @Test
    public void nullShieldedTest(){
        Assertions.assertDoesNotThrow(() -> engine.generateWorld(new ArrayList<>(), null, null));
    }


    @RepeatedTest(10)
    public void shieldedDoNotChangeTest(){
        shielded.add(a);
        shielded.add(b);

        engine.generateWorld(world, shielded, null);

        Assertions.assertEquals(oldA.getName(), a.getName());
        Assertions.assertEquals(oldA.getThought(), a.getThought());
        Assertions.assertEquals(oldA.getCoordinates(), a.getCoordinates());

        Assertions.assertEquals(oldB.getName(), b.getName());
        Assertions.assertEquals(oldB.getThought(), b.getThought());
        Assertions.assertEquals(oldB.getCoordinates(), b.getCoordinates());
    }

    @Test
    public void elementsCountPersistTest(){
        int size = world.size();

        engine.generateWorld(world, shielded, null);
        Assertions.assertEquals(size, world.size());

        shielded.add(a);
        engine.generateWorld(world, shielded, null);
        Assertions.assertEquals(size, world.size());
    }

    @RepeatedTest(10)
    public void coordinatesChangeTest(){
        Coordinates oldACoordinates = oldA.getCoordinates().clone();
        Coordinates oldBCoordinates = oldB.getCoordinates().clone();
        Coordinates oldCCoordinates = oldC.getCoordinates().clone();

        engine.generateWorld(world, shielded, null);

        boolean aEquals = a.getCoordinates().equals(oldACoordinates);
        boolean bEquals = b.getCoordinates().equals(oldBCoordinates);
        boolean cEquals = c.getCoordinates().equals(oldCCoordinates);

        Assertions.assertFalse(aEquals && bEquals && cEquals);
    }

}
