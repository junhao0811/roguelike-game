package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actors.npcs.NPC;
import game.capabilities.Sellable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Merchant extends NPC {

    private final List<Sellable> sellables = new ArrayList<>();

    public Merchant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public abstract void setupSellables();

    public void addSellable(Sellable sellable) {
        sellables.add(sellable);
    }

    public void removeSellable(Sellable sellable) {
        sellables.remove(sellable);
    }

    public List<Sellable> getSellables() {
        return Collections.unmodifiableList(sellables);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        for (Sellable sellable : sellables) {
            actions.add(new PurchaseAction(this, direction, sellable));
        }

        return actions;
    }
}
