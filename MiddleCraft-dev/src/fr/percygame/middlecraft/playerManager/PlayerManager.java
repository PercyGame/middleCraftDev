package fr.percygame.middlecraft.playerManager;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.Plugin;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.utils.FileUtils;
import fr.percygame.middlecraft.utils.PlayerDataSerealizationManager;

public class PlayerManager {
	
	
	
	static Map<UUID, PlayerData> pl = Main.players;
	static File saveDir;
	final static PlayerDataSerealizationManager pdsm = new PlayerDataSerealizationManager();
	
	public PlayerManager(Plugin plugin) {
		 saveDir = new File(plugin.getDataFolder(), "/profils/");
	}
	
	public static boolean addPlayerToList(PlayerData pd) {
		UUID playerID = pd.getPlayerID();
		pl.put(playerID, pd);
		
		return true;
	}
	
	public static PlayerData getPlayerFromList(UUID playerUUID) {
		PlayerData playerData = pl.get(playerUUID);
		return playerData;
	}
	
	private static boolean savePlayer(UUID pId, PlayerData pd) {
		File f = new File(saveDir, pId + ".json");
		final String json = pdsm.serialize(pd);
		FileUtils.save(f, json);
		return true;
	}
	
	public static boolean savePlayers() {
		
		pl.forEach((pId, pd) -> savePlayer(pId, pd));
		
		return true;
	}
}
