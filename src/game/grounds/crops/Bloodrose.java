package game.grounds.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing the "Bloodrose" crop in the valley
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class Bloodrose extends Crop {
    
    /**
     * Constructor
     */
    public Bloodrose() {
        super('w', "Bloodrose");
    }
    
    @Override
    public void bloom(Location location) {
        for (Exit exit : location.getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.containsAnActor()) {
                Actor actor = nearby.getActor();
                actor.hurt(10);
            }
        }
    }
}
