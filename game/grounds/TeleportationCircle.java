package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.enums.Ability;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a teleportation circle that allows teleportation to multiple destinations
 *
 * @author GitHub Copilot
 */
public class TeleportationCircle extends Ground {
    
    private List<Location> destinations;
    private List<String> destinationNames;
    
    /**
     * Constructor
     */
    public TeleportationCircle() {
        super('A', "Teleportation Circle");
        this.destinations = new ArrayList<>();
        this.destinationNames = new ArrayList<>();
    }
    
    /**
     * Add a destination to this teleportation circle
     *
     * @param destination the location to teleport to
     * @param destinationName the name of the destination
     */
    public void addDestination(Location destination, String destinationName) {
        destinations.add(destination);
        destinationNames.add(destinationName);
    }
    
    /**
     * Returns a list of allowable actions for the actor at this location
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
    
      
        if (actor.hasCapability(Ability.CAN_TELEPORT)) {
            if (location.containsAnActor() && location.getActor() == actor) {
                for (int i = 0; i < destinations.size(); i++) {
                    actions.add(new TeleportAction(destinations.get(i), destinationNames.get(i)));
                }
            }
        }
        
        return actions;
    }
}