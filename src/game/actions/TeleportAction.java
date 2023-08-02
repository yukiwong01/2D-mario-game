package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.WarpPipe;

/**
 * Special Action for teleporting the player from one map to the other map through Warp Pipes.
 */
public class TeleportAction extends Action {
    /**
     * Destination of the Teleportation
     */
    private final Location destination;

    /**
     * Constructor
     *
     * @param destination The location to teleport to.
     */
    public TeleportAction(Location destination) {
        this.destination = destination;
    }

    /**
     * Perform the Teleport Action.
     *
     * @param actor The actor performing the teleport action.
     * @param map   The map the actor is on.
     * @return a description of teleporting the player.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " teleports to ";
        destination.setGround(new WarpPipe(map.locationOf(actor)));
        if (destination.containsAnActor()) {
            destination.map().removeActor(destination.getActor());
        }
        destination.map().moveActor(actor, destination);
        if (actor.hasCapability(Status.AT_LAVA)) {
            actor.removeCapability(Status.AT_LAVA);
            result += "Main Zone";
        } else {
            actor.addCapability(Status.AT_LAVA);
            result += "Lava Zone";
        }
        return result;
    }

    /**
     * Returns a descriptive string for the teleporting action
     *
     * @param actor The actor performing the teleporting action.
     * @return the text we put on the menu to show the option to teleport the player
     */
    @Override
    public String menuDescription(Actor actor) {
        String result = actor + " teleports to ";
        if (actor.hasCapability(Status.AT_LAVA)) {
            result += "Main Zone";
        } else {
            result += "Lava Zone";
        }
        return result;
    }
}
