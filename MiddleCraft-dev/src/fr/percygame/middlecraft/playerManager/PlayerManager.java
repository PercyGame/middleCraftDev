package fr.percygame.middlecraft.playerManager;

import java.util.Map;
import java.util.UUID;

import fr.percygame.middlecraft.Main;

public class PlayerManager {
	
	public static boolean addPlayerToList(PlayerData pd) {
		UUID playerID = pd.getPlayerID();
		Main.players.put(playerID, pd);
		
		return true;
	}
	
	public static PlayerData getPlayerFromList(Map<UUID, PlayerData> targetList, UUID playerUUID) {
		PlayerData playerData = targetList.get(playerUUID);
		return playerData;
	}
}
