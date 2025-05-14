package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actors.npc.Npc;
import game.capabilities.Hatchable;

/**
 * A class representing an Egg that an actor can pick up and drop. The egg has the ability to hatch
 * into a creature.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class Egg extends Item {
    
    private Hatchable creature;
    private int counter;
    
    /**
     * Constructor.
     */
    public Egg(Hatchable creature) {
        super("Egg", '0', true);
        this.creature = creature;
        this.counter = 0;
    }
    
    /**
     * Hatching condition for the Egg. The Egg can hatch into other creatures with different
     * hatching conditions
     *
     * @return true if the hatching condition of the Egg is met, false otherwise
     */
    public boolean isReadyToHatch() {
        return counter == 3;
    }
    
    /**
     * Inform the Egg on the ground of the passage of time. This method is called once per turn, if
     * the egg rests upon the ground.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        counter++;
        if (isReadyToHatch()) {
            currentLocation.removeItem(this);
            Npc newCreature = creature.hatch();
            currentLocation.addActor(newCreature);
            new Display().println("A new " + newCreature + " has hatched from the Egg!");
        }
    }
    
    /**
     * List of allowable actions that the egg can perform to its owner.
     *
     * @param owner the actor that owns the item
     * @param map   the map where the actor is performing the action on
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        // Check if the actor whether he has the item which can cure
        if (owner.hasCapability(Ability.EAT)) {
//            actions.add(new EatAction(this));
        }
        return actions;
    }
}
