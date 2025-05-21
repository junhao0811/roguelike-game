package game.capabilities.speakable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conditions.Condition;
import game.conditions.ConditionList;

import java.util.ArrayList;
import java.util.List;
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

    public void add(String monologue, ConditionList conditions) {
        for (Condition condition : conditions) {
            add(monologue, condition);
        }
    }

    public void add(String monologue, List<Condition> conditions) {
        for (Condition condition : conditions) {
            add(monologue, condition);
        }
    }

    public void add(String monologue, Condition condition) {
        if (condition.check(actor, map)) {
            add(monologue);
        }
    }

    public boolean add(String monologue) {
        if (monologue != null) {
            monologues.add(monologue);
        }
        return true;
    }
}
