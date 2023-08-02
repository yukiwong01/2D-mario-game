package game.grounds;

import game.Status;
import game.items.HealingWater;
import game.items.Water;

/**
 * A fountain that provides water that heals the actor's hit points
 */
public class HealthFountain extends Fountain {
    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H');
        addCapability(Status.HEALING_WATER);
    }

    /**
     * Get the water from the fountain
     *
     * @return a HealingWater instance
     */
    @Override
    public Water getWater() {
        return new HealingWater();
    }
}
