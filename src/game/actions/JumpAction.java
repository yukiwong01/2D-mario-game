package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import java.util.Random;

/**
 * A class which is extended from the action class which inherits the attributes.
 * A class for player to have action of jumping in game to various high locations.
 */
public class JumpAction extends Action {
    /**
     * the direction which the player will move
     */
    private final String direction;
    /**
     * the high location the player wants to jump to
     */
    private final Location highLocation;
    /**
     * the success rate of the jump
     */
    private int successRate;
    /**
     * the fall damage the player will take when the jump fails
     */
    private final int fallDamage;
    /**
     * to randomly select the number as a chance of success rate
     */
    private final Random rand = new Random();

    /**
     * Constructor.
     *
     * @param direction    the direction player moved
     * @param highLocation the location to jump to
     * @param successRate  the success rate of the jump
     * @param fallDamage   the fall damage if the jump fails
     */
    public JumpAction(String direction, Location highLocation, int successRate, int fallDamage) {
        this.direction = direction;
        this.highLocation = highLocation;
        this.successRate = successRate;
        this.fallDamage = fallDamage;
    }

    /**
     * Perform the Jump Action.
     *
     * @param actor The actor performing the jump action.
     * @param map   The map the actor is on.
     * @return a description of whether the jump was successful or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {//When Mario consumes Super Mushroom, he can jump freely with a 100% success rate and no fall damage.
        if (!map.isAnActorAt(highLocation)) {
            if (actor.hasCapability(Status.TALL)) {
                successRate = 100;
            }

            // When there's a successful jump
            if (rand.nextInt(100) < successRate) {
                map.moveActor(actor, highLocation);
                return "Yay! Successful Jump to " + highLocation.getGround().getClass().getSimpleName() +
                        " (" + highLocation.x() + "," + highLocation.y() + ")";
            }
            // When there's an unsuccessful jump
            else {
                actor.hurt(fallDamage);
                return "Ouch! Unsuccessful Jump to " + highLocation.getGround().getClass().getSimpleName() +
                        " (" + highLocation.x() + "," + highLocation.y() + ")" + ", " +
                        actor + " withstands " + fallDamage + " fall damage";
            }
        } else {
            return "High Location Occupied";
        }
    }

    /**
     * Returns a descriptive string for the jump action
     *
     * @param actor The actor performing the jump action.
     * @return the text we put on the menu to show the option to jump
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + highLocation.getGround().getClass().getSimpleName() +
                " (" + highLocation.x() + "," + highLocation.y() + ") to the " + direction;
    }
}
