package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.NPC;
import game.effects.Effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class SpawnEffect extends Effect {

    private final NPC creature;

    public SpawnEffect(NPC creature, Actor target) {
        super(target);
        this.creature = creature;
    }

    public SpawnEffect(NPC creature) {
        super();
        this.creature = creature;
    }

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
            destinations.get(random.nextInt(destinations.size())).addActor(creature);
            return creature + " is spawned within the surroundings of " + target;
        }

        return "There are no valid locations surrounding " + target + " for " +
                creature + " to spawn";
    }
}
