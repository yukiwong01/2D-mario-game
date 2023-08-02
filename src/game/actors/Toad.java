package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.actions.GetBottleAction;
import game.actions.SpeakAction;
import game.actions.TradingAction;
import game.items.PowerStar;
import game.items.SneakyBerry;
import game.items.SuperMushroom;
import game.items.Wrench;

/**
 * This is a class extended from Actor class, for the player to speak and trade items with Toad.
 */
public class Toad extends Ally {
    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", 'O', 100);
    }

    /**
     * Returns a new collection of the Actions that the player can do to the Toad.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of actions (trading action for wrench, power star, super mushroom and speak action)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (!otherActor.hasCapability(Status.HAS_WRENCH)) {
                actions.add(new TradingAction(this, new Wrench()));
            }
            if (!otherActor.hasCapability(Status.HAS_BOTTLE)) {
                actions.add(new GetBottleAction());
            }
            actions.add(new TradingAction(this, new SuperMushroom()));
            actions.add(new TradingAction(this, new PowerStar()));
            actions.add(new TradingAction(this, new SneakyBerry()));
            actions.add(new SpeakAction(this, direction));
        }
        return actions;
    }
}
