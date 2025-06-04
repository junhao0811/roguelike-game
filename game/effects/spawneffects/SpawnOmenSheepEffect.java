package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.npcs.OmenSheep;

/**
 * Class representing the spawn Omen Sheep effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public class SpawnOmenSheepEffect extends SpawnEffect {

    /**
     * Constructor
     *
     * @param target the target of the effect
     */
    public SpawnOmenSheepEffect(Actor target) {
        super(new OmenSheep(), target);
    }

    /**
     * Constructor with no target as default
     */
    public SpawnOmenSheepEffect() {
        super(new OmenSheep());
    }
}
