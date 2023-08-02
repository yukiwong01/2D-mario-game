package game.items;

import game.Status;

/**
 * Power water that increases the drinker's base/intrinsic attack damage by 15
 */
public class PowerWater extends Water {
    /**
     * Constructor.
     */
    public PowerWater() {
        super("Power Water", 'a', Status.POWER_WATER);
    }
}
