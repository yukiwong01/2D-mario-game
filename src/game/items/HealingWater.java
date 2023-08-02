package game.items;

import game.Status;

/**
 * Healing water that increases the drinker's hit points/healing by 50 hit points
 */
public class HealingWater extends Water{
    /**
     * Constructor.
     */
    public HealingWater() {
        super("Healing Water", 'h', Status.HEALING_WATER);
    }
}
