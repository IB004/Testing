package org.s367118.whale;

import java.util.*;

public class SpaceShip extends Creature {
    private final Set<Creature> members = new HashSet<>();
    private final Engine engine = new Engine();

    public SpaceShip(String name, String thought) {
        super(name, thought);
    }

    public void addMember(Creature creature){
        members.add(creature);
        creature.setCoordinates(this.getCoordinates());
    }

    public void removeMember(Creature creature){
        members.remove(creature);
        creature.setCoordinates(new Coordinates(0, 0, 0));
        creature.copyCoordinates(this.getCoordinates());
    }

    public boolean isMember (Creature creature){
        return members.contains(creature);
    }

    public void doJump(List<Creature> world) {
        Coordinates target = askCoordinates();
        searchWorld(world, target);
    }

    private Coordinates askCoordinates(){
        System.out.print("Enter coordinates [-10; 10] <x> <y> <z>: ");
        Scanner scan = new Scanner(System.in);
        try {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int z = scan.nextInt();

            int min = -10;
            int max = 10;
            if (x < min || x > max || y < min || y > max || z < min || z > max) {
                throw new InputMismatchException();
            }
           return new Coordinates(x, y, z);
        }
        catch (InputMismatchException e){
            return askCoordinates();
        }
    }


    private void searchWorld(List<Creature> world, Coordinates target){
        while(true){
            for (int i = 0; i < 100; i++){
                System.out.print(".");
                engine.generateWorld(world, members, this);
                for (Creature creature : world){
                    if (creature.equals(this) && creature.getCoordinates().equals(target)){
                        System.out.println();
                        return;
                    }
                }
            }

            System.out.println();
        }
    }


}
