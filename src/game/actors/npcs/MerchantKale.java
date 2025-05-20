package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.behaviours.WanderBehaviour;
import game.capabilities.Speakable;

public class MerchantKale extends NPC implements Speakable {

    public MerchantKale() {
        super("Merchant Kale", 'k', 200);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
    }

    @Override
    public String speak(Actor actor, GameMap map) {
        return "";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        actions.add(new SpeakAction(this, direction));

        return actions;
    }
}
