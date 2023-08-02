package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Goomba;

/**
 * Sprout class extends from the tree class with a unique attributes and includes the ability to spawn goomba
 */
public class Sprout extends Tree {
    /**
     * Chance to spawn Goomba in percentage
     */
    private static final int goombaChance = 10;  //has a 10% chance to spawn Goomba on its position in every turn

    /**
     * Constructor.
     * Sprout(+): 90% success rate, 10 fall damage
     */
    public Sprout() {
        super('+', 90, 10);
    }//Sprout(+): 90% success rate, 10 fall damage

    /**
     * To determine when the sprout grow into a sapling and where the Goomba would spawn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (!hasCapability(Status.RESET)) {
            if (getAge() < 10) { //It takes 10 turns to grow into a small tree/Sapling (t)
                if (location.getActor() == null) { //If any actor stands on it, it cannot spawn Goomba
                    if (rand.nextInt(100) < goombaChance) { //Spawn Goomba on its position in every turn if random number is smaller than 10
                        Goomba g = new Goomba(); //spawn a new goomba
                        location.addActor(g); // add actor at the location (spawn Goomba)
                    }
                }
            } else {
                location.setGround(new Sapling()); //grow into a small tree/Sapling (t)
            }
        }
        removeCapability(Status.RESET);
    }
}