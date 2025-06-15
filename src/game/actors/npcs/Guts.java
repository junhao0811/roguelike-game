package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.behaviours.BerserkBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.speakable.MonologueList;
import game.conditions.comparisons.AttributeComparisonCondition;
import game.conditions.comparisons.ComparisonOperations;
import game.enums.Ability;
import game.weapons.BareFist;
import game.enums.Status;
import game.items.consumables.StaminaTonic;

/**
 * Class representing the NPC Guts
 *
 * @author Abaan Anwar and Lee Hao Zhe, modified by Chong Jun Hao
 */
public class Guts extends NPC {

    /**
     * Constructor
     */
    public Guts() {
        super("Guts", 'g', 500, -20);
        this.setIntrinsicWeapon(new BareFist());
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        updateBerserkBehavior();
    }
    
    public void updateBerserkBehavior(){
        if (getAttribute(Status.AFFECTION) < 0 && !this.hasCapability(Status.UNFRIENDLY))  {
            this.addCapability(Status.UNFRIENDLY);
            this.addBehaviour(Integer.MAX_VALUE - 1,
                new BerserkBehaviour(new AttributeComparisonCondition(ComparisonOperations.LT,
                    BaseActorAttributes.HEALTH, 50)));
        } else if(getAttribute(Status.AFFECTION) >= 0) {
            this.removeCapability(Status.UNFRIENDLY);
            this.removeBehaviour(Integer.MAX_VALUE - 1);
        }
        
    }
    
    /**
     * Generates and returns a list of monologues for Guts based on the affection level
     * between the actor and Guts. The monologues may vary depending on predefined
     * affection thresholds and certain capabilities.
     *
     * @param actor the actor interacting with Guts
     * @param map   the current game map
     * @return a MonologueList containing Guts' monologues
     */
    @Override
    public MonologueList getMonologues(Actor actor, GameMap map) {
        MonologueList monologues = new MonologueList(actor, map);
        
        // Different monologues based on affection level
        // Check for special gift at 20+ affection
        if (getAttribute(Status.AFFECTION) >= 20 && !this.hasCapability(Status.DELIVERED_STAMINA_TONIC)) {
            // Give attack potion
            StaminaTonic staminaPotion = new StaminaTonic();
            actor.addItemToInventory(staminaPotion);
            this.addCapability(Status.DELIVERED_STAMINA_TONIC);
            
            monologues.add("You've proven yourself, warrior. Take this - it will make you even stronger in battle!");
            monologues.add("This potion contains the fury of a thousand battles. Use it to crush your enemies!");
            return monologues;
        } else if (getAttribute(Status.AFFECTION) > 0) {
            // Low positive affection - neutral/respectful monologues
            monologues.add("You're not as weak as I thought.");
            monologues.add("Hmph. You show some promise.");
            monologues.add("Keep training. You might be worthy of my respect.");
        } else if (getAttribute(Status.AFFECTION) > -20) {
            // Neutral/slightly negative - standard aggressive monologues
            monologues.add("RAAAAGH!");
            monologues.add("I'LL CRUSH YOU ALL!");
            monologues.add("WEAK! TOO WEAK TO FIGHT ME!",
                new AttributeComparisonCondition(ComparisonOperations.LT,
                    BaseActorAttributes.HEALTH, 50));
        } else {
            // Very negative affection - extremely hostile monologues
            monologues.add("YOU WORTHLESS SCUM!");
            monologues.add("I'LL TEAR YOU APART!");
            monologues.add("DIE! DIE! DIE!");
            monologues.add("YOUR BLOOD WILL PAINT THE GROUND RED!");
        }
        
        return monologues;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Ability.LISTEN)) {
            actions.add(new SpeakAction(this, direction));
        }

        return actions;
    }
}
