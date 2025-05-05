package game.grounds.crops;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.BloomEffect;

public abstract class Crop extends Ground implements BloomEffect {
    
    private int counter;
    
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Crop(char displayChar, String name) {
        super(displayChar, name);
        counter = 0;
    }
    
    @Override
    public void bloom(Location location) {}
    
    @Override
    public void tick(Location location) {
        counter += 1;
    }
}
