package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actions.SpeakAction;
import game.capabilities.merchant.Merchant;
import game.capabilities.merchant.Stock;
import game.capabilities.merchant.StockList;
import game.capabilities.speakable.MonologueList;
import game.behaviours.WanderBehaviour;
import game.effects.attributeeffects.RestoreAttributeEffect;
import game.effects.attributeeffects.IncreaseMaxAttributeEffect;
import game.effects.spawneffects.SpawnGoldenBeetleEffect;
import game.effects.spawneffects.SpawnOmenSheepEffect;
import game.enums.Ability;
import game.items.consumables.HealingPotion;
import game.weapons.Broadsword;
import game.weapons.DragonslayerGreatsword;
import game.weapons.Katana;
import game.enums.Status;

/**
 * Class representing the NPC Sellen
 *
 * @author Abaan Anwar and Lee Hao Zhe, modified by Chong Jun Hao
 */
public class Sellen extends NPC implements Merchant {

    /**
     * Constructor
     */
    public Sellen() {
        super("Sellen", 's', 150, 0);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
    }

    @Override
    public StockList getStocks() {
        StockList stocks = new StockList();

        Stock<?> broadsword = new Stock<>(new Broadsword(), 100);
        broadsword.addEffect(new IncreaseMaxAttributeEffect(BaseActorAttributes.HEALTH, 20));

        Stock<?> dragonslayerGreatsword = new Stock<>(new DragonslayerGreatsword(), 1500);
        dragonslayerGreatsword.addEffect(new SpawnGoldenBeetleEffect());

        Stock<?> katana = new Stock<>(new Katana(), 500);
        katana.addEffect(new SpawnOmenSheepEffect(this));
        katana.addEffect(new RestoreAttributeEffect(BaseActorAttributes.HEALTH, 10));
        katana.addEffect(new IncreaseMaxAttributeEffect(BaseActorAttributes.STAMINA, 20));

        stocks.add(broadsword);
        stocks.add(dragonslayerGreatsword);
        stocks.add(katana);

        return stocks;
    }
    
    @Override
    public MonologueList getMonologues(Actor actor, GameMap map) {
        MonologueList monologues = new MonologueList(actor, map);
        
        // Different monologues based on affection level
        if (getAffectionLevel() >= 50 && !this.hasCapability(Status.HEAL_POTION_GIVEN)) {
            HealingPotion healPotion = new HealingPotion();
            actor.addItemToInventory(healPotion);
            this.addCapability(Status.HEAL_POTION_GIVEN);
            
            monologues.add("My dear student, you have proven yourself worthy. Please accept this healing draught as a token of my appreciation!");
            monologues.add("This potion contains the essence of ancient knowledge - use it wisely.");
            return monologues;
            
        } else if (getAffectionLevel() >= 20) {
            // Friendly affection - encouraging scholarly dialogue
            monologues.add("Your thirst for knowledge reminds me of my younger days at the academy. Most refreshing.");
            monologues.add("I see potential in you that others overlook. True wisdom recognizes true wisdom.");
            monologues.add("The academy's rigid thinking has blinded them to possibilities. You see beyond their limitations.");
            monologues.add("Perhaps one day you too will challenge the established order as I have done.");
            monologues.add("Knowledge shared is knowledge multiplied. I am pleased to guide a willing mind.");
            
        } else if (getAffectionLevel() > 0) {
            // Low positive affection - cautiously optimistic
            monologues.add("You show more sense than most who wander these lands. A curious mind is a valuable thing.");
            monologues.add("The pursuit of knowledge requires courage. I sense you possess this quality.");
            monologues.add("Perhaps there is hope yet for those who seek truth beyond convention.");
            monologues.add("The Glintstone responds to sincere seekers. Your presence does not disturb its harmony.");
        } else if (getAffectionLevel() == 0) {
            // Neutral affection - original scholarly dialogue
            monologues.add("The academy casts out those it fears. Yet knowledge, " +
                "like the stars, cannot be bound forever.");
            monologues.add("You sense it too, don't you? The Glintstone hums, even now.");
            monologues.add("Forbidden knowledge is often the most valuable. The academy's loss is my gain.");
            monologues.add("Magic flows through all things, yet so few truly understand its nature.");
            
        } else if (getAffectionLevel() >= -20) {
            // Slightly negative affection - dismissive but professional
            monologues.add("Another seeker who lacks the dedication for true understanding, I suppose.");
            monologues.add("The mysteries of the cosmos are not for the faint of heart or weak of mind.");
            monologues.add("Perhaps you should return to more... conventional pursuits.");
            monologues.add("The Glintstone's wisdom is earned, not given freely to the unworthy.");
            monologues.add("I have little time for those who treat knowledge as mere commodity.");
            
        } else {
            // Very negative affection - cold and hostile
            monologues.add("Your ignorance is as profound as it is disappointing. Begone from my sight.");
            monologues.add("The academy's teachings have poisoned your mind beyond redemption.");
            monologues.add("You represent everything wrong with the current generation of scholars.");
            monologues.add("Do not insult my intelligence with your presence. True knowledge rejects the unworthy.");
            monologues.add("The stars themselves dim in the presence of such willful ignorance.");
            monologues.add("Leave me to my studies. Your very existence disturbs the natural order.");
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
