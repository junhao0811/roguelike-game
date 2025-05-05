package game.grounds.crops;

import edu.monash.fit2099.engine.positions.Ground;
import game.capabilities.BloomEffect;

public abstract class Crop extends Ground implements BloomEffect {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Crop(char displayChar, String name) {
        super(displayChar, name);
    }
}
