package game.grounds.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing a Crop
 */
public abstract class Crop extends Ground {

    private final String name;
    private final int staminaCost;
    private boolean bloomState = false;

    /**
     * Constructor
     */
    public Crop(char displayChar, String name, int staminaCost) {
        super(displayChar, name);
        this.name = name;
        this.staminaCost = staminaCost;
    }

    public String getName() {
        return name;
    }

    public int getStaminaCost() {
        return staminaCost;
    }

    public final void bloom() {
        bloomState = true;
    }

    public abstract void plantingEffect(Actor actor, GameMap map);

    public abstract void cropEffect(Location location);

    @Override
    public void tick(Location location) {
        if (bloomState) {
            this.cropEffect(location);
        }
    }
}
