package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;
import game.actions.RefillAction;
import game.items.Water;

/**
 * A fountain that provides different effects that will help player in his journey
 */
public abstract class Fountain extends Ground {
    /**
     * The capacity of the fountain
     */
    private final int capacity = 10;

    /**
     * current water storage of the fountain
     */
    private int current;

    /**
     * timer for fountain's personal use
     */
    private int timer = 0;

    /**
     * boolean to determine whether to tick the timer or not
     */
    private boolean counting = false;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        current = capacity;
    }

    /**
     * Allows player to refill the bottle from the fountain
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a collection of actions (refill actions)
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (direction.length() == 0) {
            if (actor.hasCapability(Status.IS_PLAYER) && actor.hasCapability(Status.HAS_BOTTLE)) {
                actions.add(new RefillAction(this));
            }
        }
        return actions;
    }

    /**
     * An abstract method to get the water, allow subclasses to define what water they provide
     * @return the water to consume
     */
    public abstract Water getWater();

    /**
     * Lets the fountain experience the passage of time, starts timer of 5 turns when fountain becomes empty
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (timer >= 0) {
            timer--;
        }
        if (current == 0 && !counting) {
            timer = 5;
            counting = true;
        }
        if (timer == 0) {
            current = capacity;
            counting = false;
        }
        if (hasCapability(Status.ENEMY_DRINK)) {
            exhaustWater();
            removeCapability(Status.ENEMY_DRINK);
        }
    }

    /**
     * Reduces the water storage of the fountain by one water slot
     */
    public void exhaustWater() {
        current--;
    }

    /**
     * Getter for the current water storage of the fountain
     * @return An integer indicating the number of water instances left in the fountain
     */
    public int getCurrent() {
        return current;
    }

    /**
     * Getter for the capacity of the water storage of the fountain
     * @return An integer indicating the capacity of the fountain
     */
    public int getCapacity() {
        return capacity;
    }
}
