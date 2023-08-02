package game.items;

import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;

/**
 * A class to manage all valuable items to retrieve their values
 */
public class ValuableItemManager {
    /**
     * Array list of Valuable objects
     */
    private ArrayList<Valuable> items;

    /**
     * instance of ValuableItems
     */
    private static ValuableItemManager instance;

    /**
     * Private Constructor.
     */
    private ValuableItemManager() { // 1-  private to prevent anyone else from instantiating
        items = new ArrayList<>();
    }

    /**
     * Get the instance of ValuableItems
     *
     * @return instance of ValuableItems
     */
    public static ValuableItemManager getInstance() {
        if (instance == null) {
            instance = new ValuableItemManager();
        }
        return instance;
    }

    /**
     * Append ValuableItem into the array list created
     *
     * @param item ValuableItem
     */
    public void appendItem(Valuable item) {
        this.items.add(item);
    }

    /**
     * Getter for the Valuable array list
     *
     * @return an array list of Valuable
     */
    public ArrayList<Valuable> getItems() {
        return new ArrayList<>(this.items);
    }

    /**
     * Getter for the item in parameter in Valuable array list
     *
     * @return the Valuable object (item) from the list
     */
    public Valuable getItem(Item itemTarget) {
        for (Valuable item : items) {
            if (item.equals(itemTarget)) {
                return item;
            }
        }
        return null;
    }
}
