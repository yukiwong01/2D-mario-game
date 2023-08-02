package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.BottleManager;
import game.grounds.Fountain;

/**
 * Special Action for refilling all the actors' bottles for drinking purposes
 */
public class RefillAction extends Action {
    /**
     * the fountain which the actor will refill its bottle
     */
    private final Fountain fountain;

    /**
     * Constructor
     *
     * @param fountain the fountain which the actor will refill its bottle
     */
    public RefillAction(Fountain fountain) {
        this.fountain = fountain;
    }

    /**
     * Perform the Refill Action.
     *
     * @param actor The actor performing the refill action.
     * @param map   The map the actor is on.
     * @return a description of the refilling the bottle from which fountain.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (fountain.getCurrent() > 0) {
            BottleManager.getInstance().getActor(actor).addWater(fountain.getWater());
            fountain.exhaustWater();
            return actor + " refills bottle from " + fountain.getClass().getSimpleName();
        }
        return "Unsuccessful refill as " + fountain.getClass().getSimpleName() + " is fully exhausted!";
    }

    /**
     * Returns a descriptive string for the refill action
     *
     * @param actor The actor performing the refill action.
     * @return the text we put on the menu to show the option to refill from which fountain and its capacity
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + fountain.getClass().getSimpleName() + " (" + fountain.getCurrent() + "/" + fountain.getCapacity() + ")";
    }
}
