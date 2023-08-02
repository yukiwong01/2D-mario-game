package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;

/**
 * A class for players to reset the game
 */
public class ResetAction extends Action {

    /**
     * Perform the Reset Action.
     *
     * @param actor The actor performing the reset action.
     * @param map   The map the actor is on.
     * @return a description of the reset of the game
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return actor + " has reset the game.";
    }

    /**
     * Returns a descriptive string for the reset action
     *
     * @param actor The actor performing the reset action.
     * @return the text we put on the menu to show the option to reset
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets the game";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @return 'r' which is the key of a reset action
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
