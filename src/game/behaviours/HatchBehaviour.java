package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Egg;

/**
 * Class representing a behaviour for creatures to hatch from eggs.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class HatchBehaviour implements Behaviour {
    
    private Egg egg;
    private int counter;
    
    /**
     * Constructor.
     *
     * @param egg the Egg to be hatched
     */
    public HatchBehaviour(Egg egg) {
        this.egg = egg;
        this.counter = 0;
    }
    
    @Override
    public Action getAction(Actor actor, GameMap map) {
        counter += 1;
        return counter % 3 == 0 ? this : null;
    }
}
