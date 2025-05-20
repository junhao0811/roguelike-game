package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.merchants.Merchant;
import game.capabilities.Sellable;
import game.effects.Effect;
import game.effects.attributeeffects.RestoreAttributeEffect;

import java.util.ArrayList;
import java.util.List;

public class Broadsword extends WeaponItem implements Sellable {

    private final int price;
    private final List<Effect> effects = new ArrayList<>();

    public Broadsword(int price) {
        super("Broadsword", 'b', 30, "slashes", 50);
        this.price = price;
        effects.add(new RestoreAttributeEffect(BaseActorAttributes.HEALTH, 10));
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    @Override
    public String sell(Actor actor, GameMap map, Merchant merchant) {

        if (actor.getBalance() >= price) {
            StringBuilder result = new StringBuilder(" successfully purchases the " + this + " from " + merchant);

            actor.deductBalance(price);
            merchant.addBalance(price);
            result.append("\n").append(actor).append(" pays ").append(price).append(" Runes to ").
                    append(merchant);

            actor.addItemToInventory(this);
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

    @Override
    public String toString() {
        return super.toString() + " (" + price + " Runes)";
    }
}