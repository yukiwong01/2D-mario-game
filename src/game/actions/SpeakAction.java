package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;

/**
 * Special Action for speaking with Toad.
 */

public class SpeakAction extends Action {
    /**
     * Toad
     */
    private final Actor target;

    /**
     * The direction where toad currently at
     */
    private final String direction;

    /**
     * Constructor
     *
     * @param target    Toad
     * @param direction The direction where toad currently at
     */
    public SpeakAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform the Speak Action.
     *
     * @param actor The actor performing the speak action.
     * @param map   The map the actor is on.
     * @return  a description of one of the monologue.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Monologue m = new Monologue(actor);
        return target + ": \"" + m.speechText() + "\"";
    }

    /**
     * Returns a descriptive string for the speak action
     *
     * @param actor The actor performing the speak action.
     * @return the text we put on the menu to show the option to speak with Toad
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to " + target + " at " + direction;
    }
}
