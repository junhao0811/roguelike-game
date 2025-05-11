package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Ability;
import game.Status;

/**
 * A class representing the soil in the valley
 * @author Adrian Kristanto, modified by Chong Jun Hao
 */
public class Soil extends Ground {
    public Soil() {
        super('.', "Soil");
        this.addCapability(Status.PLANTABLE_GROUND);
    }
}
