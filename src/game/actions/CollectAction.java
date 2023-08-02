package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.WalletManager;
import game.items.Coin;

/**
 * A class for player to pick up the coin from ground and added into wallet.
 */

public class CollectAction extends PickUpItemAction {
    /**
     * The value of the coin to be picked up.
     */
    private final int value;

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public CollectAction(Coin item) {
        super(item);
        value = item.getValue();
    }

    /**
     * Pick up the coins on the ground and add it into the wallet.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The description of action to pick up the coins.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (WalletManager.getInstance().getActors().contains(actor)) {
            WalletManager.getInstance().getActor(actor).walletIncrease(value);
        }
        return super.execute(actor, map);
    }
}
