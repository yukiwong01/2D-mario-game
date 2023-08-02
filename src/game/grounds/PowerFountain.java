package game.grounds;

import game.Status;
import game.items.PowerWater;
import game.items.Water;

/**
 * A fountain that provides water that increases the drinker's base/intrinsic attack damage by 15
 */
public class PowerFountain extends Fountain {
    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A');
        addCapability(Status.POWER_WATER);
    }

    /**
     * Get the water from the fountain
     *
     * @return A PowerWater instance
     */
    @Override
    public Water getWater() {
        return new PowerWater();
    }
}
