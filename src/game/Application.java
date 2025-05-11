package game;

import game.actors.creatures.Creature;
import game.displays.FancyMessage;
import game.items.Talisman;
import game.grounds.Blight;
import game.grounds.Floor;
import game.actors.creatures.OmenSheep;
import game.actors.CrimsonRot;
import game.actors.Player;
import game.grounds.Soil;
import game.actors.creatures.SpiritGoat;
import game.grounds.Wall;
import game.items.seeds.BloodroseSeed;
import game.items.seeds.InheritreeSeed;
import game.items.seeds.Seed;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The game.main class to setup and run the game.
 *
 * @author Adrian Kristanto, modified by Chong Jun Hao
 */
public class Application {
    
    public static void main(String[] args) {
        
        World world = new World(new Display());
        
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(),
            new Wall(), new Floor(), new Soil());
        
        List<String> map = Arrays.asList(
            "xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
            "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
            "..........xxxx....xxxxxxxxxxxxxx.......x",
            "....xxx...........xxxxxxxxxxxxxxx.....xx",
            "...xxxxx...........xxxxxxxxxxxxxx.....xx",
            "...xxxxxxxxxx.......xxxxxxxx...xx......x",
            "....xxxxxxxxxx........xxxxxx...xxx......",
            "....xxxxxxxxxxx.........xxx....xxxx.....",
            "....xxxxxxxxxxx................xxxx.....",
            "...xxxx...xxxxxx.....#####.....xxx......",
            "...xxx....xxxxxxx....#___#.....xx.......",
            "..xxxx...xxxxxxxxx...#___#....xx........",
            "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
            "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
            "xxxxx..xxxxxxxxxxxx.......xxxxxx......xx");
        
        GameMap gameMap = new GameMap("Valley of the Inheritree", groundFactory, map);
        world.addGameMap(gameMap);
        
        // Add spirit goat and omen sheep to the game map, with CrimsonRot status effect
        Creature spiritGoat = new SpiritGoat();
        gameMap.at(22, 13).addActor(spiritGoat);
        spiritGoat.addStatusEffect(new CrimsonRot(10));
        
        Creature omenSheep = new OmenSheep();
        gameMap.at(27, 13).addActor(omenSheep);
        omenSheep.addStatusEffect(new CrimsonRot(15));
        
        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        
        Player player = new Player("Farmer", '@', 100, 200);
        world.addPlayer(player, gameMap.at(23, 10));
        
        // Add CrimsonRot status effect to player
        player.addStatusEffect(new CrimsonRot());
        
        // game setup
        gameMap.at(24, 11).addItem(new Talisman());
        
        // game test where the "Farmer" starts with one seed of "Inheritree" and one seed of "Bloodrose" in their inventory
        Seed inheritreeSeed = new InheritreeSeed();
        player.addItemToInventory(inheritreeSeed);
        
        Seed bloodroseSeed = new BloodroseSeed();
        player.addItemToInventory(bloodroseSeed);
        
        world.run();
    }
}
