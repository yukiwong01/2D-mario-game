package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;

/**
 * A class that allows Non-Player-Controlled (NPC) actors to attack players.
 */
public class AttackBehaviour implements Behaviour {
    /**
     * actor to attack
     */
    private final Actor target;

    /**
     * Constructor.
     *
     * @param target actor to attack
     */
    public AttackBehaviour(Actor target) {
        this.target = target;
    }

    /**
     * Returns an attack action if there is another hostile actor around the actor
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an attack action if there is another hostile actor around the actor, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor))
            return null;
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()) {
                if ((exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY) && !actor.hasCapability(Status.IS_GHOST))
                        || (actor.hasCapability(Status.IS_GHOST) && !exit.getDestination().getActor().hasCapability(Status.CURSED))) {
                    return new AttackAction(exit.getDestination().getActor(), exit.getDestination().toString());
                }
            }
        }
        return null;
    }
}
