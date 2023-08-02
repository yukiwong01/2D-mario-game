package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;

public abstract class HostileActor extends Actor {
    /**
     * The base attack of the actor
     */
    private int baseAttack;

    /**
     * Verb used to describe their attack
     */
    private final String verb;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param baseAttack  the base attack of the actor
     * @param verb        verb used to describe their attack
     */
    public HostileActor(String name, char displayChar, int hitPoints, int baseAttack, String verb) {
        super(name, displayChar, hitPoints);
        this.baseAttack = baseAttack;
        this.verb = verb;
    }

    /**
     * Select and return an action to perform on the current turn.
     * If drink power water, increase 15 damages to the base attack.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action to perform on the current turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.CONSUMED_POWER_WATER)) {
            baseAttack += 15;
            removeCapability(Status.CONSUMED_POWER_WATER);
        }
        return null;
    }

    /**
     * Creates and returns an Intrinsic Weapon based on the actor's base attack.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(baseAttack, verb);
    }
}
