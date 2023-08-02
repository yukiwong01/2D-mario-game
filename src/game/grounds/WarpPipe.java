package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.PiranhaPlant;

/**
 * A warp pipe that spawn piranha plant, can teleport to other map
 */
public class WarpPipe extends HighGround implements Resettable {
    /**
     * boolean for determining whether to spawn Piranha Plant or not
     */
    private boolean firstTick = true;

    /**
     * The destination the Warp Pipe brings actors to
     */
    private final Location destination;

    /**
     * Constructor.
     */
    public WarpPipe(Location destination) {
        super('C', 100, 0);
        this.destination = destination;
        registerInstance();
    }

    /**
     * Allows player to teleport to another map
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a collection of actions (teleport actions)
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (direction.length() == 0) {
            actions.add(new TeleportAction(destination));
        }
        return actions;
    }

    /**
     * Spawns Piranha Plant in second turn or after reset, if possible
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (firstTick && !location.containsAnActor()) {
            location.addActor(new PiranhaPlant());
        }
        firstTick = false;   // only second turn (after reset too) needs to spawn, otherwise no Piranha Plant spawn needed
    }

    /**
     * Resets the Warp Pipe
     */
    @Override
    public void resetInstance() {
        firstTick = true;
    }
}
