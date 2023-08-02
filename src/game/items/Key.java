package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * A key to unlock Princess Peach's handcuffs
 */
public class Key extends Item {
    /***
     * Constructor.
     */
    public Key() {
        super("Key", 'k', true);
        addCapability(Status.IS_A_KEY);
    }

    /**
     * Create and return an action to drop the key.
     *
     * @param actor actor that carries out the action of dropping the key
     * @return a drop action if the actor is Bowser; else null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (actor.hasCapability(Status.IS_BOWSER)) {
            return new DropItemAction(this);
        }
        return null;
    }
}
