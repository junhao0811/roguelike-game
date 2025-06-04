package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.behaviours.BerserkBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.speakable.MonologueList;
import game.capabilities.speakable.Speakable;
import game.conditions.comparisons.AttributeComparisonCondition;
import game.conditions.comparisons.ComparisonOperations;
import game.enums.Ability;
import game.weapons.BareFist;

/**
 * Class representing the NPC Guts
 *
 * @author Abaan Anwar and Lee Hao Zhe
 */
public class Guts extends NPC implements Speakable {

    /**
     * Constructor
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist());
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1,
                new BerserkBehaviour(new AttributeComparisonCondition(ComparisonOperations.GT,
                        BaseActorAttributes.HEALTH, 50)));
    }

    @Override
    public MonologueList getMonologues(Actor actor, GameMap map) {
        MonologueList monologues = new MonologueList(actor, map);

        monologues.add("RAAAAGH!");
        monologues.add("I'LL CRUSH YOU ALL!");
        monologues.add("WEAK! TOO WEAK TO FIGHT ME!",
                new AttributeComparisonCondition(ComparisonOperations.LT,
                        BaseActorAttributes.HEALTH,50));

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
