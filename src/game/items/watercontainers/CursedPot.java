package game.items.watercontainers;

import game.enums.Status;

public class CursedPot extends WaterContainer {

    public CursedPot() {
        super("Cursed Pot", 'c');
        this.addCapability(Status.CURSED);
    }
}
