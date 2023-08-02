package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.WalletManager;
import game.items.ValuableItemManager;

/**
 * Special Action for trading with Toad using coins collected.
 */
public class TradingAction extends Action {
    /**
     * Toad
     */
    private final Actor target;

    /**
     * The item Toad will be traded with player
     */
    private final Item item;

    /**
     * Constructor
     *
     * @param target Toad
     * @param item   The item Toad will be traded with player
     */
    public TradingAction(Actor target, Item item) {
        this.target = target;
        this.item = item;
    }

    /**
     * Perform the Trading Action.
     *
     * @param actor The actor performing the trading action.
     * @param map   The map the actor is on.
     * @return a description of trading the items with its prices.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result;
        if (WalletManager.getInstance().getActor(actor).getBalance() < ValuableItemManager.getInstance().getItem(item).getValue()) {
            result = "You don't have enough coins!";
        } else {
            actor.addItemToInventory(item);
            WalletManager.getInstance().getActor(actor).walletDeduct(ValuableItemManager.getInstance().getItem(item).getValue());
            result = actor + " bought " + item + " for $" + ValuableItemManager.getInstance().getItem(item).getValue() + " from " + target + "\n";
            result += actor + " has a remaining balance of $" + WalletManager.getInstance().getActor(actor).getBalance();
        }
        return result;
    }

    /**
     * Returns a descriptive string for the trading action
     *
     * @param actor The actor performing the trading action.
     * @return the text we put on the menu to show the option to trade with Toad
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " bought " + item + " ($" + ValuableItemManager.getInstance().getItem(item).getValue() + ")";
    }
}
