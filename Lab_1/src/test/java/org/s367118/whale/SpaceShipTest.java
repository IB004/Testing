package org.s367118.whale;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.*;

class SpaceShipTest {

    private final SpaceShip spaceShip = new SpaceShip("TEST", "");

    private final Creature a = new Creature("A", "aaa");
    private final Creature b = new Creature("B", "bbb");
    private final Creature c = new Creature("C", "ccc");

    private final List<Creature> world = new ArrayList<>(3);

    @BeforeEach
    public void initWorld(){
        world.add(spaceShip);
        world.add(a);
        world.add(b);
        world.add(c);
    }

    @Test
    public void membersTest(){
        Assertions.assertFalse(spaceShip.isMember(a));
        Assertions.assertFalse(spaceShip.isMember(b));
        Assertions.assertFalse(spaceShip.isMember(c));

        spaceShip.addMember(a);
        Assertions.assertTrue(spaceShip.isMember(a));
        Assertions.assertFalse(spaceShip.isMember(b));
        Assertions.assertFalse(spaceShip.isMember(c));

        spaceShip.addMember(c);
        Assertions.assertTrue(spaceShip.isMember(a));
        Assertions.assertFalse(spaceShip.isMember(b));
        Assertions.assertTrue(spaceShip.isMember(c));

        spaceShip.removeMember(c);
        Assertions.assertTrue(spaceShip.isMember(a));
        Assertions.assertFalse(spaceShip.isMember(b));
        Assertions.assertFalse(spaceShip.isMember(c));

        spaceShip.removeMember(c);
        Assertions.assertTrue(spaceShip.isMember(a));
        Assertions.assertFalse(spaceShip.isMember(b));
        Assertions.assertFalse(spaceShip.isMember(c));
    }

    @Test
    public void membersChangePositionWithShipTest() {
        spaceShip.addMember(a);
        spaceShip.addMember(b);
        spaceShip.addMember(c);

        spaceShip.copyCoordinates(new Coordinates(1, 2, 3));
        Assertions.assertEquals(spaceShip.getCoordinates(), a.getCoordinates());
        Assertions.assertEquals(spaceShip.getCoordinates(), b.getCoordinates());
        Assertions.assertEquals(spaceShip.getCoordinates(), c.getCoordinates());
    }

    @Test
    public void removedMembersDoesNotChangePositionWithShipTest() {
        spaceShip.addMember(a);
        spaceShip.addMember(b);
        spaceShip.addMember(c);
        spaceShip.removeMember(c);
        spaceShip.copyCoordinates(new Coordinates(1, 2, 3));
        Assertions.assertEquals(spaceShip.getCoordinates(), a.getCoordinates());
        Assertions.assertEquals(spaceShip.getCoordinates(), b.getCoordinates());
        Assertions.assertNotEquals(spaceShip.getCoordinates(), c.getCoordinates());
    }

    @Timeout(1)
    @RepeatedTest(10)
    public void jumpTest(){
        Coordinates target = new Coordinates(1, 2, 3);
        Assertions.assertNotEquals(target, spaceShip.getCoordinates());

        writeToStdin("1 2 3");
        spaceShip.doJump(world);
        for (Creature creature : world){
            if (creature.equals(spaceShip)){
                Assertions.assertEquals(target, creature.getCoordinates());
            }
        }
    }

    @Test
    public void zeroInputTest(){
        writeToStdin("");
        Assertions.assertThrows(NoSuchElementException.class,
                () -> spaceShip.doJump(world));
    }

    private void writeToStdin(String data){
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

}