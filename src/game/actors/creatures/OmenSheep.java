package game.actors.creatures;

import edu.monash.fit2099.engine.actors.Actor;
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
        this.behaviours.put(Integer.MAX_VALUE, new WanderBehaviour());
    }
    
    /**
     * Cures the environment by replacing nearby ground with Inheritree instances.
     * This action applies to the locations surrounding the current actor's position
     * in the provided game map.
     *
     * @param actor the actor initiating the cure action
     * @param map the game map where the cure action is performed
     */
    public boolean cure(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(this).getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.canActorEnter(this)) {
                nearby.setGround(new Inheritree());
            }
        }
        System.out.println("Inheritree has been planted around the Omen Sheep!");
        return true;
    }
}

