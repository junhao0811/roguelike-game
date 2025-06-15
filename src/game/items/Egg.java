package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actors.creatures.Creature;
import game.capabilities.Consumable;
import game.conditions.Condition;
import game.effects.Effect;
import game.effects.EffectList;
import game.enums.Ability;
import game.capabilities.Hatchable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class representing an Egg that an actor can pick up and drop.
 * The egg has the ability to hatch into a creature.
 *
 * @author Chong Jun Hao and Ng Jun Xian
 * @version 22.0.1
 */
public class Egg<C extends Creature & Hatchable> extends Item implements Consumable {
    
    private final C creature;
    private final Condition condition;
    private final EffectList effects = new EffectList();
    
    /**
     * Constructor
     */
    public Egg(C creature, Condition condition, String prefix) {
        super(prefix + " Egg", '0', true);
        this.creature = creature;
        this.condition = condition;
    }
    
    /**
     * Constructor with no prefix as default
     */
    public Egg(C creature, Condition condition) {
        super("Egg", '0', true);
        this.creature = creature;
        this.condition = condition;
    }
    
    /**
     * Updates the state of the Egg during each game tick by checking its hatching condition
     * and handling the hatching process if it is ready to hatch.
     *
     * @param location the location of the Egg in the game at the time of the tick
     */
    @Override
    public void tick(Location location) {
        Display display = new Display();
        Random random = new Random();
        
        if (condition.check(creature, location)) {
            Creature child = creature.hatch();
            
            if (!location.canActorEnter(child)) {
                List<Location> destinations = new ArrayList<>();
                
                for (Exit exit : location.getExits()) {
                    Location destination = exit.getDestination();
                    
                    if (destination.canActorEnter(child)) {
                        destinations.add(destination);
                    }
                }
                
                if (destinations.isEmpty()) {
                    display.println(this + " is ready to hatch but is unable to as there is no space");
                    return;
                }
                
                destinations.get(random.nextInt(destinations.size())).addActor(child);
                
            } else {
                location.addActor(child);
            }
            
            location.removeItem(this);
            display.println("A new " + child + " hatches from the " + this + "!");
        }
    }
    
    /**
     * Allows the provided actor to consume the Egg and apply its specific effects.
     * The Egg is removed from the actor's inventory upon consumption.
     *
     * @param actor the actor attempting to eat the Egg
     * @param map the current game map
     */
    public String consume(Actor actor, GameMap map) {
        StringBuilder result = new StringBuilder();
        actor.removeItemFromInventory(this);
        
        for (Effect effect : effects) {
            result.append("\n").append(effect.applyEffect(actor, map));
            
            if (!actor.isConscious()) {
                actor.unconscious(map);
                return result + "\n" + actor + " met their demise at the hands of the " + this;
            }
        }
        
        return result.toString();
    }
    
    /**
     * Appends the contents of another Effects list to the EffectList
     * tied to consuming the Egg
     *
     * @param effects the Effects to append
     */
    public void addEffect(EffectList effects) {
        this.effects.add(effects);
    }
    
    /**
     * Appends the contents of any List&lt;Effect&gt; to the EffectList
     * tied to the stock
     *
     * @param effects the List&lt;Effect&gt; to append
     */
    public void addEffect(List<Effect> effects) {
        this.effects.add(effects);
    }
    
    /**
     * Appends a single Effect to the EffectList tied to the stock
     *
     * @param effect the Effect to append
     */
    public void addEffect(Effect effect) {
        this.effects.add(effect);
    }
    
    /**
     * List of allowable actions that the egg can perform to its owner.
     *
     * @param owner the actor that owns the item
     * @param map   the map where the actor is performing the action on
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        
        if (owner.hasCapability(Ability.CONSUME)) {
            actions.add(new ConsumeAction(this));
        }
        
        return actions;
    }
}
