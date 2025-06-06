package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
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
        super(target);
    }

    /**
     * Constructor with no target as default
     */
    public SpawnOmenSheepEffect() {
        super();
    }

    @Override
    public String applyEffect(Actor actor, GameMap map) {
        return super.spawnNPC(actor, map, new OmenSheep());
    }
}
