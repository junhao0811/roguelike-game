package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actors.npcs.NPC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Merchant extends NPC {

    private final List<Stock> stocks = new ArrayList<>();

    public Merchant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public abstract void setupStocks();

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    public List<Stock> getStocks() {
        return Collections.unmodifiableList(stocks);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        for (Stock stock : stocks) {
            actions.add(new PurchaseAction(this, direction, stock));
        }

        return actions;
    }
}
