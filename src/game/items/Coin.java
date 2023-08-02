package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;
import game.actions.CollectAction;

/**
 * Class representing the coin item implements the valuable and resettable interface.
 */
public class Coin extends Item implements Valuable, Resettable {
    /**
     * The value of the coin
     */
    private final int value;

    /***
     * Constructor.
     */
    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
        addToValuableItems();
        registerInstance();
    }

    /**
     * Returns the value of the coin
     *
     * @return An integer containing the value of the coin
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * An action to pick up the coin
     *
     * @param actor actor that carries out the action of picking up the coin
     * @return An action to pick up the coin
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new CollectAction(this);
    }

    /**
     * An action to drop the coin
     *
     * @param actor actor that carry the action of picking up the coin
     * @return null as coins cannot be dropped once collected
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Inform the coin on the ground of the passage of time.
     * Removes the if the player resets the game.
     *
     * @param currentLocation The location of the item.
     */
    @Override
    public void tick(Location currentLocation) {
        if (hasCapability(Status.RESET)) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * Resets the Coin instance.
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }

}
