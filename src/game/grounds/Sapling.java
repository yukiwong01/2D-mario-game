package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Coin;

/**
 * A class representing a sapling
 */
public class Sapling extends Tree {
    /**
     * chance of spawning a coin
     */
    private static final int coinChance = 10;

    /**
     * Constructor.
     * 80% success rate, 20 fall damage for sapling
     */
    public Sapling() {
        super('t', 80, 20);
    }//80% success rate, 20 fall damage

    /**
     * for spawning the coin if condition is met
     *
     * @param location location of the ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (!hasCapability(Status.RESET)) {
            if (getAge() < 10) { //10 turns to grow into a tall tree/Mature(T)
                if (rand.nextInt(100) < coinChance) { //10% chance to produce/spawn a coin ($20)
                    location.addItem(new Coin(20)); // add $20 on the sapling location
                }
            } else {
                location.setGround(new Mature()); //grow into a tall tree/Mature(T) if conditions are met
            }
        }
        removeCapability(Status.RESET);
    }
}
