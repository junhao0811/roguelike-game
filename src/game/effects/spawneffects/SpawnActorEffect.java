package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.effects.Effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing a spawn effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public abstract class SpawnActorEffect extends Effect {

    /**
     * Constructor
     *
     * @param target the target of the effect
     */
    protected SpawnActorEffect(Actor target) {
        super(target);
    }

    /**
     * Constructor with no target as default
     */
    protected SpawnActorEffect() {
        super();
    }

    /**
     * Method to spawn an NPC
     *
     * @param actor The actor the effect is tied to.
     * @param map The map the actor is on.
     * @param spawnedActor The actor to spawn.
     * @return a description of what happened
     */
    public String spawn(Actor actor, GameMap map, Actor spawnedActor) {
        List<Location> destinations = new ArrayList<>();
        Random random = new Random();
        Actor target = this.getTarget(actor);

        for (Exit exit : map.locationOf(target).getExits()) {
            Location destination = exit.getDestination();

            if (destination.canActorEnter(target)) {
                destinations.add(destination);
            }
        }

        if (!destinations.isEmpty()) {
            destinations.get(random.nextInt(destinations.size())).addActor(spawnedActor);
            return spawnedActor + " is spawned within the surroundings of " + target;
        }

        return "There are no valid locations surrounding " + target + " for " +
                spawnedActor + " to spawn";
    }
}
