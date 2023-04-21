package org.battlefield;

import org.battlefield.controller.GameController;
import org.battlefield.controller.PlayerController;
import org.battlefield.controller.ShipController;
import org.battlefield.dao.BaseDao;
import org.battlefield.dao.BattleFieldDao;
import org.battlefield.dao.PlayerDao;
import org.battlefield.dao.inmemory.DefaultBattleFieldDao;
import org.battlefield.dao.inmemory.DefaultMissileDao;
import org.battlefield.dao.inmemory.DefaultPlayerDao;
import org.battlefield.dao.inmemory.DefaultShipDao;
import org.battlefield.model.Missile;
import org.battlefield.model.Player;
import org.battlefield.model.Ship;
import org.battlefield.service.GameService;
import org.battlefield.service.PlayerService;
import org.battlefield.service.ShipService;
import org.battlefield.service.impl.DefaultGameService;
import org.battlefield.service.impl.DefaultPlayerService;
import org.battlefield.service.impl.DefaultShipService;
import org.battlefield.strategy.BattleStrategy;
import org.battlefield.strategy.impl.DefaultBattleStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlayerDao playerDao = new DefaultPlayerDao();
        PlayerService playerService = new DefaultPlayerService(playerDao);
        PlayerController playerController = new PlayerController(playerService);

        BaseDao<Ship> shipBaseDao = new DefaultShipDao();
        BaseDao<Missile> missileBaseDao = new DefaultMissileDao();

        BattleFieldDao gameDao = new DefaultBattleFieldDao();
        BattleStrategy battleStrategy =
                new DefaultBattleStrategy(
                        missileBaseDao,
                        shipBaseDao,
                        gameDao);
        GameService gameService = new DefaultGameService(gameDao, battleStrategy);
        GameController gameController = new GameController(gameService);

        ShipService shipService = new DefaultShipService(gameService, shipBaseDao);
        ShipController shipController = new ShipController(shipService);

        Player playerA = new Player("A", "A", new ArrayList<>(), new ArrayList<>());
        Player playerB = new Player("B", "B", new ArrayList<>(), new ArrayList<>());
        playerController.addPlayer(playerA);
        playerController.addPlayer(playerB);
        List<String > playerList = new ArrayList<>();
        playerList.add(playerA.getId());
        playerList.add(playerB.getId());

        int N = 6;
        gameController.initGame(N, "sample", playerList);

        Ship shipA = new Ship("A-SH1", 1, 5, 2, false, playerA.getId(), "sample");
        Ship shipB = new Ship("B-SH1", 4,4, 2, false, playerB.getId(), "sample");
        List<Ship> ships = new ArrayList<>();
        ships.add(shipA);
        ships.add(shipB);
        shipController.addShip(ships);


        gameController.viewBattleField("sample");
        shipController.addShip(ships);
        gameController.viewBattleField("sample");

        gameController.startGame("sample");
        gameController.viewBattleField("sample");

        System.out.println(String.format("Player: %s lost the game", gameController.getBattleField("sample").getLoserId()));
    }
}