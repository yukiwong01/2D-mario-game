package game.actors;

import game.items.Bottle;
import game.items.Water;

/**
 * An interface for actors that have a bottle
 */
public interface HasBottle {
    /**
     * Add water into the bottle
     *
     * @param water the water from fountain
     */
    void addWater(Water water);

    /**
     * Retrieve the bottle of the actor
     *
     * @return Bottle of the actor
     */
    Bottle getBottle();

    /**
     * Add a bottle to the actor
     *
     * @param newBottle a Bottle instance
     */
    void addBottle(Bottle newBottle);

    /**
     * Add an actor that has bottle to the ArrayList of the BottleManager
     */
    default void addToBottles() {
        BottleManager.getInstance().appendActor(this);
    }
}
