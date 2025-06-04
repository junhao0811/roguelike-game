package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;

import game.enums.Ability;
import game.weapons.BareFist;
import game.enums.Status;

/**
 * Class representing the Player
 *
 * @author Adrian Kristanto
 */
public class Player extends Actor {
    
    /**
     * Constructor
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.setIntrinsicWeapon(new BareFist());
        this.setupCapabilities();
    }

    /**
     * Method to setup all the capabilities of the Player
     */
    private void setupCapabilities() {
        this.addCapability(Ability.PLANT_CROPS);
        this.addCapability(Ability.EAT);
        this.addCapability(Ability.CAN_BE_FOLLOWED);
        this.addCapability(Ability.LISTEN);
        this.addCapability(Ability.PURCHASE);
        this.addCapability(Ability.CAN_TELEPORT);

    }

    /**
     * Determines and performs the player's action during their turn, including handling ongoing
     * multi-turn actions, death, and presenting a menu of available actions.
     *
     * @param actions The list of actions the player can take this turn.
     * @param lastAction The action executed by the player in the previous turn, which may have a follow-up action.
     * @param map The game map the player is currently on.
     * @param display The display used for output to the player.
     * @return The action chosen by the player to be performed on this turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null) {
            return lastAction.getNextAction();
        }

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * A method for returning the string representation of the player. It displays the player's name
     * and its current hit points, along with its maximum health hit points. Moreover, it also
     * displays the player's current stamina, along with its maximum stamina.
     *
     * @return a string representation of the player
     */
    @Override
    public String toString() {
        return name + " (HP: " +
                this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ", STA: " +
                this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.STAMINA) +
                ")";
    }
}