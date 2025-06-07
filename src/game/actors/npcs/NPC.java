package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.GiveAction;
import game.capabilities.Giveable;
import game.enums.Ability;
import game.enums.Status;
import game.items.affectionitems.AffectionItem;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing an NPC
 *
 * @author Lee Hao Zhe, modified by Chong Jun Hao
 */
public abstract class NPC extends Actor implements Giveable {

    /**
     * The behaviours TreeMap to store all the behaviours of the NPC
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private int affectionLevel;

    /**
     * Constructor
     *
     * @param name        the name of the NPC
     * @param displayChar the character that will represent the NPC in the display
     * @param hitPoints   the NPC's starting hit points
     */
    protected NPC(String name, char displayChar, int hitPoints, int affectionLevel) {
        super(name, displayChar, hitPoints);
        this.affectionLevel = affectionLevel;
        this.addCapability(Ability.RECEIVE);
    }
    
    public int getAffectionLevel() {
        return affectionLevel;
    }
    
    public boolean affectionIsPositive(){
        return affectionLevel > 0;
    }
    
    public void setAffectionLevel(int affectionLevel) {
        this.affectionLevel = affectionLevel;
    }
    
    public void removeBehaviour(int key) {
        this.behaviours.remove(key);
    }
    
    
    /**
     * Add behaviour to the behaviours TreeMap
     *
     * @param key       the key
     * @param behaviour the behaviour
     */
    public void addBehaviour(int key, Behaviour behaviour) {
        this.behaviours.put(key, behaviour);
    }

    /**
     * Gets the collection of behaviours
     *
     * @return the behaviours
     */
    public Collection<Behaviour> getBehaviours() {
        return behaviours.values();
    }
    
    /**
     * Allows the farmer to gift an affection item to this NPC. The gifting operation checks
     * whether the NPC has the capability to receive the gift and if the item can be given.
     * If successful, the item is removed from the actor's inventory, added to the NPC's inventory,
     * and any associated affection effect of the gift is applied between the actor and this NPC.
     *
     * @param actor the Player attempting to gift the item
     * @param gift the item to be gifted
     * @param map the map where the operation occurs
     * @return a message indicating the result of the gifting operation
     */
    @Override
    public String gift(Actor actor, Item gift, GameMap map) {
        if (this.hasCapability(Ability.RECEIVE) && gift.hasCapability(Ability.GIVE)) {
            actor.removeItemFromInventory(gift);
            this.addItemToInventory(gift);
            return ((AffectionItem) gift).applyAffection(actor, this, map);
        }
        return actor + " cannot give " + gift + " to " + this;
    }
    
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : getBehaviours()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        
        return new DoNothingAction();
    }
    
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        
        if (otherActor.hasCapability(Ability.RECEIVE)) {
            for(Item item : otherActor.getItemInventory()) {
                if (item.hasCapability(Ability.GIVE)){
                    actions.add(new GiveAction(this, item, direction));
                }
            }
        }
        return actions;
    }
}
