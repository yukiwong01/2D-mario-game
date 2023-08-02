package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Class representing a weapon item called curse, used by Ghost actors.
 */
public class Curse extends WeaponItem {
    /**
     * Constructor.
     *
     */
    public Curse() {
        super("Curse", 'c', 0, "curses", 100);
    }

    /**
     * Disallow Curse weapon to be dropped.
     * @param actor actor holding the weapon
     * @return null, as Curse cannot be dropped
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Disallow Curse weapon to be picked up.
     * @param actor actor wanting to pick up the weapon
     * @return null, as Curse cannot be picked up
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }
}
