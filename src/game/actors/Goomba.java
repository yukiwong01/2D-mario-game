package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

/**
 * A little fungus guy.
 * Class representing a Goomba enemy.
 */
public class Goomba extends Enemy {
    /**
     * Goomba's suicide chance in percentage
     */
    private static final int dieChance = 10;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     */
    public Goomba() {
        super("Goomba", 'g', 20, 10, "kicks");
    }

    /**
     * Select and return an action to perform on the current turn.
     * 10% chance to make Goomba self-destruct.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (isConscious()) {
            if (rand.nextInt(100) < dieChance) {
                map.removeActor(this);
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
