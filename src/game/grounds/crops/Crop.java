package game.grounds.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;

/**
 * A class representing a Crop
 *
 * @author Lee Hao Zhe
 */
public abstract class Crop extends Ground {

    private final String name;
    private final int staminaCost;
    private boolean bloomState = false;

    /**
     * Constructor
     */
    protected Crop(char displayChar, String name, int staminaCost) {
        super(displayChar, name);
        this.name = name;
        this.staminaCost = staminaCost;
        this.addCapability(Ability.CAN_BE_WATERED);
    }

    /**
     * Getter method for name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for stamina cost
     */
    public int getStaminaCost() {
        return staminaCost;
    }

    /**
     * Method to set the bloom state of the Crop to true (blooms the crop)
     */
    public final void bloom() {
        bloomState = true;
    }

    /**
     * Method to house the logic for effects that occur when first planting the crop's seed
     * (only occurs once)
     */
    public abstract String plantingEffect(Actor actor, GameMap map);

    /**
     * Method to house the logic for effects that occur every tick once the crop has bloomed
     */
    public abstract void cropEffect(Location location);

    @Override
    public void tick(Location location) {
        if (bloomState) {
            this.cropEffect(location);
        }
    }
}
