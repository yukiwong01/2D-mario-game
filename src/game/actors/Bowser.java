package game.actors;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Key;

/**
 * A big boss that kidnapped Princess Peach.
 * Class representing a Bowser enemy.
 */
public class Bowser extends Enemy {
    /**
     * The location the Bowser is at
     */
    private final Location location;

    /**
     * Constructor
     *
     * @param location The location the Bowser is at
     */
    public Bowser(Location location) {
        super("Bowser", 'B', 500, 80, "punches");
        addItemToInventory(new Key());
        addCapability(Status.IS_BOWSER);
        getBehaviours().remove(10);
        this.location = location;
    }

    /**
     * Resets the Bowser instance.
     */
    @Override
    public void resetInstance() {
        super.resetInstance();
        heal(getMaxHp());
        // clear all behaviours - stand still
        getBehaviours().clear();
        // original position
        if (!location.containsAnActor()) {
            location.map().moveActor(this, location);
        }
    }
}
