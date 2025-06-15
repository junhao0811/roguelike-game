package game.actors.creatures;

import java.util.Random;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.conditions.CounterCondition;
import game.effects.attributeeffects.IncreaseMaxAttributeEffect;
import game.enums.Ability;
import game.enums.BehaviourSelection;
import game.enums.Status;
import game.actions.CureAction;
import game.actors.statuseffects.CrimsonRot;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Curable;
import game.capabilities.Hatchable;
import game.capabilities.Reproducible;
import game.grounds.crops.Inheritree;
import game.items.Egg;
import game.items.affectionitems.SacredTear;

/**
 * Class representing an Omen Sheep
 *
 * @author Chong Jun Hao and Ng Jun Xian
 * @version 22.0.1
 */
public class OmenSheep extends Creature implements Curable, Reproducible, Hatchable {

    /**
     * Constructor
     *
     * @param selection the Omen Sheep's strategy for selecting behaviours
     */
    public OmenSheep(BehaviourSelection selection) {
        super("Omen Sheep", 'm', 75, selection);
    }

    /**
     * Constructor with default behaviour selection as default
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 75);
    }

    @Override
    protected void setup() {
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1,
                new ReproduceBehaviour(this, new CounterCondition(7)));
        this.addStatusEffect(new CrimsonRot(15));
        this.addCapability(Ability.PRODUCE_EGG);
        this.addCapability(Status.CURSED);
    }
    
    /**
     * Handles the unconscious state of the Omen Sheep by removing it from the map,
     * and potentially dropping a Sacred Tear at its current location.
     *
     * @param actor The actor causing the Omen Sheep to become unconscious.
     * @param map The game map where the unconscious action takes place.
     * @return A string message indicating the result of the unconscious action,
     *         including details about the Sacred Tear drop if applicable.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Get the location before removing the sheep
        Location currentLocation = map.locationOf(this);
        super.unconscious(map);
        
        String result = null;
        Random random = new Random();
        
        if (currentLocation != null) {
            // drops SacredTear
            SacredTear sacredTear = new SacredTear();
            currentLocation.addItem(sacredTear);
            result = "A Sacred Tear drops from the fallen " + this + "!";
        }
        
        return result;
    }

    @Override
    public String reproduce(Actor actor, Location location) {
        Egg<OmenSheep> egg = new Egg<>(this, new CounterCondition(3));
        egg.addEffect(new IncreaseMaxAttributeEffect(BaseActorAttributes.HEALTH, 10));
        location.addItem(egg);

        return this + " produces a new " + egg;
    }

    @Override
    public Creature hatch() {
        return new OmenSheep(getSelection());
    }

    @Override
    public String cure(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            destination.setGround(new Inheritree());
        }
        return actor + " cures the " + this + ", Inheritrees grow at each tile surrounding it";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        for (Item item : otherActor.getItemInventory()) {
            if (item.hasCapability(Ability.CURE)) {
                actions.add(new CureAction(this, direction, item));
            }
        }

        return actions;
    }
}
