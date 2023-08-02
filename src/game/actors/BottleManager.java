package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * A class to manage the bottle
 */
public class BottleManager {
    /**
     * Array list of actors that can have a bottle
     */
    private ArrayList<HasBottle> actors;

    /**
     * instance of BottleManager
     */
    private static BottleManager instance;

    /**
     * Private Constructor.
     */
    private BottleManager() { // 1-  private to prevent anyone else from instantiating
        actors = new ArrayList<>();
    }

    /**
     * Get the instance of BottleManager if it exists, creates one if it does not exist
     *
     * @return instance of BottleManager
     */
    public static BottleManager getInstance() {
        if (instance == null) {
            instance = new BottleManager();
        }
        return instance;
    }

    /**
     * Append HasBottle actor into the array list created
     *
     * @param actor hasWallet instance
     */
    public void appendActor(HasBottle actor) {
        this.actors.add(actor);
    }

    /**
     * Getter for the HasBottle array list
     *
     * @return an array list of the HasBottle
     */
    public ArrayList<HasBottle> getActors() {
        return new ArrayList<>(this.actors);
    }

    /**
     * Getter for the actor in parameter in HasBottle array list, null if actor does not have bottle
     *
     * @return the HasBottle object (actor) from the list, null if the actor is not in the list
     */
    public HasBottle getActor(Actor actorTarget) {
        for (HasBottle actor : actors) {
            if (actor.equals(actorTarget)) {
                return actor;
            }
        }
        return null;
    }
}
