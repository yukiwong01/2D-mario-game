package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;
import game.items.SuperMushroom;

/**
 * A little turtle creature.
 * Class representing a Koopa enemy.
 */
public abstract class Koopa extends Enemy {
    /**
     * Constructor.
     *
     * @param name          the name of the Actor
     * @param displayChar   the character that will represent the Actor in the display
     * @param hitPoints     the Actor's starting hit points
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, 30, "punches");
        addItemToInventory(new SuperMushroom());
        addCapability(Status.IS_KOOPA);
    }

    /**
     * Returns true if Koopa is in dormant state or its hitPoints is positive
     *
     * @return true if Koopa is in dormant state or its hitPoints is positive, else false
     */
    @Override
    public boolean isConscious() {
        return hasCapability(Status.DORMANT) || super.isConscious();
    }

    /**
     * Allows Koopa to be attacked by players.
     * Allows Koopa to attack players and follow them whenever they get into contact with one.
     * Allows a dormant Koopa's shell to be destroyed if the Actor attacking has a wrench
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.HAS_WRENCH) && hasCapability(Status.DORMANT)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Deals damage to Koopa.
     * If Koopa's hitpoints reaches zero, Koopa goes into dormant state and its display character is set to 'D'.
     *
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        if (!super.isConscious()) {
            addCapability(Status.DORMANT);
            setDisplayChar('D');
        }
    }
}
