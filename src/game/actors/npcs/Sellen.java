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
import game.capabilities.speakable.Speakable;
import game.effects.attributeeffects.RestoreAttributeEffect;
import game.effects.attributeeffects.IncreaseMaxAttributeEffect;
import game.effects.spawneffects.SpawnGoldenBeetleEffect;
import game.effects.spawneffects.SpawnOmenSheepEffect;
import game.enums.Ability;
import game.weapons.Broadsword;
import game.weapons.DragonslayerGreatsword;
import game.weapons.Katana;

/**
 * Class representing the NPC Sellen
 *
 * @author Abaan Anwar and Lee Hao Zhe
 */
public class Sellen extends NPC implements Speakable, Merchant {

    /**
     * Constructor
     */
    public Sellen() {
        super("Sellen", 's', 150);
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

        monologues.add("The academy casts out those it fears. Yet knowledge, " +
                "like the stars, cannot be bound forever.");

        monologues.add("You sense it too, don’t you? The Glintstone hums, even now.");

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
