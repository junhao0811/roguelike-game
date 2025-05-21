package game.capabilities.speakable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conditions.Condition;

import java.util.ArrayList;
import java.util.Random;

public class MonologueList {

    private final Actor actor;
    private final GameMap map;
    private final ArrayList<String> monologues = new ArrayList<>();
    private final Random random = new Random();

    public MonologueList(Actor actor, GameMap map) {
        this.actor = actor;
        this.map = map;
    }

    public String getMonologue() {
        if (!monologues.isEmpty()) {
            return monologues.get(random.nextInt(monologues.size()));
        } else {
            return null;
        }
    }

    public void add(String monologue) {
        monologues.add(monologue);
    }

    public void add(String monologue, Condition condition) {
        if (condition.check(actor, map)) {
            monologues.add(monologue);
        }
    }
}
