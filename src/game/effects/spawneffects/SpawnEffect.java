package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.GoldenBeetle;
import game.actors.npcs.OmenSheep;
import game.actors.npcs.SpiritGoat;
import game.effects.Effect;
import game.enums.Creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnEffect extends Effect {

    private final Creatures creature;

    public SpawnEffect(Creatures creature) {
        super();
        this.creature = creature;
    }

    public SpawnEffect(Creatures creature, Actor target) {
        super(target);
        this.creature = creature;
    }

    @Override
    public String applyEffect(Actor actor, GameMap map) {
        Actor target = this.getTarget(actor);
        Actor spawnedCreature = null;

        if (creature == Creatures.OMEN_SHEEP) {
            spawnedCreature = new OmenSheep();
        } else if (creature == Creatures.SPIRIT_GOAT) {
            spawnedCreature = new SpiritGoat();
        } else if (creature == Creatures.GOLDEN_BEETLE) {
            spawnedCreature = new GoldenBeetle();
        }

        List<Location> destinations = new ArrayList<>();
        Random random = new Random();

        for (Exit exit : map.locationOf(target).getExits()) {
            Location destination = exit.getDestination();

            if (destination.canActorEnter(target)) {
                destinations.add(destination);
            }
        }

        if (!destinations.isEmpty()) {
            destinations.get(random.nextInt(destinations.size())).addActor(spawnedCreature);
            return spawnedCreature + " is spawned within the surroundings of " + target;
        }

        return "There are no valid locations surrounding " + target + " for " +
                spawnedCreature + " to spawn";
    }
}
