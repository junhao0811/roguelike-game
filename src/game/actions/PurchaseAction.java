package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.merchant.Merchant;
import game.capabilities.merchant.Stock;

/**
 * Class representing an action to purchase
 */
public class PurchaseAction extends Action {

    private final Merchant merchant;
    private final String direction;
    private final Stock stock;

    /**
     * Constructor
     *
     * @param merchant the merchant selling the stock
     * @param direction the direction where the purchase should be performed
     *                  (only used for display purposes)
     * @param stock the stock to be sold
     */
    public PurchaseAction(Merchant merchant, String direction, Stock stock) {
        this.merchant = merchant;
        this.direction = direction;
        this.stock = stock;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return stock.sell(actor, map, merchant);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + stock + " from " + merchant + " at " + direction;
    }
}
