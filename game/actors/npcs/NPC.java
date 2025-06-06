package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.enums.Ability;
import game.actions.GiftAction;
import game.capabilities.Giftable;
import game.enums.Status;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import edu.monash.fit2099.engine.items.Item;
import game.items.affectionitems.AffectionItem;
/**
 * Class representing an NPC
 *
 * @author Lee Hao Zhe, modified by Chong Jun Hao
 */
public abstract class NPC extends Actor implements Giftable {

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
        this.addCapability(Ability.CAN_BE_AFFECTED);
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

    //     String gift(Actor actor, GameMap map);
    @Override
    public String gift(Actor actor, Item gift, GameMap map) {
        if (this.hasCapability(Ability.CAN_BE_AFFECTED) && gift.hasCapability(Ability.CAN_AFFECT)) {
            actor.removeItemFromInventory(gift);
            this.addItemToInventory(gift);
            return gift.applyAffection(actor, this, map);
        }
        return actor + " cannot affect " + this + " with this item.";
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

        if (otherActor.hasCapability(Ability.CAN_BE_AFFECTED)) {
            for(Item item : otherActor.getItemInventory()) {
                if (item.hasCapability(Ability.CAN_AFFECT)){
                    actions.add(new GiftAction(this, item, direction));
                }
            }
        }
        return actions;
    }
}
