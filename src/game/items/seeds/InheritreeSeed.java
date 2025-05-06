package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.SproutEffect;
import game.grounds.crops.Inheritree;
import game.grounds.Soil;

/**
 * A class representing the "Inheritree" seed in the valley.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class InheritreeSeed extends Seed implements SproutEffect {
    /**
     * Constructor.
     */
    public InheritreeSeed() {
        super("Inheritree Seed");
    }
    
    /**
     * After planting the InheritreeSeed on a soil, the ground should be changed to a Inheritree (t) instead of soil (.) at the Farmer's particular location on the game map, and it will convert the surrounding eight tiles from blights (x) to soil (.), and this only happens once.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     */
    @Override
    public void plant(Actor actor, GameMap map) {
        super.plant(actor, map);
        for (StatusEffect statusEffect : actor.getStatusEffects()) {
            if (statusEffect.toString().contains("Crimson Rot")) {
                actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 25);
            }
        }
        map.locationOf(actor).setGround(new Inheritree());
    }
    
    @Override
    public void sprout(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location nearby = exit.getDestination();
            // change this logic to adhere to SOLID principles
            if (nearby.getGround().getDisplayChar() == 'x') {
                nearby.setGround(new Soil());
            }
        }
    }
}
