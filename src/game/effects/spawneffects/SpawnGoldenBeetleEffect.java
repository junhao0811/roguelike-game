package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.GoldenBeetle;

/**
 * Class representing the spawn Golden Beetle effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public class SpawnGoldenBeetleEffect extends SpawnCreatureEffect {

    /**
     * Constructor
     *
     * @param target the target of the effect
     */
    public SpawnGoldenBeetleEffect(Actor target) {
        super(target);
    }

    /**
     * Constructor with no target as default
     */
    public SpawnGoldenBeetleEffect() {
        super();
    }

    @Override
    public String applyEffect(Actor actor, GameMap map) {
        return super.spawnCreature(actor, map, new GoldenBeetle());
    }
}
