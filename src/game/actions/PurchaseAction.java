package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.merchants.Merchant;
import game.capabilities.Sellable;

public class PurchaseAction extends Action {

    private final Merchant merchant;
    private final String direction;
    private final Sellable sellable;

    public PurchaseAction(Merchant merchant, String direction, Sellable sellable) {
        this.merchant = merchant;
        this.direction = direction;
        this.sellable = sellable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + sellable.sell(actor, map, merchant);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + sellable + " from " + merchant + " at " + direction;
    }
}
