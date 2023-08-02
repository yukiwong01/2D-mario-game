package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import game.Status;
import game.Resettable;
import game.actions.AttackAction;
import game.behaviours.*;
import game.items.HealingWater;
import game.items.PowerWater;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class representing an Enemy object that implements the resettable interface.
 */
public abstract class Enemy extends HostileActor implements Resettable {
    /**
     * List of behaviours mapped with their priority
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints, int baseAttack, String verb) {
        super(name, displayChar, hitPoints, baseAttack, verb);
        this.behaviours.put(10, new WanderBehaviour());
        registerInstance();
        addCapability(Status.IS_ENEMY);
    }


    protected Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Allows Enemy to be attacked by players.
     * Allows Enemy to attack players and follow them whenever they get into contact with one.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !hasCapability(Status.DORMANT)) {
            actions.add(new AttackAction(this, direction));      // can attack non-dormant
            behaviours.put(1, new AttackBehaviour(otherActor));
            behaviours.put(2, new FollowBehaviour(otherActor));
        }
        if (otherActor.hasCapability(Status.INVISIBILITY)) {
            behaviours.remove(1);
            behaviours.remove(2);
        }
        return actions;
    }


    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.RESET) && map.contains(this)) {
            map.removeActor(this);
        }
        if (map.contains(this) && !hasCapability(Status.DORMANT)) {    // not dormant
            if (map.locationOf(this).getGround().hasCapability(Status.HEALING_WATER)) {
                behaviours.put(3, new DrinkBehaviour(new HealingWater()));
            } else if (map.locationOf(this).getGround().hasCapability(Status.POWER_WATER)) {
                behaviours.put(3, new DrinkBehaviour(new PowerWater()));
            }
            for (Behaviour Behaviour : behaviours.values()) {
                Action action = Behaviour.getAction(this, map);
                if (action != null) {
                    behaviours.remove(3);
                    return action;
                }
            }
        }
        return new DoNothingAction();
    }

    /**
     * Resets the Enemy instance.
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
