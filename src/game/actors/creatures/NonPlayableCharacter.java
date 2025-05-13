package game.actors.creatures;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.Status;

/**
 * Class representing a Creature. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public abstract class NonPlayableCharacter extends Actor {

    /**
     * Constructor.
     */
    public NonPlayableCharacter(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
    
    /**
     * The creature can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions that can be performed by the other actor
     * @see edu.monash.fit2099.demo.huntsman.HuntsmanSpider#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}