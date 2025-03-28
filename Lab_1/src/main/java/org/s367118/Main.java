package org.s367118;

import org.s367118.whale.Creature;
import org.s367118.whale.SpaceShip;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        SpaceShip spaceShip = new SpaceShip("Heart of Gold", "I need more tea.");
        Creature aurthurDent = new Creature("Aurthur Dent", "What's happening?");
        Creature fordPrefect = new Creature("Ford Prefect", "Great adventure!");

        Creature firstRocket = new Creature("Rocket 1", "I'll kill you!");
        Creature secondRocket = new Creature("Rocket 2", "I'll find you!");

        List<Creature> world = new ArrayList<>();
        world.add(spaceShip);
        world.add(aurthurDent);
        world.add(fordPrefect);
        spaceShip.addMember(aurthurDent);
        spaceShip.addMember(fordPrefect);

        world.add(firstRocket);
        world.add(secondRocket);

        for (Creature creature : world){
            System.out.println(creature.think());
        }

        System.out.println();

        spaceShip.doJump(world);
        for (Creature creature : world){
            System.out.println(creature.think());
        }
    }
}