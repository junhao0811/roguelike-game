package game.actors.creatures;

import java.util.Random;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
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

    private int reproductionCounter = 0;
    
    private Random random = new Random();
    /**
     * Constructor
     */
    public OmenSheep(BehaviourSelection selectionStrategy) {
        super("Omen Sheep", 'm', 75, selectionStrategy);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1, new ReproduceBehaviour(this));
        this.addStatusEffect(new CrimsonRot(15));
        this.addCapability(Ability.PRODUCE_EGG);
        this.addCapability(Status.CURSED);
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
    public void reproduce(Actor actor, Location location) {
        location.addItem(new Egg(this, "Egg"));
    }

    @Override
    public boolean canReproduce(Actor actor, GameMap map) {
        reproductionCounter++;
        return reproductionCounter % 7 == 0;
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        super.unconscious(map);
        return dropSacredTear(map);
    }
    
    /**
     * Handles the rare drop of SacredTear when OmenSheep dies
     * Low drop rate of approximately 15%
     */
    private String dropSacredTear(GameMap map) {
        String result = null;
        if (random.nextDouble() < 0.15) {
            Location currentLocation = map.locationOf(this);
            if (currentLocation != null) {
                SacredTear sacredTear = new SacredTear();
                currentLocation.addItem(sacredTear);
                
                result = "A Sacred Tear drops from the fallen " + this + "!";
            }
        }
        return result;
    }

    @Override
    public Creature hatch() {
        BehaviourSelection offspringBehavior;
        if (random.nextBoolean()) {
            offspringBehavior = BehaviourSelection.RANDOM;
        } else {
            offspringBehavior = BehaviourSelection.PRIORITY_BASED;
        }
        return new OmenSheep(offspringBehavior);
    }

    @Override
    public boolean isReadyToHatch(int counter, Location location) {
        return counter == 3;
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
