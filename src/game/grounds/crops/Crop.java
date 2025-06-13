package game.grounds.crops;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.BloomAction;
import game.enums.Ability;

/**
 * A class representing a Crop
 *
 * @author Lee Hao Zhe
 */
public abstract class Crop extends Ground {

    private final int staminaCost;
    private boolean bloomState = false;

    /**
     * Constructor
     */
    protected Crop(char displayChar, String name, int staminaCost) {
        super(displayChar, name);
        this.staminaCost = staminaCost;
        this.addCapability(Ability.CAN_BE_WATERED);
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
    public final String bloom() {
        if (!bloomState) {
            bloomState = true;
            setDisplayChar(Character.toUpperCase(getDisplayChar()));
            return "\nThe " + this + " blooms";
        }

        return "";
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

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (direction.equals("crop")) {
            actions.add(new BloomAction(this));
        }

        return actions;
    }
}
