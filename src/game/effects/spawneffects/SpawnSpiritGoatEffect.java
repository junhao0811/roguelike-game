package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.npcs.SpiritGoat;

public class SpawnSpiritGoatEffect extends SpawnEffect {

    public SpawnSpiritGoatEffect(Actor target) {
        super(new SpiritGoat(), target);
    }

    public SpawnSpiritGoatEffect() {
        super(new SpiritGoat());
    }
}
