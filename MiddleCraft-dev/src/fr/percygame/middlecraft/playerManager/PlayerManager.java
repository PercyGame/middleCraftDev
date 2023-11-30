package fr.percygame.middlecraft.playerManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.Plugin;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.utils.FileUtils;
import fr.percygame.middlecraft.utils.PlayerDataSerealizationManager;

public class PlayerManager {
	
	
	
	static Map<UUID, PlayerData> pl = Main.players;
	public static List<PlayerData> players = new ArrayList<PlayerData>();
	static File saveDir;
	final static PlayerDataSerealizationManager pdsm = new PlayerDataSerealizationManager();
	
	
	public PlayerManager(Plugin plugin) {
		saveDir = new File(plugin.getDataFolder(), "/playerData/");
	}	
	
	public static boolean addPlayerToList(PlayerData pd) {
		UUID playerID = pd.getPlayerID();
		pl.put(playerID, pd);
		players.add(pd);
		
		return true;
	}
	
	public static PlayerData getPlayerFromList(UUID playerUUID) {
		PlayerData playerData = pl.get(playerUUID);
		return playerData;
	}
	
	private static boolean savePlayer(UUID pId, PlayerData pd) {
		File f = new File(saveDir, pId + ".json"); // create object of save file
		final String json = pdsm.serialize(pd); // serialize PlayerData to json syntaxe
		FileUtils.save(f, json); // save data in json syntaxe in the right file
		return true;
	}
	
	public static boolean savePlayers() {
		
		try {
			pl.forEach((pId, pd) -> savePlayer(pId, pd));
		}
		catch (Exception e){
			return false;
		}
		
		return true;
	}

	public static boolean loadPlayers() {
		
		File[] files = saveDir.listFiles();
		
		if (!files.equals(null)) {
			for (File file : files) {
				String json = FileUtils.loadContent(file);
				PlayerData pd = pdsm.deserialise(json);
				pl.put(pd.getPlayerID(), pd);
			}
			
			return true;
		}
		return false;
		
	}
	
	
	public static PlayerData getPlayerByName(String playerName) {
		players.clear(); //clearing array list from older operation
		PlayerData playerTempData;
		
		pl.forEach((pId, pd) -> players.add(pd)); //putting in array list all the loading playerData
				
		//scan all playerData to get the right name, and return it
		for (int i=0; i<players.size(); i=i+1) {
			playerTempData = players.get(i);
			if (playerTempData.getPlayerName().equals(playerName)) {
				return playerTempData;
			}
		}
		return null;
	}
	
}
