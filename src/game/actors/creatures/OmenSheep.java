package game.actors.creatures;

import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.WanderBehaviour;
import game.grounds.Soil;
import game.grounds.crops.Inheritree;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing an Omen Sheep. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class OmenSheep extends Creature {
    
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    
    /**
     * Constructor.
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 75);
        this.behaviours.put(0, new WanderBehaviour()); // Highest priority
    }
    
    public void cure(GameMap map) {
        for (Exit exit : map.locationOf(this).getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.canActorEnter(this)) {
                nearby.setGround(new Inheritree());
            }
        }
    }
}

