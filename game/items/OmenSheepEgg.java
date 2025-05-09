package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.EatAction;
import game.creatures.OmenSheep;
import game.status.Status;
import game.interfaces.Hatchable;
import game.interfaces.Edible;
import game.actions.HatchAction;

/**
 * An egg produced by the Omen Sheep that can hatch into a new Omen Sheep.
 * The egg hatches after 3 turns on the ground and can be eaten by the Farmer.
 * It will not hatch if it is in a player's inventory.
 *
 * @author Ng Jun Xian
 */
public class OmenSheepEgg extends Item implements Hatchable, Edible {
    
    private int age;
    private static final int HATCHING_AGE = 3;
    private static final int HEALTH_INCREASE = 10;
    
    /**
     * Constructor for OmenSheepEgg.
     */
    public OmenSheepEgg() {
        super("Omen Sheep Egg", '0', true);
        this.age = 0;
        this.addCapability(Status.HATCHABLE);
        this.addCapability(Status.EDIBLE);
    }

    /**
     * Returns a collection of Actions that can be performed with this item when it's in an actor's inventory.
     * Adds an EatAction when the item is in an actor's inventory.
     * 
     * @param owner the actor carrying this item
     * @param map the game map
     * @return a collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        
        // If this item is edible, add an eat action
        if (this.hasCapability(Status.EDIBLE)) {
            actions.add(new EatAction(this, this));
        }
        
        return actions;
    }

    /**
     * Tick method that is called every turn when the egg is on the ground.
     * Increments the age of the egg and checks if it's ready to hatch.
     * The egg will only age and potentially hatch when on the ground (not in inventory).
     *
     * @param currentLocation The location of the egg
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        
        // Only increment age when on the ground
        age++;
        
        // Check if egg is ready to hatch and attempt hatching
        if (age >= HATCHING_AGE) {
            // Create and execute HatchAction
            HatchAction hatchAction = new HatchAction(this, currentLocation);
            hatchAction.execute(null, null);
        }
    }

    /**
     * Alternative tick method that is called every turn when the egg is in an inventory.
     * The egg does not age while in inventory, so it cannot hatch.
     *
     * @param currentLocation The location of the actor carrying this egg
     * @param actor The actor carrying this egg
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        // Don't increment age when in inventory, so the egg won't hatch
    }

    /**
     * Hatches the egg and spawns a new Omen Sheep at the current location if conditions are met.
     * The egg is removed after hatching.
     *
     * @param location The location where the egg will hatch
     * @return true if hatching was successful, false otherwise
     */
    @Override
    public boolean hatch(Location location) {
        // Check if the egg is ready to hatch (already checked in tick, but included here
        // to ensure the hatch method can be called independently)
        if (age >= HATCHING_AGE) {
            location.removeItem(this);
            
            if (!location.containsAnActor()) {
                location.addActor(new OmenSheep());
                return true;
            } else {
                return false;
            }
        }
        
        return false;
    }

    /**
     * Defines what happens when the egg is eaten by an actor.
     * Increases the maximum hit points of the actor by HEALTH_INCREASE.
     *
     * @param actor The actor eating the egg
     * @return true if the egg is successfully eaten, false otherwise
     */
    @Override
    public boolean eaten(Actor actor) {
        actor.heal(HEALTH_INCREASE);
        System.out.println(actor + " eats the Omen Sheep Egg and gains " + HEALTH_INCREASE + " maximum health!");
        return true;
    }
}