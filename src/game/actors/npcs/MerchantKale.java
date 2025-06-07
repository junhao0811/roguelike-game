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
import game.weapons.DemonKingsDaggers;
import game.weapons.DragonslayerGreatsword;

/**
 * Class representing the NPC Merchant Kale
 *
 * @author Abaan Anwar and Lee Hao Zhe, modified by Chong Jun Hao
 */
public class MerchantKale extends NPC implements Speakable, Merchant {

    /**
     * Constructor
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 200, 0);
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
        
        // Different monologues based on affection level
        if (getAffectionLevel() >= 50 && !this.hasCapability(Status.SECRET_WEAPON_GIVEN)) {
            // Give secret weapon
            DemonKingsDaggers secretWeapon = new DemonKingsDaggers();
            actor.addItemToInventory(secretWeapon);
            this.addCapability(Status.SECRET_WEAPON_GIVEN);
            
            monologues.add("Ah, my most valued customer! I've been saving this legendary weapon for someone truly worthy.");
            monologues.add("These are the Demon King's Daggers - a weapon of immense power. Please, accept this as a gift for your loyalty!");
            return monologues;
            
        } else if (getAffectionLevel() >= 20) {
            // Friendly affection - discounts and preferential treatment
            monologues.add("Welcome back, friend! I always have good deals for valued customers.");
            monologues.add("For you, I can offer a special discount on my finest weapons.");
            monologues.add("Your continued patronage keeps my business alive. Thank you!");
            monologues.add("I've set aside some quality goods just for trusted customers like you.");
            
        } else if (getAffectionLevel() > 0) {
            // Low positive affection - slightly better treatment
            monologues.add("Good to see a returning customer. Perhaps we can make a deal.");
            monologues.add("You've been fair in our dealings. I appreciate honest customers.");
            monologues.add("Business has been slow, but customers like you give me hope.");
        } else if (getAffectionLevel() == 0) {
            // Neutral affection - standard merchant dialogue
            monologues.add("Ah, hard times, I see. Keep your head low and your blade sharp.",
                new RunesComparisonCondition(ComparisonOperations.LT, 50));
            monologues.add("Not a scrap to your name? Even a farmer should carry a " +
                "trinket or two.", new EmptyInventoryCondition());
            monologues.add("Rest by the flame when you can, friend. These lands " +
                "will wear you thin.", new NearbyCondition(Status.CURSED));
            monologues.add("A merchant's life is a lonely one. But the roads... they whisper " +
                "secrets to those who listen.");
            
        } else if (getAffectionLevel() >= -20) {
            // Slightly negative affection - less friendly but still professional
            monologues.add("Business is business, I suppose. What do you need?");
            monologues.add("My prices are fair, even if some customers think otherwise.");
            monologues.add("I run an honest establishment here. Take it or leave it.");
            monologues.add("Times are tough for everyone, including merchants.");
            
        } else {
            // Very negative affection - hostile and unwelcoming
            monologues.add("You again? My prices have gone up since last time.");
            monologues.add("I don't appreciate customers who waste my time.");
            monologues.add("Buy something or move along. I have paying customers to serve.");
            monologues.add("Your kind of business I can do without.");
            monologues.add("Maybe you should try the other merchants... if you can find any.");
        }
        
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
