package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.BottleManager;
import game.items.Bottle;

/**
 * A class for actors to get the bottles to store the healing water from fountain for healing purposes.
 */
public class GetBottleAction extends Action {

    /**
     * Perform the Get Bottle Action.
     *
     * @param actor The actor performing the get bottle action.
     * @param map   The map the actor is on.
     * @return a description of the getting the bottle.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        BottleManager.getInstance().getActor(actor).addBottle(new Bottle());
        actor.addCapability(Status.HAS_BOTTLE);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string for the get bottle action
     *
     * @param actor The actor performing the get bottle action.
     * @return the text we put on the menu to show the option to get the bottle
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " retrieves a bottle";
    }
}
