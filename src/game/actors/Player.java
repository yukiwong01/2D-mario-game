package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Resettable;
import game.Status;
import game.actions.ResetAction;
import game.items.Bottle;
import game.items.Water;


/**
 * Class representing the Player implements the wallet, bottle and resettable interface.
 */
public class Player extends HostileActor implements HasWallet, Resettable, HasBottle {

    /**
     * The menu of the application
     */
    private final Menu menu = new Menu();
    /**
     * The wallet of the player
     */
    private int wallet;
    /**
     * the duration of the power star
     */
    private int powerStarDuration;
    /**
     * the duration of the sneaky berry
     */
    private int sneakyBerryDuration;
    /**
     * the bottle of the player
     */
    private Bottle bottle;

    /**
     * the remaining turns the player is cursed for
     */
    private int cursedDuration;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, 5, "punches");
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.IS_PLAYER);
        this.wallet = 1000;
        this.addToActors();
        registerInstance();
        this.addCapability(Status.CAN_RESET);
        this.addToBottles();
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return An action for the player to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        super.playTurn(actions, lastAction, map, display);
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        if (hasCapability(Status.CAN_RESET)) {
            actions.add(new ResetAction());
        }

        if (hasCapability(Status.CONSUMED_POWER_STAR)) {
            powerStarDuration = 10;
            removeCapability(Status.CONSUMED_POWER_STAR);
            heal(200);
        }

        if (hasCapability(Status.INVINCIBLE)) {
            display.println(this + " is INVINCIBLE!");
            powerStarDuration--;
            if (powerStarDuration == 0) {
                removeCapability(Status.INVINCIBLE);
            }
        }

        if (hasCapability(Status.CONSUMED_SNEAKY_BERRY)) {
            sneakyBerryDuration = 10;
            removeCapability(Status.CONSUMED_SNEAKY_BERRY);
        }

        if (hasCapability(Status.INVISIBILITY)) {
            display.println(this + " is INVISIBLE!");
            sneakyBerryDuration--;
            if (sneakyBerryDuration == 0) {
                removeCapability(Status.INVISIBILITY);
            }
        }

        if (hasCapability(Status.ATTACKED_BY_CURSE)) {
            cursedDuration = 3;
            removeCapability(Status.ATTACKED_BY_CURSE);
        }

        if (hasCapability(Status.CURSED)) {
            hurt(10);
            if (!isConscious()) {
                map.removeActor(this);
            }
            cursedDuration--;
            if (cursedDuration == 0) {
                removeCapability(Status.CURSED);
            }
        }

        display.println("Player " + printHp() + " at (" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ")");
        display.println("Wallet balance: $" + wallet);

        // return/print the console menu
        return menu.showMenu(this, actions, display);
    }

    @Override
    public char getDisplayChar() {
        return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()) : super.getDisplayChar();
    }

    /**
     * Deduct the price of the items to be bought from the wallet
     *
     * @param price the price of the items to be bought
     */
    public void walletDeduct(int price) {
        wallet -= price;
    }

    /**
     * Increase the wallet balance when coins are collected
     *
     * @param price the price of the coins that has been collected
     */
    public void walletIncrease(int price) {
        wallet += price;
    }

    /**
     * Retrieve the current balance of the wallet
     *
     * @return Integer containing the current balance of the wallet
     */
    @Override
    public int getBalance() {
        return wallet;
    }

    /**
     * Do some damage to the player.
     *
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        if (hasCapability(Status.TALL)) {
            removeCapability(Status.TALL);
        }
    }

    /**
     * Reset the player status, Heal player to maximum hp, remove reset command in the console anymore once it is executed.
     */
    @Override
    public void resetInstance() {
        removeCapability(Status.TALL);
        removeCapability(Status.INVINCIBLE);
        heal(getMaxHp());
        removeCapability(Status.CAN_RESET);
    }

    /**
     * Add an item to player's inventory.
     *
     * @param item The Item to add.
     */
    @Override
    public void addItemToInventory(Item item) {
        super.addItemToInventory(item);
        if (item.hasCapability(Status.IS_A_WRENCH)) {
            addCapability(Status.HAS_WRENCH);
        }
        if (item.hasCapability(Status.IS_A_KEY)) {
            addCapability(Status.HAS_KEY);
        }
    }

    /**
     * Remove an item to player's inventory.
     *
     * @param item The Item to remove.
     */
    @Override
    public void removeItemFromInventory(Item item) {
        super.removeItemFromInventory(item);
        if (item.hasCapability(Status.IS_A_WRENCH)) {
            removeCapability(Status.HAS_WRENCH);
        }
    }

    /**
     * Add water into the player's bottle
     *
     * @param water the healing water from fountain
     */
    @Override
    public void addWater(Water water) {
        bottle.addWater(water);
    }

    /**
     * Retrieve the bottle of the player
     *
     * @return Bottle of the player
     */
    @Override
    public Bottle getBottle() {
        return bottle;
    }

    /**
     * Add a new bottle to the player
     */
    @Override
    public void addBottle(Bottle newBottle) {
        addItemToInventory(newBottle);
        bottle = newBottle;
    }

}

