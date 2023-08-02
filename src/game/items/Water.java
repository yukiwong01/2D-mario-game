package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.Status;

/**
 * Class representing water that can be consumable
 */
public abstract class Water extends ConsumableItem {
    /**
     * Constructor.
     */
    public Water(String name, char displayChar, Status buff) {
        super(name, displayChar, buff);
    }

    /**
     * Water should not be picked up
     * @param actor actor performing PickUpAction
     * @return null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }
}
