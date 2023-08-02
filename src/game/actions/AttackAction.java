package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.grounds.Fire;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private final Actor target;

    /**
     * The direction of incoming attack.
     */
    private final String direction;

    /**
     * Random number generator
     */
    private final Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform the Attack Action.
     *
     * @param actor The actor performing the attack action.
     * @param map   The map the actor is on.
     * @return a description of the result of the attack.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();

        if (!(rand.nextInt(100) <= weapon.chanceToHit()) && !actor.hasCapability(Status.INVINCIBLE)) {
            return actor + " misses " + target + ".";
        }

        String result;
        int damage;
        if (actor.hasCapability(Status.INVINCIBLE)) {
            result = actor + " instantly kills " + target + ".";
        } else if (target.hasCapability(Status.INVINCIBLE)) {
            result = actor + "'s attack is useless.";
        } else if (actor.hasCapability(Status.HAS_WRENCH) && target.hasCapability(Status.DORMANT)) {    // break shell if dormant
            target.removeCapability(Status.DORMANT);    // break shell if dormant
            result = actor + " destroys the shell of " + target + " and kills it.";
        } else {
            damage = weapon.damage();
            result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
            target.hurt(damage);
        }

        if (actor.hasCapability(Status.IS_BOWSER)) {
            map.locationOf(target).setGround(new Fire());
        }

        if (actor.hasCapability(Status.IS_GHOST)) {
            target.addCapability(Status.ATTACKED_BY_CURSE);
            target.addCapability(Status.CURSED);
        }

        if (!target.isConscious() || actor.hasCapability(Status.INVINCIBLE)) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";
        }

        return result;
    }

    /**
     * Returns a descriptive string for the attack action
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu to show the option to attack
     */
    @Override
    public String menuDescription(Actor actor) {
        String result = actor + " attacks " + target + " at " + direction;
        if (target.hasCapability(Status.DORMANT)) {
            result = actor + " destroys " + target + "'s shell and kills it";
        }
        return result;
    }
}
