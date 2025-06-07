package game.items.affectionitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.enums.Status;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.Item;

/**
 *
 * A thorned bloom that causes bleeding and negative affection
 * Dropped from Bloodrose when farmer is hurt
 *
 * @author Chong Jun Hao
 */
public class ThornedBloom extends AffectionItem {
    
    private static final int NEGATIVE_AFFECTION = -10;
    private static final int DAMAGE_TO_RECEIVER = 5;
    private static final int BLEEDING_DAMAGE = 5;
    
    /**
     * Constructor for ThornedBloom
     */
    public ThornedBloom() {
        super("Thorned Bloom", '^', NEGATIVE_AFFECTION);
        this.addCapability(Ability.CAUSE_BLEEDING);
    }
    
    @Override
    protected String applySpecificEffect(Actor giver, Actor receiver, GameMap map) {
        StringBuilder result = new StringBuilder();
        
        // Remove bleeding from giver if they have it
        if (giver.hasCapability(Status.BLEEDING)) {
            giver.removeCapability(Status.BLEEDING);
            result.append(giver).append(" stops bleeding as the thorns are passed on");
        }
        
        // Damage the receiver
        receiver.hurt(DAMAGE_TO_RECEIVER);
        result.append("\n").append("The thorns pierce ").append(receiver)
            .append(" for ").append(DAMAGE_TO_RECEIVER).append(" damage");
        
        // Check if receiver is unconscious
        if (!receiver.isConscious()) {
            result.append("\n").append(receiver).append(" collapses from the thorny pain");
            receiver.unconscious(map);
        }
        
        return result.toString();
    }
    
    /**
     * Causes bleeding damage to the carrier each turn
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        Display display = new Display();
        
        if (actor.hasAttribute(BaseActorAttributes.STAMINA) && !actor.hasCapability(Status.BLEEDING)) {
            for(Item item : actor.getItemInventory()) {
                if (item.hasCapability(Ability.CAUSE_BLEEDING)) {
                    actor.addCapability(Status.BLEEDING);
                    actor.hurt(BLEEDING_DAMAGE);
                    
                    display.println(actor + " bleeds for " + BLEEDING_DAMAGE + " damage from " + this);
                    
                    if (!actor.isConscious()) {
                        display.println(actor + " succumbs to blood loss");
                        actor.unconscious(currentLocation.map());
                    } // Already bleeding, no need to apply again
                }
            }
        }
        
        if (actor.hasCapability(Status.BLEEDING)) {
            actor.hurt(BLEEDING_DAMAGE);
            display.println(actor + " continues to bleed for " + BLEEDING_DAMAGE + " damage from " + this);
            
            if (!actor.isConscious()) {
                display.println(actor + " succumbs to blood loss");
                actor.unconscious(currentLocation.map());
            }
        }
    }
}