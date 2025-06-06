package game.items.affectionitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GiftAction;
import game.actors.npcs.NPC;
import game.capabilities.Giftable;
import game.enums.Ability;

/**
 * Abstract base class for all items that can influence NPC affection
 * Implements the Affection interface and extends Item
 * Follows Template Method pattern for common affection item behavior
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
 */
public abstract class AffectionItem extends Item {

    protected final int affectionValue;

    /**
     * Constructor for AffectionItem
     *
     * @param name the name of the affection item
     * @param displayChar the display character
     * @param affectionValue the affection value (positive or negative)
     */
    protected AffectionItem(String name, char displayChar, int affectionValue) {
        super(name, displayChar, true);
        this.affectionValue = affectionValue;
        this.addCapability(Ability.CAN_AFFECT);
    }

    public int getAffectionValue() {
        return affectionValue;
    }

    public String applyAffection(Actor giver, NPC receiver, GameMap map) {
        StringBuilder result = new StringBuilder();

        // Apply the specific affection effect
        result.append(applySpecificEffect(giver, receiver, map));

        // Update NPC affection level if they support it
        if (receiver.hasCapability(Ability.CAN_BE_AFFECTED)) {
            updateNPCAffection(receiver, giver);
            result.append("\n").append(getAffectionMessage(receiver));
        }

        return result.toString();
    }

    /**
     * Template method for specific affection effects
     * Subclasses override this for unique behaviors
     */
    protected abstract String applySpecificEffect(Actor giver, Actor receiver, GameMap map);

    /**
     * Updates the NPC's affection level towards the giver
     */
    private void updateNPCAffection(NPC receiver, Actor giver) {
        if (receiver.hasCapability(Ability.CAN_BE_AFFECTED)) {
            int newAffectionLevel = receiver.getAffectionLevel() + affectionValue;
            receiver.setAffectionLevel(newAffectionLevel);
        }
    }

    /**
     * Gets the appropriate affection message
     */
    private String getAffectionMessage(NPC receiver) {
        if (receiver.affectionIsPositive()) {
            return receiver + " seems pleased with the gift";
        } else {
            return receiver + " looks disgusted with the gift";
        }
    }

    public GiftAction getGiftAction(Giftable targetNPC) {
        return new GiftAction(targetNPC, this);
    }
}
