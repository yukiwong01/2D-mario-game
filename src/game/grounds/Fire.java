package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents fire that can hurts anyone in 3 turns.
 */
public class Fire extends DamageGround {

    /**
     * The lifespan of fire
     */
    private int lifespan;

    /***
     * Constructor.
     */
    public Fire() {
        super('v', 20);
        lifespan = 3;
    }

    /**
     * Inform fire on the ground of the passage of time.
     * Removes the fire from the location if its age is already 3 turns.
     * Hurt anyone that step on it
     *
     * @param location The location of the fire.
     */
    @Override
    public void tick(Location location) {
        if (lifespan > 0) {
            lifespan--;
            super.tick(location);
        }
        if (lifespan == 0) {
            // remove it from map
            location.setGround(new Dirt());
        }
    }
}
