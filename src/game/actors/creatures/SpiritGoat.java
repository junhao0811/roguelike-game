package game.actors.creatures;

import edu.monash.fit2099.engine.actors.Behaviour;
import game.behaviours.WanderBehaviour;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing a Spirit Goat. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class SpiritGoat extends Creature {
    
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    
    /**
     * Constructor.
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.behaviours.put(0, new WanderBehaviour()); // Highest priority
    }
}
