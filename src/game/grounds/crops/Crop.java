package game.grounds.crops;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
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
    
    /**
     * This method is called every turn to allow the crop to bloom.
     *
     * @param location the location of the crop
     */
    @Override
    public void tick(Location location) {
        this.bloom(location);
    }
}
