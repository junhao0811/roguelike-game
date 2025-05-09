package game.driver;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.creatures.OmenSheep;
import game.creatures.SpiritGoat;
import game.displays.FancyMessage;
import game.grounds.Blight;
import game.grounds.Floor;
import game.grounds.Soil;
import game.items.Talisman;
import game.player.Player;
import game.structures.Wall;

/**
 * The main class to setup and run the game.
 * @author Adrian Kristanto
 */
public class Application {
    /**
     * The main driver of the game to start the game.
     */
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

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Farmer", '@', 100, 200); //req 1 (dependency on player2
    // )
        world.addPlayer(player, gameMap.at(23, 10));
        gameMap.at(20, 8).addActor(new OmenSheep()); //req 1  (dependency on location and omen sheep)
        gameMap.at(35,10).addActor(new OmenSheep());
        gameMap.at(15, 11).addActor(new SpiritGoat()); // req 1 (dependency on location and spirit goat)
        gameMap.at(18, 12).addActor(new SpiritGoat());

        // game setup
        gameMap.at(24, 11).addItem(new Talisman());

        world.run();
    }
}
