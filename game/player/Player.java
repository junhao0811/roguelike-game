package game.player;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Menu;

import game.items.seeds.BloodroseSeed;
import game.items.seeds.InheritreeSeed;
import game.status.Status;
import game.weapons.BareFist;


/**
 * Class representing the Player.
 * @author Adrian Kristanto
 * modified by Ng Jun Xian
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int staminaPoints) { //req 1
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.setIntrinsicWeapon(new BareFist());
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(staminaPoints)); //req 1
        this.addItemToInventory(new InheritreeSeed());
        this.addItemToInventory(new BloodroseSeed());
    }

    /**
     * Determines and returns an action to perform on the current turn.
     * If the last action has a follow-up action, returns that follow-up action.
     * Otherwise, displays a menu of available actions for the player to choose from.
     *
     * @param actions    collection of possible Actions for this player
     * @param lastAction The Action this player took last turn
     * @param map        the game map containing the player
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed by the player this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }
    
    public String toString() { //req 1
        return super.toString() + " (" + this.getAttribute(BaseActorAttributes.STAMINA) + "/" + this.getAttributeMaximum(BaseActorAttributes.STAMINA) + ")";
    }
}
