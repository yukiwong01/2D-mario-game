package game;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a class used for player to pick a random sentence to speak with Toad.
 */
public class Monologue {
    /**
     * Toad
     */
    protected Actor target;

    /**
     * Random object
     */
    protected Random rand = new Random();

    /**
     * Constructor
     *
     * @param target Toad
     */
    public Monologue(Actor target) {
        this.target = target;
    }

    /**
     * Picks a random sentence to speak with Toad.
     *
     * @return A string containing the sentence to speak with Toad
     */
    public String speechText() {
        ArrayList<String> lines = new ArrayList<>();

        if (!target.hasCapability(Status.HAS_WRENCH)) {
            lines.add("You might need a wrench to smash Koopa's hard shells.");
        }
        if (!target.hasCapability(Status.INVINCIBLE)) {
            lines.add("You better get back to finding the Power Stars.");
        }
        lines.add("The Princess is depending on you! You are our only hope.");
        lines.add("Being imprisoned in these walls can drive a fungus crazy :(");

        return lines.get(rand.nextInt(lines.size()));
    }


}
