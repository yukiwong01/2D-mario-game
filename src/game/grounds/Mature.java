package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.FlyingKoopa;
import game.actors.Koopa;
import game.actors.NormalKoopa;
import game.items.SneakyBerry;

import java.util.ArrayList;

/**
 * A class representing a mature tree.
 * Mature is an extension of tree with a 15% chance to spawn Koopa in every turn
 * and has 20% to wither and die (becomes Dirt) in every turn
 */
public class Mature extends Tree {
    /**
     * Chance of spawning Koopa in every turns
     */
    private static final int koopaChance = 15;//It has a 15% chance to spawn Koopa in every turn***

    /**
     * Chance of spawning Sneaky berry after it withers
     */
    private static final int SneakyChance = 1;

    /**
     * Chance of withering in
     */
    private static final int dieChance = 20;//It has 20% to wither and die (becomes Dirt) in every turn

    /**
     * List of fertile exits which can be spawned with a new Sprout
     */
    private final ArrayList<Exit> fertileExits = new ArrayList<>();

    /**
     * Constructor.
     * Mature(T): 70% success rate,  30 fall damage
     */
    public Mature() {
        super('T', 70, 30);
    }

    /**
     * To create the chance of mature spawning koopa while also the chance of a mature dying and turing back into dirt
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (!hasCapability(Status.RESET)) {
            if (location.getActor() == null) { //If an actor stands on it, it cannot spawn Koopa
                if (rand.nextInt(100) < koopaChance) { // if the random number generated is less than 15
                    if (rand.nextInt(100) < 50) {
                        location.map().addActor(new NormalKoopa(), location);// spawn Normal Koopa
                    } else {
                        location.map().addActor(new FlyingKoopa(), location);// spawn Flying Koopa
                    }
                }
            }
            if (getAge() % 5 == 0) { //For every 5 turns, It can grow a new sprout (+)
                //check randomly one of the surrounding fertile squares
                for (Exit exit : location.getExits()) { //check for possible spawn location
                    if (exit.getDestination().getGround().hasCapability(Status.FERTILE)) {//identify fertile ground as Dirt
                        fertileExits.add(exit);
                    }
                }
                fertileExits.get(rand.nextInt(fertileExits.size())).getDestination().setGround(new Sprout());
            }
            if (rand.nextInt(100) < dieChance) {//if random number generated is less than 20 then the mature will wither
                location.setGround(new Dirt());//after mature dies, convert location back to dirt
                if (rand.nextInt(100) < SneakyChance) {
                    location.addItem(new SneakyBerry());
                }
            }
        }
        removeCapability(Status.RESET);
    }
}

