package fr.percygame.middlecraft.playerManager;

import java.util.Map;
import java.util.UUID;

import fr.percygame.middlecraft.Main;

public class PlayerManager {
	
	static Map<UUID, PlayerData> pl = Main.players;
	
	public static boolean addPlayerToList(PlayerData pd) {
		UUID playerID = pd.getPlayerID();
		pl.put(playerID, pd);
		
		return true;
	}
	
	public static PlayerData getPlayerFromList(UUID playerUUID) {
		PlayerData playerData =pl.get(playerUUID);
		return playerData;
	}
	
	public static boolean savePlayers() {
		
		return true;
	}
}
