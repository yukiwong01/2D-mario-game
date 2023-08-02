package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;

import java.util.Random;

/**
 * An abstract class representing a tree
 */
public abstract class Tree extends HighGround implements Resettable {
    /**
     * age of tree
     */
    private int age = 0;
    /**
     * Random number generator
     */
    protected Random rand = new Random();


    /**
     * Constructor.
     */
    public Tree(char displayChar, int successRate, int fallDamage) {
        super(displayChar, successRate, fallDamage);
        registerInstance();
    }

    /**
     * Getter of age of tree
     *
     * @return age of tree
     */
    public int getAge() {
        return age;
    }

    /**
     * Increment the tree's age every turn, might turn tree to ground in the case of a game reset
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;
        if (hasCapability(Status.RESET)) {
            if (rand.nextInt(100) < 50) {
                location.setGround(new Dirt());
            }
        }
    }

    /**
     * Reset the tree instance
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
