package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.speakable.Speakable;

/**
 * Class representing an action to speak
 *
 * @author Abaan Anwar
 */
public class SpeakAction extends Action {

    private final Speakable target;
    private final String direction;

    /**
     * Constructor
     *
     * @param target the Speakable to speak to
     * @param direction the direction where the speaking should be performed
     *                  (only used for display purposes)
     */
    public SpeakAction(Speakable target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor) + "\n" + target.speak(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to " + target + " at " + direction;
    }
}
