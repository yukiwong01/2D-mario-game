package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.grounds.*;
import game.items.PowerStar;
import game.items.SuperMushroom;

/**
 * The main class for the Mario World game.
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(), new HealthFountain(), new PowerFountain());

        List<String> map = Arrays.asList(
                "..........................................##..........+.........................",
                "............+............+..................#...................................",
                "............................................#...................................",
                ".............................................##......................+..........",
                "...............................................#................................",
                "................................................#...............................",
                ".................+................................#.............................",
                "............................................A....##.............................",
                "................................................##..............................",
                ".........+..............................+#____####.................+............",
                ".......................................+#_____###++.............................",
                ".......................................+#______###..............................",
                "........................................+#_____###..............................",
                "........................+........................##.............+...............",
                "...................................................#............................",
                ".............................................H......#...........................",
                "...................+.................................#..........................",
                "......................................................#.........................",
                ".......................................................##.......................");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        Actor mario = new Player("Player", 'm', 100);
        world.addPlayer(mario, gameMap.at(42, 10));

        // REQ 5: add the two items together with Player
        gameMap.at(42, 10).addItem(new SuperMushroom());
        gameMap.at(42, 10).addItem(new PowerStar());

        gameMap.at(44, 11).addActor(new Toad());

        FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Lava());

        List<String> map2 = Arrays.asList(
                ".......................LLLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
                "........................LLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
                "..........................LLLLLLLLLLLLLLLLLLLLLLLLLLL",
                "..............................LLLLLLLLLLLLLLLLLLLLLLL",
                "LL...................................................",
                "LLLL.............LLLLL...............................",
                "LLLLL...........LLLLLLLLLL...........................",
                "LLLLLLL.........LLLLLLLLLL.................LLLLLLLLLL",
                "LLLLL............LLLLLLLLLL...............LLLLLLLLLLL",
                "LLL...............LLLLL..............LLLLLLLLLLLLLLLL",
                "LL..............................LLLLLLLLLLLLLLLLLLLLL",
                "............................LLLLLLLLLLLLLLLLLLLLLLLLL",
                "........................LLLLLLLLLLLLLLLLLLLLLLLLLLLLL");

        GameMap gameMap2 = new GameMap(groundFactory2, map2);
        world.addGameMap(gameMap2);

        gameMap2.at(48, 5).addActor(new Bowser(gameMap2.at(48, 5)));
        gameMap2.at(49, 5).addActor(new PrincessPeach());

        gameMap.at(10, 17).setGround(new WarpPipe(gameMap2.at(0, 0)));
        gameMap.at(47, 13).setGround(new WarpPipe(gameMap2.at(0, 0)));
        gameMap.at(20, 3).setGround(new WarpPipe(gameMap2.at(0, 0)));
        gameMap.at(60, 5).setGround(new WarpPipe(gameMap2.at(0, 0)));
        gameMap.at(70, 16).setGround(new WarpPipe(gameMap2.at(0, 0)));

        gameMap2.at(0, 12).addActor(new Ghost());

        world.run();
    }
}
