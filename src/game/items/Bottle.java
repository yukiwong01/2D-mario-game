package game.items;

import game.Status;

import java.util.Stack;

/**
 * A class representing the bottle that is used to refill the water from fountain
 */
public class Bottle extends ConsumableItem {
    /**
     * Stack used to store the water from fountain
     */
    private final Stack<Water> waterList = new Stack<>();

    /**
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', Status.BOTTLE);
        addCapability(Status.IS_BOTTLE);
    }

    /**
     * Accessor to get the stack
     *
     * @return a stack containing the Water
     */
    public Stack<Water> getWaterList() {
        return waterList;
    }

    /**
     * Add water into the bottle stack
     *
     * @param water water that can be refilled from fountain
     */
    public void addWater(Water water) {
        waterList.push(water);
    }

    /**
     * Accessor to get the water in the stack
     *
     * @return water that is being refilled from fountain
     */
    public Water getWater() {
        return waterList.pop();
    }
}
