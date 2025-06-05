package game;

import game.actors.npcs.*;
import game.displays.FancyMessage;
import game.enums.BehaviourSelection;
import game.grounds.crops.Bloodrose;
import game.grounds.crops.Inheritree;
import game.items.Talisman;
import game.grounds.Blight;
import game.grounds.Floor;
import game.actors.Player;
import game.actors.creatures.GoldenBeetle;
import game.actors.creatures.OmenSheep;
import game.actors.creatures.SpiritGoat;
import game.grounds.Soil;
import game.grounds.TeleportationCircle;
import game.grounds.Wall;
import game.items.Seed;
import java.util.Arrays;
import java.util.List;
import game.actors.creatures.*;


import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class to setup and run the game.
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
        // A3 Req 1
        List<String> limveldMap = Arrays.asList(
           ".............xxxx",
                "..............xxx",
                "................x",
                ".................",
                "................x",
                "...............xx",
                "..............xxx",
                "..............xxx",
                "..............xxx",
                ".............xxxx",
                ".............xxxx",
                "....xxx.....xxxxx",
                "....xxxx...xxxxxx");
        
        GameMap limveldGameMap = new GameMap("Limveld", groundFactory, limveldMap);
        world.addGameMap(limveldGameMap);
        

        // Create teleportation circles and place them directly A3 Req 1
        TeleportationCircle valleyTeleporter = new TeleportationCircle();
        TeleportationCircle limveldTeleporter = new TeleportationCircle();
        
        // Place teleporters on the maps A3 Req 1
        gameMap.at(23, 11).setGround(valleyTeleporter);
        limveldGameMap.at(8, 6).setGround(limveldTeleporter);
        
        // Add destinations to teleporters A3 Req 1
        valleyTeleporter.addDestination(limveldGameMap.at(8, 6), "Limveld");
        limveldTeleporter.addDestination(gameMap.at(23, 11), "Valley of the Inheritree");
        
        // Add spirit goat and omen sheep to the game map
        Creature spiritGoat = new SpiritGoat(BehaviourSelection.PRIORITY_BASED);
        gameMap.at(22, 13).addActor(spiritGoat);
        
        Creature omenSheep = new OmenSheep(BehaviourSelection.PRIORITY_BASED);
        //gameMap.at(24, 13).addActor(omenSheep);
        
        Creature goldenBeetle = new GoldenBeetle(BehaviourSelection.PRIORITY_BASED);

        //gameMap.at(23, 13).addActor(goldenBeetle);
        Creature spiritGoat2 = new SpiritGoat(BehaviourSelection.RANDOM);
        limveldGameMap.at(6, 6).addActor(spiritGoat2);
        
        Creature omenSheep2 = new OmenSheep(BehaviourSelection.RANDOM);
        limveldGameMap.at(9, 9).addActor(omenSheep2);
        
        Creature goldenBeetle2 = new GoldenBeetle(BehaviourSelection.RANDOM);
        limveldGameMap.at(10, 10).addActor(goldenBeetle2);
        
        Creature bedofchaos = new BedOfChaos(BehaviourSelection.PRIORITY_BASED);
        limveldGameMap.at(8, 8).addActor(bedofchaos);

        NPC guts = new Guts();
        //gameMap.at(22, 14).addActor(guts);

        NPC sellen = new Sellen();
        //gameMap.at(23, 14).addActor(sellen);

        NPC merchantKale = new MerchantKale();
        gameMap.at(24, 14).addActor(merchantKale);
        
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
        world.addPlayer(player, limveldGameMap.at(5,5));
        
        // game setup
        gameMap.at(24, 11).addItem(new Talisman());
        
        // game test where the "Farmer" starts with one seed of "Inheritree" and
        // one seed of "Bloodrose" in their inventory
        player.addItemToInventory(new Seed(new Inheritree()));
        player.addItemToInventory(new Seed(new Bloodrose()));

        player.addBalance(5000);

        world.run();

        // YOU DIED!
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
