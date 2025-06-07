package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.effects.Effect;
import game.actors.creatures.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing a spawn effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public abstract class SpawnEffect extends Effect {

    /**
     * Constructor
     *
     * @param target the target of the effect
     */
    public SpawnEffect(Actor target) {
        super(target);
    }

    /**
     * Constructor with no target as default
     *
     */
    public SpawnEffect() {
        super();
    }

    protected abstract Creature spawnCreature();

    @Override
    public String applyEffect(Actor actor, GameMap map) {
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
            Creature creature = spawnCreature();
            destinations.get(random.nextInt(destinations.size())).addActor(creature);
            return creature + " is spawned within the surroundings of " + target;
        }

        return "There are no valid locations surrounding " + target + " for spawning";
    }
}
