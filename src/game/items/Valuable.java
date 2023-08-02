package game.items;

/**
 * Valuable is an interface for all items that have value
 */
public interface Valuable {
    /**
     * Retrieve the value of the item
     *
     * @return Integer containing the value of the item
     */
    int getValue();

    /**
     * Add the instantiated item to the ArrayList of the ValuableItems
     */
    default void addToValuableItems() {
        ValuableItemManager.getInstance().appendItem(this);
    }
}
