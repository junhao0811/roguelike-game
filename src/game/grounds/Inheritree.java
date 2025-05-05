package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import game.actions.PlantAction;

/**
 * A class representing the "Inheritree" crop in the valley
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class Inheritree extends Ground {
    
    /**
     * Constructor
     */
    public Inheritree() {
        super('t', "Inheritree");
    }
}
