package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.SpiritGoat;

/**
 * Class representing the spawn Spirit Goat effect
 *
 * @author Lee Hao Zhe
 */
public class SpawnSpiritGoatEffect extends SpawnCreatureEffect {

    /**
     * Constructor
     *
     * @param target the target of the effect
     */
    public SpawnSpiritGoatEffect(Actor target) {
        super(target);
    }

    /**
     * Constructor with no target as default
     */
    public SpawnSpiritGoatEffect() {
        super();
    }

    @Override
    public String applyEffect(Actor actor, GameMap map) {
        return super.spawnCreature(actor, map, new SpiritGoat());
    }
}
