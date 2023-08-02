package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import game.Status;

/**
 * Class representing the super mushroom item implements the valuable interface
 */
public class SuperMushroom extends ConsumableItem implements Valuable {
    /***
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', Status.TALL);
        addToValuableItems();
    }

    /**
     * Returns the value of the super mushroom
     *
     * @return An integer containing the value of the super mushroom
     */
    @Override
    public int getValue() {
        return 400;
    }

    /**
     * Create and return an action to drop this Item.
     *
     * @param actor actor that carries out the action of dropping the super mushroom
     * @return a drop action if the actor is Koopa; else null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (actor.hasCapability(Status.IS_KOOPA)) {
            return new DropItemAction(this);
        }
        return null;
    }
}
