package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.BottleManager;
import game.items.ConsumableItem;
import game.items.Water;

/**
 * A class for actors to consume consumable items to get buffs in game.
 */
public class ConsumeAction extends Action {
    /**
     * Item to consume
     */
    private final ConsumableItem target;

    /**
     * Buff given by the item to consume
     */
    private final Status buff;

    /**
     * Constructor.
     *
     * @param target item to consume
     */
    public ConsumeAction(ConsumableItem target) {
        this.target = target;
        this.buff = null;
    }

    /**
     * Constructor.
     *
     * @param target item to consume
     * @param buff   buff given by the item to consume
     */
    public ConsumeAction(ConsumableItem target, Status buff) {
        this.target = target;
        this.buff = buff;
    }

    /**
     * Perform the Consume Action.
     *
     * @param actor The actor performing the consume action.
     * @param map   The map the actor is on.
     * @return a description of the item consumption
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(buff);
        // if item is in inventory, remove it
        if (!target.hasCapability(Status.IS_BOTTLE)) {
            actor.removeItemFromInventory(target);
        }
        // if item on the ground, remove it
        map.locationOf(actor).removeItem(target);
        if (buff == Status.TALL) {
            actor.increaseMaxHp(50);
        } else if (buff == Status.INVINCIBLE) {
            actor.addCapability(Status.CONSUMED_POWER_STAR);
        } else if (buff == Status.INVISIBILITY) {
            actor.addCapability(Status.CONSUMED_SNEAKY_BERRY);
        } else if (buff == Status.HEALING_WATER) {
            actor.heal(50);
            actor.removeCapability(Status.CURSED);
        } else if (buff == Status.POWER_WATER) {
            actor.addCapability(Status.CONSUMED_POWER_WATER);
        } else if (buff == Status.BOTTLE) {
            Water water = BottleManager.getInstance().getActor(actor).getBottle().getWater();
            ConsumeAction drink = new ConsumeAction(water, water.getBuff());
            drink.execute(actor, map);
        }
        if (actor.hasCapability(Status.IS_ENEMY)) {
            map.locationOf(actor).getGround().addCapability(Status.ENEMY_DRINK);
        }
        return actor + " consumed " + target;
    }

    /**
     * Returns a descriptive string for the consume action
     *
     * @param actor The actor performing the consume action.
     * @return the text we put on the menu to show the option to consume, with the lifespan of the item if applicable
     */
    @Override
    public String menuDescription(Actor actor) {
        String result = actor + " consumes " + target;
        if (target.getLifespan() > 0) {
            result += " - " + target.getLifespan() + " turn(s) remaining";
        }
        if (buff == Status.BOTTLE) {
            result += " - " + BottleManager.getInstance().getActor(actor).getBottle().getWaterList();
        }
        return result;
    }
}
