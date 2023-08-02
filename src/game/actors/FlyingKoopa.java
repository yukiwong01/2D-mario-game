package game.actors;

import game.Status;

/**
 * Class representing a koopa that can fly
 */
public class FlyingKoopa extends Koopa {
    /**
     * Constructor.
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        addCapability(Status.IS_FLYING_KOOPA);
    }
}
