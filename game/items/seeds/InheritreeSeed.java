package game.items.seeds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.crops.Crop;
import game.crops.Inheritree;
import game.grounds.Soil;
import game.interfaces.effects.GroundCureEffect;
import game.status.Status;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * A class representing an Inheritree seed that can be planted to grow an Inheritree crop.
 * This seed has the ability to cure cursed ground in surrounding locations when planted.
 * It requires less stamina to plant compared to other seeds.
 *
 * @author Ng Jun Xian
 */
public class InheritreeSeed extends Seed implements GroundCureEffect {

    /**
     * Constructor for InheritreeSeed.
     * Initializes the seed with the name "Inheritree Seed".
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

    /**
     * Plants this seed at the specified location if possible.
     * If planting is successful, applies ground cure effect to surrounding tiles.
     *
     * @param actor the actor planting the seed
     * @param location the location to plant the seed
     * @return true if planting was successful, false otherwise
     */
    @Override
    public boolean plant(Actor actor, Location location) {
        if (super.plant(actor, location)) {
            applyGroundCureEffect(location, location.map());
            return true;
        }
        return false;
    }

    /**
     * Applies ground cure effect to all cursed tiles in adjacent locations.
     * Converts cursed ground to soil.
     *
     * @param location the location from which to apply the ground cure effect
     * @param map the game map containing this location
     */
    @Override
    public void applyGroundCureEffect(Location location, GameMap map) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Status.CURSED)) {
                destination.setGround(new Soil());
                System.out.println("Surrounding tiles are converted to soil!");
            }
        }
    }
}