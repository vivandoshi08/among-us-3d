package de.twometer.amongus3d.server;

import com.esotericsoftware.kryonet.Connection;
import de.twometer.amongus3d.core.GameState;
import de.twometer.amongus3d.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerSession {

    public String gameId;

    public String host;

    public long autoSkipTask = -9;

    public Map<String, ServerPlayer> players = new ConcurrentHashMap<>();

    public GameState.State gameState = GameState.State.Lobby;
    public int skipVotes;

    public int totalTasks = 0;
    public int totalTasksDone = 0;

    public ServerSession(String gameId) {
        this.gameId = gameId;
    }

    public void addPlayer(Connection connection, String username) {
        ServerPlayer player = new ServerPlayer(connection, this);
        player.player = new Player(username);
        players.put(username, player);
    }

    public void sendToAll(Object o) {
        for (ServerPlayer player : players.values())
            player.connection.sendTCP(o);
    }
}
