package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Ability;
import game.weapons.BareFist;
import game.Status;

/**
 * Class representing the Player.
 * @author Adrian Kristanto
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.PLANT_CROPS);
        this.setIntrinsicWeapon(new BareFist());
    }
    
    /**
     * A method for returning the string representation of the player.
     * It displays the player's name and its current hit points, along with its maximum health hit points.
     * Moreover, it also displays the player's current stamina, along with its maximum stamina.
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
    
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }
}
