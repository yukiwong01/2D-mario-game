package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

/**
 * A class that represents lava that can hurt anyone
 */
public class Lava extends DamageGround {
    /**
     * Constructor.
     */
    public Lava() {
        super('L', 15);
    }

    /**
     * Disallow enemies to enter the ground
     *
     * @param actor the Actor to check
     * @return true if player steps on it; false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.IS_ENEMY);
    }

}
