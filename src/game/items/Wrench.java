package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * Class representing the Wrench weapon implements the valuable interface
 */
public class Wrench extends WeaponItem implements Valuable {

    /**
     * Constructor.
     */
    public Wrench() {
        super("Wrench", 'W', 50, "wrenched", 80);
        addCapability(Status.IS_A_WRENCH);
        addToValuableItems();
    }

    /**
     * Returns the value of the wrench
     *
     * @return An integer containing the value of the wrench
     */
    @Override
    public int getValue() {
        return 200;
    }
}
