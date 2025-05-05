package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.SproutEffect;
import game.grounds.crops.Bloodrose;

/**
 * A class representing the "Bloodrose" seed in the valley.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class BloodroseSeed extends Seed implements SproutEffect {
    
    /**
     * Constructor.
     */
    public BloodroseSeed() {
        super("Bloodrose Seed");
    }
    
    /**
     * After planting the BloodRoseSeed on a soil, the ground should be changed to a Bloodrose (w)
     * instead of soil (.) at the Farmer's particular location on the game map.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     */
    @Override
    public void plant(Actor actor, GameMap map) {
        super.plant(actor, map);
        this.sprout(actor, map);
        map.locationOf(actor).setGround(new Bloodrose());
    }
    
    @Override
    public void sprout(Actor actor, GameMap map) {
        actor.hurt(5);
    }
}
