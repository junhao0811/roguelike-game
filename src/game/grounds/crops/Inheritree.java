package game.grounds.crops;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Soil;

/**
 * A class representing the "Inheritree" crop in the valley
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class Inheritree extends Crop {
    
    /**
     * Constructor
     */
    public Inheritree() {
        super('t', "Inheritree");
    }
    
    public void cureSurrounding(Location location) { // SRP
        for (Exit exit : location.getExits()) {
            Location tile = exit.getDestination();
            if (tile.getGround().getDisplayChar() == 'x') {
                tile.setGround(new Soil());
            }
        }
    }
}
