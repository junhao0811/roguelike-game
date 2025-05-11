package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.crops.Bloodrose;
import game.grounds.crops.Crop;

/**
 * A class representing the "Bloodrose" seed in the valley.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class BloodroseSeed extends Seed {
    
    /**
     * Constructor.
     */
    public BloodroseSeed() {
        super("Bloodrose Seed");
    }

    /**
     * Returns the stamina cost required to plant this seed.
     *
     * @return the stamina cost (75 points)
     */
    @Override
    public int getStaminaCost() {
        return 75; // Bloodrose costs 75 stamina to plant
    }

    /**
     * Creates and returns a new Bloodrose crop.
     *
     * @return a new instance of the Bloodrose crop
     */
    @Override
    public Crop createCrop() {
        return new Bloodrose();
    }

    /**
     * Returns the string representation of this seed.
     *
     * @return the name of this seed ("Bloodrose Seed")
     */
    @Override
    public String toString() {
        return "Bloodrose Seed";
    }
    
    @Override
    public void sprout(Actor actor, GameMap map) {
        actor.hurt(5);
    }
}
