package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.status.Status;

/**
 * A class representing the soil in the valley
 * @author Adrian Kristanto
 *
 * modified by Ng Jun Xian
 */
public class Soil extends Ground {

    /**
     * Constructor for Blight.
     * Initializes a new Blight with display character 'x', the name "Blight",
     * and marks it as having the PLANTABLE_GROUND status.
     */
    public Soil() {
        super('.', "Soil");
        this.addCapability(Status.PLANTABLE_GROUND); //req 2
    }
}
