package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.grounds.crops.Crop;
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
     * Returns the stamina cost required to plant this seed.
     *
     * @return the stamina cost (25 points)
     */
    @Override
    public int getStaminaCost() {
        return 25; // Inheritree costs 25 stamina to plant
    }

    /**
     * Creates and returns a new Inheritree crop.
     *
     * @return a new instance of Inheritree crop
     */
    @Override
    public Crop createCrop() {
        return new Inheritree();
    }

    /**
     * Returns the string representation of this seed.
     *
     * @return the name of this seed ("Inheritree Seed")
     */
    @Override
    public String toString() {
        return "Inheritree Seed";
    }

    @Override
    public void sprout(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.getGround().hasCapability(Status.CURSED)) {
                nearby.setGround(new Soil());
            }
        }
    }
}
