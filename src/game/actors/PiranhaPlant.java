package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Resettable;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * A scary plant that grows on warp pipes
 * Class representing piranha plant object
 */
public class PiranhaPlant extends Enemy{
    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150, 90, "chomps");
        // remove ability to wander
        getBehaviours().remove(10);
    }

    /**
     * Allows PiranhaPlant to be attacked by players.
     * Allows PiranhaPlant to attack players whenever they get into contact with one.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        getBehaviours().remove(2);
        return actions;
    }

    /**
     * Reset the PiranhaPlant status, increases additional 50hp and heal to max hp
     */
    @Override
    public void resetInstance() {
        resetMaxHp(getMaxHp() + 50);
    }
}
