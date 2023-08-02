package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.items.Water;

/**
 * A class that allows Non-Player-Controlled (NPC) actors to drink water.
 */
public class DrinkBehaviour implements Behaviour {
    /**
     * The water to drink
     */
    private final Water target;

    /**
     * Constructor
     *
     * @param target The water to drink
     */
    public DrinkBehaviour(Water target) {
        this.target = target;
    }

    /**
     * Returns a consume action of the water
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return a consume action of the water
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new ConsumeAction(target, target.getBuff());
    }
}
