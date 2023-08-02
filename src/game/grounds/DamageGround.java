package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing the grounds that can hurt the actor.
 */
public abstract class DamageGround extends Ground {
    /**
     * damage that cause to the actor who steps on the ground
     */
    private final int damagePerTick;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public DamageGround(char displayChar, int damagePerTick) {
        super(displayChar);
        this.damagePerTick = damagePerTick;
    }

    /**
     * Inform damage ground of the passage of time.
     * Hurt player every tick
     *
     * @param location The location of the damage ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            Actor target = location.getActor();
            GameMap map = location.map();
            target.hurt(damagePerTick);
            if (!target.isConscious()) {
                ActionList dropActions = new ActionList();
                // drop all items
                for (Item item : target.getInventory())
                    dropActions.add(item.getDropAction(target));
                for (Action drop : dropActions)
                    drop.execute(target, map);
                // remove actor
                map.removeActor(target);
            }
        }
    }
}
