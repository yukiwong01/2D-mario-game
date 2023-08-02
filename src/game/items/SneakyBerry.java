package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import game.Status;

/**
 * A berry that can make the player invisible from enemy
 */
public class SneakyBerry extends ConsumableItem implements Valuable {
    /**
     * Constructor
     */
    public SneakyBerry() {
        super("Sneaky Berry", '@', Status.INVISIBILITY);
        addToValuableItems();
    }

    /**
     * Returns the value of the sneaky berry
     *
     * @return An integer containing the value of the sneaky berry
     */
    @Override
    public int getValue() {
        return 500;
    }

    /**
     * Allows Ghost actors to drop Sneaky Berries after they are defeated
     * @param actor actor that carries out the action of dropping the super mushroom
     * @return A new DropItemAction if the actor is a ghost, else null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (actor.hasCapability(Status.IS_GHOST)) {
            return new DropItemAction(this);
        }
        return null;
    }
}