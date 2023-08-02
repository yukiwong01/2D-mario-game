package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.actors.Player;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
    /**
     * Constructor.
     */
    public Floor() {
        super('_');
    }

    /**
     * To check if an actor (player or flying koopa) can enter a ground
     *
     * @param actor the Actor to check
     * @return true if the Actor is not an enemy, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.IS_PLAYER);
    }
}
