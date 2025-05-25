package game.capabilities.speakable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conditions.Condition;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing a MonologueList used to store possible monologue options
 * and returning a random monologue from the options
 *
 * @author Abaan Anwar and Lee Hao Zhe
 */
public class MonologueList {

    private final Actor actor;
    private final GameMap map;
    private final ArrayList<String> monologues = new ArrayList<>();
    private final Random random = new Random();

    /**
     * Constructor
     *
     * @param actor The actor performing the monologue
     * @param map The map the actor is on
     */
    public MonologueList(Actor actor, GameMap map) {
        this.actor = actor;
        this.map = map;
    }

    /**
     * Method to get a random monologue from the list of possible monologues
     */
    public String getMonologue() {
        if (!monologues.isEmpty()) {
            return monologues.get(random.nextInt(monologues.size()));
        } else {
            return null;
        }
    }

    /**
     * Method to add a monologue with a condition, the monologue is only
     * added once it passes the condition
     */
    public void add(String monologue, Condition condition) {
        if (condition.check(actor, map)) {
            add(monologue);
        }
    }

    /**
     * Method to add a monologue without any conditions, the monologue is only
     * added if it is not null
     */
    public boolean add(String monologue) {
        if (monologue != null) {
            monologues.add(monologue);
        }
        return true;
    }
}
