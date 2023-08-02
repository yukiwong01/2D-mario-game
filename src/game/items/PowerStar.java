package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Class representing the power star item implements the valuable and resettable interface
 */
public class PowerStar extends ConsumableItem implements Valuable {
    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*', Status.INVINCIBLE,10);
        addToValuableItems();
    }

    /**
     * Returns the value of the power star.
     *
     * @return An integer containing the value of the power star.
     */
    @Override
    public int getValue() {
        return 600;
    }

    /**
     * Inform a carried power star of the passage of time.
     * Removes the power star from the actor's inventory if its age is already 10 turns.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (getLifespan() == 0) {
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Inform power star on the ground of the passage of time.
     * Removes the power star from the location if its age is already 10 turns.
     *
     * @param currentLocation The location of the ground on which contains the item.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if (getLifespan() == 0) {
            currentLocation.removeItem(this);
        }
    }
}
