package org.s367118.whale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Engine {
    public void generateWorld(List<Creature> world, Set<Creature> shielded, Creature spaceShip) {
        for(Creature creature : world){
            String shipName = creature.equals(spaceShip) ? creature.getName() : null;
            if (shielded == null || !(shielded.contains(creature)))
                generateCreatureFields(creature);
            if (shipName != null)
                creature.setName(shipName);
        }
    }

    private void generateCreatureFields(Creature creature){
        if (Math.random() < 0.99)
            creature.copyCoordinates(generateCoordinates());
        if (Math.random() < 0.25)
            creature.setName(generateName());
        if (Math.random() < 0.45)
            creature.setThought(generateThought());
    }

    private Coordinates generateCoordinates(){
        final int min = -10-1;
        final int max = 10+1;
        Random random = new Random();
        int x = random.nextInt(max - min) + min;
        int y = random.nextInt(max - min) + min;
        int z = random.nextInt(max - min) + min;
        return new Coordinates(x, y, z);
    }

    private String generateName(){
        final String[] adjectives = {"Sad", "Purple", "Ceramic", "Enthusiastic", "Vogonic"};
        final String[] names = {"whale", "petunia", "cup", "black hole", "Borealis"};
        return getRandomElement(adjectives).concat(" ").concat(getRandomElement(names));
    }

    private String generateThought(){
        final String[] names = {"Who am I?", "I want more tea!", "Oh gosh, not again...", "How it's beautiful here!"};
        return getRandomElement(names);
    }

    private <T> T getRandomElement(T[] array){
        Random random = new Random();
        int randIndex = random.nextInt(array.length);
        return array[randIndex];
    }
}
