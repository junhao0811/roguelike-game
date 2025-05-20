package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.actors.npcs.MonologueList;
import game.behaviours.WanderBehaviour;
import game.capabilities.Speakable;
import game.weapons.Broadsword;
import game.weapons.DragonslayerGreatsword;
import game.weapons.Katana;

public class Sellen extends Merchant implements Speakable {

    public Sellen() {
        super("Sellen", 's', 150);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.setupSellables();
    }

    @Override
    public void setupSellables() {
        this.addSellable(new Broadsword(100));
        this.addSellable(new DragonslayerGreatsword(1500));
        this.addSellable(new Katana(500));
    }

    @Override
    public String speak(Actor actor, GameMap map) {
        MonologueList monologues = new MonologueList(actor, map);

        monologues.add("The academy casts out those it fears. Yet knowledge, " +
                "like the stars, cannot be bound forever.");

        monologues.add("You sense it too, don’t you? The Glintstone hums, even now.");

        return this + ": “" + monologues.getMonologue() + "”";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        actions.add(new SpeakAction(this, direction));

        return actions;
    }
}
