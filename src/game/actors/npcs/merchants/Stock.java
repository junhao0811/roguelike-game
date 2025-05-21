package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Sellable;
import game.effects.Effect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock {

    private final Sellable sellable;
    private final int price;
    private final List<Effect> effects = new ArrayList<>();

    public Stock(Sellable sellable, int price) {
        this.sellable = sellable;
        this.price = price;
        this.addEffect(sellable.getEffects());
    }

    public String sell(Actor actor, GameMap map, Merchant merchant) {

        if (actor.getBalance() >= price) {
            StringBuilder result = new StringBuilder(" successfully purchases the " + this + " from " + merchant);

            actor.deductBalance(price);
            merchant.addBalance(price);
            result.append("\n").append(actor).append(" pays ").append(price).append(" Runes to ").
                    append(merchant);

            actor.addItemToInventory((Item) sellable);
            result.append("\n").append(this).append(" is added to the inventory of ").append(actor);

            for (Effect effect : effects) {
                result.append("\n").append(effect.applyEffect(actor, map));

                if (!actor.isConscious()) {
                    actor.unconscious(map);
                    return result + "\n" + actor + " met their demise at the hands of the " + this;
                }
            }

            return result.toString();
        }

        return " does not have enough Runes to purchase the " + this + " from " + merchant;
    }

    public Sellable getSellable() {
        return sellable;
    }

    public int getPrice() {
        return price;
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void addEffect(List<Effect> effects) {
        this.effects.addAll(effects);
    }

    public void removeEffect(Effect effect) {
        effects.remove(effect);
    }

    public List<Effect> getEffects() {
        return Collections.unmodifiableList(effects);
    }

    @Override
    public String toString() {
        return getSellable() + " (" + getPrice() + " Runes)";
    }
}
