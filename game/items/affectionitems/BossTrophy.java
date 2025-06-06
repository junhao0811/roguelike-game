package game.items.affectionitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GiftAction;
import game.enums.Ability;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A boss trophy that provides high positive affection
 * Dropped from Bed of Chaos after defeat
 *
 * @author GitHub Copilot
 */
public class BossTrophy extends AffectionItem {

    private static final int POSITIVE_AFFECTION = 25;


    /**
     * Constructor for BossTrophy
     */
    public BossTrophy() {
        super("Boss Trophy", 'B', POSITIVE_AFFECTION);
    }

    @Override
    protected String applySpecificEffect(Actor giver, Actor receiver, GameMap map) {
        StringBuilder result = new StringBuilder();

        result.append(receiver).append(" is impressed by the legendary trophy");

        result.append("\n").append(giver).append(" gains prestige from showing the trophy");

        // Specific reactions based on NPC
        String receiverName = receiver.toString();
        switch (receiverName) {
            case "Sellen":
                result.append("\n").append("\"Incredible! The power emanating from this trophy...\"");
                break;
            case "Guts":
                result.append("\n").append("\"Hmph. You've proven yourself worthy, warrior.\"");
                break;
            case "Merchant Kale":
                result.append("\n").append("\"My word! A genuine boss trophy! This changes everything!\"");
                break;
            default:
                result.append("\n").append(receiver).append(" regards you with newfound respect");
        }

        return result.toString();
    }

    /**
	 * List of allowable actions that the item allows its owner do to other actor.
	 * Example #1: a weapon can return an attacking action to the other actor.
	 * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
	 * Example #3: a food can return an action to feed the other actor.
	 *
	 * @param otherActor the other actor
	 * @param location the location of the other actor
	 * @return an unmodifiable list of Actions
	 */
    @Override
	public ActionList allowableActions(Actor targetNPC, Location location){
		ActionList actions = new ActionList();
        if (targetNPC.hasCapability(Ability.CAN_BE_AFFECTED)) {
            actions.add(new GiftAction(targetNPC, this));
        }
        return actions;
	}
}
