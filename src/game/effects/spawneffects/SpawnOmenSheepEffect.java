package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.OmenSheep;
import game.enums.BehaviourSelection;

/**
 * Class representing the spawn Omen Sheep effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public class SpawnOmenSheepEffect extends SpawnCreatureEffect {

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
        return super.spawnCreature(actor, map, new OmenSheep(BehaviourSelection.DEFAULT));
    }
}
