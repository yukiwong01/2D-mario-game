package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * WalletManager is a class to manage all instances that possess wallets
 */
public class WalletManager {
    /**
     * Array list of instances that implement the hasWallet interface
     */
    private ArrayList<HasWallet> actors;

    /**
     * instance of WalletManager
     */
    private static WalletManager instance;

    /**
     * Private Constructor.
     */
    private WalletManager() { // 1-  private to prevent anyone else from instantiating
        actors = new ArrayList<>();
    }

    /**
     * Get the instance of WalletManager if it exists, creates one if it does not exist
     *
     * @return instance of WalletManager
     */
    public static WalletManager getInstance() {
        if (instance == null) {
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Append hasWallet Item into the array list created
     *
     * @param actor hasWallet instance
     */
    public void appendActor(HasWallet actor) {
        this.actors.add(actor);
    }

    /**
     * Getter for the hasWallet array list
     *
     * @return an array list of the hasWallet
     */
    public ArrayList<HasWallet> getActors() {
        return new ArrayList<>(this.actors);
    }

    /**
     * Getter for the actor in parameter in hasWallet array list, null if actor does not have wallet
     *
     * @return the hasWallet object (actor) from the list, null if the actor is not in the list
     */
    public HasWallet getActor(Actor actorTarget) {
        for (HasWallet actor : actors) {
            if (actor.equals(actorTarget)) {
                return actor;
            }
        }
        return null;
    }
}
