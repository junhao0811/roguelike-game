package game.capabilities.merchant;

/**
 * Interface representing a Merchant
 *
 * @author Tan Choong Sheng
 */
public interface Merchant {

    /**
     * Method to house the logic to set up the Merchant's stock
     */
    StockList getStocks();
}
