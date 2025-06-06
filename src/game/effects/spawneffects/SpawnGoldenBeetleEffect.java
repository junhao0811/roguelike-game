package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.GoldenBeetle;

/**
 * Class representing the spawn Golden Beetle effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public class SpawnGoldenBeetleEffect extends SpawnEffect {

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
        return super.spawnNPC(actor, map, new GoldenBeetle());
    }
}
