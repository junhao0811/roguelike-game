package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.grounds.crops.Inheritree;
import game.grounds.Soil;

/**
 * A class representing the "Inheritree" seed in the valley.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class InheritreeSeed extends Seed {
    /**
     * Constructor.
     */
    public InheritreeSeed() {
        super("Inheritree Seed");
    }
    
    /**
     * After planting the InheritreeSeed on a soil, the ground should be changed to a Inheritree (t) instead of soil (.) at the Farmer's particular location on the game map, and it will cure all cursed grounds in the surrounding locations.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     */
    @Override
    public void plant(Actor actor, GameMap map) {
        super.plant(actor, map);
        for (StatusEffect statusEffect : actor.getStatusEffects()) {
            if (statusEffect.toString().contains("Crimson Rot")) {
                if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
                    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 25);
                }
            }
        }
        map.locationOf(actor).setGround(new Inheritree());
    }
    
    @Override
    public void sprout(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.getGround().hasCapability(Ability.RECEIVE_CURE)) {
                nearby.setGround(new Soil());
            }
        }
    }
}
