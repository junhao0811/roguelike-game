package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.EatAction;
import game.capabilities.Edible;
import game.capabilities.Hatchable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class representing an Egg that an actor can pick up and drop. The egg has the ability to hatch
 * into a creature.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class Egg extends Item implements Edible {
    
    private final Random random = new Random();
    private final Hatchable creature;
    private final String name;
    private int counter;
    
    /**
     * Constructor.
     */
    public Egg(Hatchable creature, String name) {
        super(name, '0', true);
        this.creature = creature;
        this.counter = 0;
        this.name = name;
    }
    
    /**
     * Updates the state of the Egg during each game tick by checking its hatching condition
     * and handling the hatching process if it is ready to hatch.
     *
     * @param currentLocation the location of the Egg in the game at the time of the tick
     */
    @Override
    public void tick(Location currentLocation) {
        if (creature.isReadyToHatch(counter, currentLocation)) {
            Actor newCreature = creature.hatch();
            // Check if the current location is occupied by an actor
            if (currentLocation.containsAnActor()) {
                List<Location> validSurroundingTiles = new ArrayList<>();
                
                // Iterate through the surrounding tiles from the current location
                for (Exit exit : currentLocation.getExits()) {
                    Location surroundingTile = exit.getDestination();
                    
                    // Check whether the surrounding tiles are already occupied
                    if (surroundingTile.canActorEnter(newCreature)
                        && !surroundingTile.containsAnActor()) {
                        validSurroundingTiles.add(surroundingTile);
                    }
                }
                
                // Delay the egg hatching if all surrounding tiles are already occupied
                if (validSurroundingTiles.isEmpty()) {
                    return;
                }
                
                // Randomly select a valid tile from the surrounding to allow the new creature to hatch
                Location randomSurroundingTile = validSurroundingTiles.get(
                    random.nextInt(validSurroundingTiles.size()));
                currentLocation.removeItem(this);
                randomSurroundingTile.addActor(newCreature);
            } else {
                // If the current location is empty, the new creature will hatch at the current location
                currentLocation.removeItem(this);
                currentLocation.addActor(newCreature);
            }
            new Display().println("A new " + newCreature + " hatches from the Egg!");
        }
        counter++;
    }

    /**
     * Allows the provided actor to eat the Egg and apply its specific effect.
     * The Egg is removed from the actor's inventory upon consumption.
     *
     * @param actor the actor attempting to eat the Egg
     * @param map the current game map
     */
    public void eat(Actor actor, GameMap map) {
        Display display = new Display();

        actor.removeItemFromInventory(this);

        // Apply different effects based on egg type
        if (this.name.equals("Golden Egg")) {
            // Golden Egg restores 20 stamina
            actor.modifyAttribute(BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.INCREASE, 20);
            display.println(actor + " eats the Golden Egg and stamina is restored! (+20 stamina)");

        } else {
            // Regular egg increases max health by 10
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH,
                    ActorAttributeOperations.INCREASE, 10);
            display.println(actor + " eats the Egg and health is restored! (+10 max health)");
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

        if (owner.hasCapability(Ability.EAT)) {
            actions.add(new EatAction(this));
        }

        return actions;
    }
}
