package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.behaviours.HatchBehaviour;
import java.util.Map;
import java.util.TreeMap;

/**
 * A class representing an Egg that an actor can pick up and drop.
 * The egg has the ability to hatch into a OmenSheep.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class Egg extends Item {
    
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    
    /**
     * Constructor.
     */
    public Egg() {
        super("Egg", '0', true);
        this.behaviours.put(0, new HatchBehaviour(this));
    }
    
    /**
     * Inform a carried Egg of the passage of time.
     *
     * This method is called once per turn, if the Egg is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
    
    }
    
    /**
     * Inform the Egg on the ground of the passage of time.
     * This method is called once per turn, if the egg rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
    
    }
}
