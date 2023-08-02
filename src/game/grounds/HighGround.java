package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.items.Coin;

/**
 * An abstract class that represents a high ground
 */
public abstract class HighGround extends Ground {
    /**
     * Success rate to jump onto high ground
     */
    private final int successRate;
    /**
     * Fall Damage if jump fails
     */
    private final int fallDamage;

    /**
     * Constructor.
     */
    public HighGround(char displayChar, int successRate, int fallDamage) {
        super(displayChar);
        this.successRate = successRate;
        this.fallDamage = fallDamage;
    }

    /**
     * Returns a list of allowable actions on this ground
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of allowable actions on this ground
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (direction.length() != 0 && !actor.hasCapability(Status.INVINCIBLE)) {
            actions.add(new JumpAction(direction, location, successRate, fallDamage));
        }
        return actions;
    }

    /**
     * To check if an actor can enter a ground
     *
     * @param actor the Actor to check
     * @return true if the Actor is invincible & it is flying koopa, false otherwise
     */
    public boolean canActorEnter(Actor actor) {
        return (actor.hasCapability(Status.INVINCIBLE) || actor.hasCapability(Status.IS_FLYING_KOOPA));
    }

    /**
     * Sets the ground to Dirt and spawns a coin when this ground is destroyed by an invincible actor
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            if (location.getActor().hasCapability(Status.INVINCIBLE)) {
                location.setGround(new Dirt());
                location.addItem(new Coin(5));
            }
        }
    }
}
