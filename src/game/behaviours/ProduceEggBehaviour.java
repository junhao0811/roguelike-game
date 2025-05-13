package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;

import game.capabilities.Reproducible;

public class ProduceEggBehaviour extends Action implements Behaviour {
    private Reproducible reproducible;
    private int counter;

    /**
     * Constructor to create a ProduceEggBehaviour.
     *
     * @param reproducible the Reproducible object that will reproduce
     */
    public ProduceEggBehaviour(Reproducible reproducible) {
        this.reproducible = reproducible;
        this.counter = 0;
    }

    /**
     * Returns a ReproduceAction to reproduce at a random location, if possible.
     * If no reproduction is possible, returns null.
     *
     * @param actor the Actor performing the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no ReproduceAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        counter += 1;
        return counter % 7 == 0 ? new this : null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        for(Exit exit : map.locationOf(actor).getExits()){
            if(!exit.getDestination().containsAnActor()){
                exit.getDestination().addActor();
                return actor + " successfully clones itself";
            }
        };
        return actor + " is unable to clone";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
