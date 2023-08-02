package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.UnlockAction;

/**
 * A princess from Mushroom Kingdom
 * Class representing a princess.
 */
public class PrincessPeach extends Ally {

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 100);
        addCapability(Status.LOCKED);
    }

    /**
     * Returns a new collection of the Actions that the player can do to the Princess.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of actions (unlock action)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HAS_KEY)) {
            actions.add(new UnlockAction(this));
        }
        return actions;
    }
}
