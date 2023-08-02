package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * Special Action for unlocking the Princess Peach's handcuffs with keys.
 */
public class UnlockAction extends Action {
    /**
     * Princess Peach
     */
    private final Actor target;

    /**
     * Constructor
     *
     * @param target Princess Peach
     */
    public UnlockAction(Actor target) {
        this.target = target;
    }

    /**
     * Perform the Unlocking Action.
     *
     * @param actor The actor performing the unlocking action.
     * @param map   The map the actor is on.
     * @return a description of victory message after unlocking Princess Peach's handcuffs.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HAS_KEY)) {
            target.removeCapability(Status.LOCKED);
            // end game
            map.removeActor(actor);
            return "Congratulations Mario, and thank you for saving me!";
        }
        return null;
    }

    /**
     * Returns a descriptive string for the unlocking action
     *
     * @param actor The actor performing the unlocking action.
     * @return the text we put on the menu to show the action of unlocking Princess Peach's handcuffs
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks " + target + "'s handcuffs with the key!";
    }
}
