package game.crops;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An abstract class representing crops in the game.
 * Crops are special types of ground that can apply periodic effects to their surroundings.
 * Each specific crop type should implement the applyPeriodicEffects method
 * to define its unique behavior.
 *
 * @author Ng Jun Xian
 */
public abstract class Crop extends Ground {

    /**
     * Constructor for Crop.
     *
     * @param displayChar character to display for this type of crop on the map
     * @param name the name of this crop type
     */
    public Crop(char displayChar, String name) {
        super(displayChar, name);
    }

    /**
     * Applies periodic effects specific to each crop type.
     * This method is called once per turn to allow crops to affect their surroundings.
     *
     * @param location the location of this crop
     * @param map the game map containing this crop
     */
    public abstract void applyPeriodicEffects(Location location, GameMap map);

    /**
     * Called once per turn, so that crops can experience the passage of time.
     * Calls applyPeriodicEffects to execute the specific behavior of each crop type.
     *
     * @param location The location of the crop
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        applyPeriodicEffects(location, location.map());
    }
}
