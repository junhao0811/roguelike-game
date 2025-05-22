package game.capabilities.merchant;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Sellable;
import game.effects.Effect;
import game.effects.EffectList;

import java.util.List;

/**
 * Class representing a Stock (a good that a Merchant sells)
 */
public class Stock {

    private final Sellable sellable;
    private final int price;
    private final EffectList effects = new EffectList();

    /**
     * Constructor
     *
     * @param sellable the sellable to sell
     * @param price the price of the stock
     */
    public Stock(Sellable sellable, int price) {
        this.sellable = sellable;
        this.price = price;
        this.addEffect(sellable.getEffects());
    }

    /**
     * Sells the stock to the actor that purchases it by deducting the Runes in the
     * actor's wallet by the price and adding it to the merchant's wallet.
     * The sellable tied to the stock is then added to the actor's inventory before
     * applying the effects tied to the stock
     *
     * @param actor the actor purchased the stock
     * @param map the map on which the purchase was executed
     * @param merchant the merchant who sold the stock
     * @return a description of what happened when the stock was purchased
     */
    public String sell(Actor actor, GameMap map, Merchant merchant) {
        Actor seller = (Actor) merchant;

        if (actor.getBalance() >= price) {
            StringBuilder result = new StringBuilder(actor + " successfully purchases the " +
                    this + " from " + seller);

            actor.deductBalance(price);
            seller.addBalance(price);
            result.append("\n").append(actor).append(" pays ").append(price).append(" Runes to ").
                    append(seller);

            actor.addItemToInventory((Item) sellable);
            result.append("\n").append(sellable).append(" is added to the inventory of ").append(actor);

            for (Effect effect : effects) {
                result.append("\n").append(effect.applyEffect(actor, map));

                if (!actor.isConscious()) {
                    actor.unconscious(map);
                    return result + "\n" + actor + " met their demise at the hands of the " + this;
                }
            }

            return result.toString();
        }

        return actor + " does not have enough Runes to purchase the " + this + " from " + seller;
    }

    /**
     * Appends the contents of another Effects list to the Effects list
     * tied to the stock
     *
     * @param effects the Effects to append
     */
    public void addEffect(EffectList effects) {
        this.effects.add(effects);
    }

    /**
     * Appends the contents of any List&lt;Effect&gt; to the Effects list
     * tied to the stock
     *
     * @param effects the List&lt;Effect&gt; to append
     */
    public void addEffect(List<Effect> effects) {
        this.effects.add(effects);
    }

    /**
     * Appends a single Effect to the Effects list tied to the stock
     *
     * @param effect the Effect to append
     */
    public void addEffect(Effect effect) {
        this.effects.add(effect);
    }

    @Override
    public String toString() {
        return sellable + " (" + price + " Runes)";
    }
}
