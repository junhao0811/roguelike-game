package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.merchants.Merchant;
import game.capabilities.Sellable;

public class Katana extends WeaponItem implements Sellable {

    private final int price;

    public Katana(int price) {
        super("Katana", 'j', 50, "hits", 60);
        this.price = price;
    }

    @Override
    public String sell(Actor actor, GameMap map, Merchant merchant) {

        if (actor.getBalance() >= price) {
            String result;

            result = " successfully purchases the " + this + " from " + merchant;

            actor.deductBalance(price);
            merchant.addBalance(price);
            result += "\n" + actor + " pays " + price + " Runes to " + merchant;

            actor.addItemToInventory(this);
            result += "\n" + this + " is added to the inventory of " + actor;

            return result;
        }

        return " does not have enough Runes to purchase the " + this + " from " + merchant;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + price + " Runes)";
    }
}