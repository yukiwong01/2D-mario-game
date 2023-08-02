package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;

/**
 * Class representing a consumable item.
 */
public abstract class ConsumableItem extends Item {
    /**
     * The buff the consumable item provides to an actor
     */
    private final Status buff;

    /**
     * The lifespan of a consumable item, -1 if not applicable
     */
    private int lifespan;

    /**
     * Constructor for consumable items without a lifespan
     *
     * @param name        The name of this Item
     * @param displayChar The character used to represent this item if it is on the ground
     * @param buff        The buff the consumable item provides to an actor
     */
    public ConsumableItem(String name, char displayChar, Status buff) {
        super(name, displayChar, true);
        this.buff = buff;
        this.lifespan = -1;
        addAction(new ConsumeAction(this, buff));
    }

    /**
     * Constructor for consumable items with a lifespan
     *
     * @param name        The name of this Item
     * @param displayChar The character used to represent this item if it is on the ground
     * @param buff        The buff the consumable item provides to an actor
     * @param lifespan    The lifespan of the consumable item
     */
    public ConsumableItem(String name, char displayChar, Status buff, int lifespan) {
        super(name, displayChar, true);
        this.buff = buff;
        this.lifespan = lifespan;
        addAction(new ConsumeAction(this, buff));
    }

    /**
     * Create and return an action to drop this Item.
     *
     * @param actor actor that carries out the action of dropping the super mushroom
     * @return null as current consumable items cannot be dropped by players
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Returns the buff the consumable item provides to an actor
     *
     * @return A status which is the buff the consumable item provides to an actor
     */
    public Status getBuff() {
        return buff;
    }

    /**
     * Returns the lifespan of the consumable item
     *
     * @return An integer which is the lifespan of the consumable item, -1 if not applicable
     */
    public int getLifespan() {
        return lifespan;
    }

    /**
     * Inform a consumable item on the ground of the passage of time.
     * Decrements its lifespan if applicable.
     *
     * @param currentLocation The location of the ground on which contains the consumable item.
     */
    @Override
    public void tick(Location currentLocation) {
        if (lifespan > 0) {
            lifespan--;
        }
    }

    /**
     * Inform a carried consumable item of the passage of time.
     * Decrements its lifespan if applicable.
     *
     * @param currentLocation The location of the actor carrying this consumable item.
     * @param actor           The actor carrying this consumable item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (lifespan > 0) {
            lifespan--;
        }
    }
}
