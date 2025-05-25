package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.capabilities.merchant.Merchant;
import game.capabilities.merchant.Stock;
import game.capabilities.merchant.StockList;
import game.enums.Ability;
import game.enums.Status;
import game.actions.SpeakAction;
import game.capabilities.speakable.MonologueList;
import game.behaviours.WanderBehaviour;
import game.capabilities.speakable.Speakable;
import game.conditions.EmptyInventoryCondition;
import game.conditions.NearbyCondition;
import game.conditions.comparisons.ComparisonOperations;
import game.conditions.comparisons.RunesComparisonCondition;
import game.effects.attributeeffects.IncreaseMaxAttributeEffect;
import game.effects.attributeeffects.RestoreAttributeEffect;
import game.weapons.Broadsword;
import game.weapons.DragonslayerGreatsword;

/**
 * Class representing the NPC Merchant Kale
 *
 * @author Abaan Anwar and Lee Hao Zhe
 */
public class MerchantKale extends NPC implements Speakable, Merchant {

    /**
     * Constructor
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 200);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
    }

    @Override
    public StockList getStocks() {
        StockList stocks = new StockList();

        Stock<?> broadsword = new Stock<>(new Broadsword(), 150);
        broadsword.addEffect(new IncreaseMaxAttributeEffect(BaseActorAttributes.STAMINA, 30));

        Stock<?> dragonslayerGreatsword = new Stock<>(new DragonslayerGreatsword(), 1700);
        dragonslayerGreatsword.addEffect(new RestoreAttributeEffect(BaseActorAttributes.STAMINA, 20));

        stocks.add(broadsword);
        stocks.add(dragonslayerGreatsword);

        return stocks;
    }

    @Override
    public MonologueList getMonologues(Actor actor, GameMap map) {
        MonologueList monologues = new MonologueList(actor, map);

        monologues.add("Ah, hard times, I see. Keep your head low and your blade sharp.",
                new RunesComparisonCondition(ComparisonOperations.LT, 50));

        monologues.add("Not a scrap to your name? Even a farmer should carry a " +
                "trinket or two.", new EmptyInventoryCondition());

        monologues.add("Rest by the flame when you can, friend. These lands " +
                "will wear you thin.", new NearbyCondition(Status.CURSED));

        monologues.add("A merchant’s life is a lonely one. But the roads… they whisper " +
                "secrets to those who listen.");

        return monologues;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Ability.LISTEN)) {
            actions.add(new SpeakAction(this, direction));
        }

        if (otherActor.hasCapability(Ability.PURCHASE)) {
            for (Stock<?> stock : getStocks()) {
                actions.add(new PurchaseAction(this, direction, stock));
            }
        }

        return actions;
    }
}
