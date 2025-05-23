package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.npcs.OmenSheep;

public class SpawnOmenSheepEffect extends SpawnEffect {

    public SpawnOmenSheepEffect(Actor target) {
        super(new OmenSheep(), target);
    }

    public SpawnOmenSheepEffect() {
        super(new OmenSheep());
    }
}
