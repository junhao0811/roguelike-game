package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a teleportation circle that allows teleportation to multiple destinations
 *
 * @author Tan Choong Sheng
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
     * @param destination     the location to teleport to
     * @param destinationName the name of the destination
     */
    public void addDestination(Location destination, String destinationName) {
        destinations.add(destination);
        destinationNames.add(destinationName);
    }
}
