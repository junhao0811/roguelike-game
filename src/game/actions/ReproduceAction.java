package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Reproducible;

public class ReproduceAction extends Action {
    private Reproducible reproducible;

    public ReproduceAction(Reproducible reproducible) {
        this.reproducible = reproducible;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        reproducible.reproduce(actor, map);
        return actor + " reproduces successfully!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
