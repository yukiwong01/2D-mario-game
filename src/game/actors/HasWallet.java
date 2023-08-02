package game.actors;

/**
 * hasWallet is an interface for actors that have a wallet
 */
public interface HasWallet {
    /**
     * Deduct the price of the items to be bought from the wallet
     *
     * @param price the price of the item to be bought
     */
    void walletDeduct(int price);

    /**
     * Increase the wallet balance when coins are collected
     *
     * @param price the value of the coins that has been collected
     */
    void walletIncrease(int price);

    /**
     * Retrieve the current balance of the wallet
     *
     * @return Integer containing the current balance of the wallet
     */
    int getBalance();

    /**
     * Add an actor that has a wallet to the ArrayList of the WalletManager
     */
    default void addToActors() {
        WalletManager.getInstance().appendActor(this);
    }
}
