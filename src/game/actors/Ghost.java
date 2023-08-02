package game.actors;

import game.Status;
import game.items.Curse;
import game.items.SneakyBerry;

public class Ghost extends Enemy {
    /**
     * Constructor.
     *
     */
    public Ghost() {
        super("Ghost", 'X', 200, 0, "curses");
        addItemToInventory(new SneakyBerry());
        addCapability(Status.IS_GHOST);
        addItemToInventory(new Curse());
    }
}
