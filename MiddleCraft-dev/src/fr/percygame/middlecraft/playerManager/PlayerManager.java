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
	
	
	
	static Map<UUID, PlayerData> pl = Main.players; //get players Map from Main
	public static List<PlayerData> players = new ArrayList<PlayerData>(); //create List of players
	static File saveDir; // create the path for saving files
	final static PlayerDataSerealizationManager pdsm = new PlayerDataSerealizationManager(); // create a instance of the class in charge to (JSON)serialize data
	
	//constructor
	public PlayerManager(Plugin plugin) {
		saveDir = new File(plugin.getDataFolder(), "/playerData/"); // attribute the saving path a folder
	}	
	
	// function to add a new player to map(for when a player connect for the first time)
	public static boolean addPlayerToList(PlayerData pd) {
		UUID playerID = pd.getPlayerID(); //get the id of the player
		pl.put(playerID, pd); //add the playerData given in arg to the map
		players.add(pd); //add the playerData to the list (only for this class purpose)
		
		return true; //return that everything happened find
	}
	
	// function to get a player from the Map pl
	public static PlayerData getPlayerFromList(UUID playerUUID) { // take player id in arg
		PlayerData playerData = pl.get(playerUUID); // create the instance of playerData that will hold data
		return playerData; //return it
	}
	
	//function to save a specific player in file
	private static boolean savePlayer(UUID pId, PlayerData pd) {
		File f = new File(saveDir, pId + ".json"); // create object of save file
		final String json = pdsm.serialize(pd); // serialize PlayerData to json syntaxe
		FileUtils.save(f, json); // saveing data in json syntaxe in the right file
		return true;
	}
	
	//function to save all player
	public static boolean savePlayers() {
		//always use try catch when dealing with files
		try {
			pl.forEach((pId, pd) -> savePlayer(pId, pd)); //for each data in pl, saving it using savePlayer
		}
		catch (Exception e){
			return false; // return that something went wrong
		}
		
		return true; //return that everything is good
	}
	
	//function to load players from files (when the plugin starts)
	public static boolean loadPlayers() {
		
		File[] files = saveDir.listFiles(); // get a list of every file in the playerData save folder
		
		if (!files.equals(null)) { //check if their is actually files to load
			for (File file : files) { //do the following actions for each files
				String json = FileUtils.loadContent(file); //read data stored in file
				PlayerData pd = pdsm.deserialise(json); //convert data readed into PlayerData object
				pl.put(pd.getPlayerID(), pd); //add converted data to the Map pl 
			}
			
			return true; //return that every thing happened well
		}
		return false; //return that something went wrong
		
	}
	
	//function to get PlayerData only using player name (to use if realy needed)
	public static PlayerData getPlayerByName(String playerName) {
		players.clear(); //clearing array list from older operation
		PlayerData playerData;
		
		pl.forEach((pId, pd) -> players.add(pd)); //putting in array list all the loading playerData
				
		//scan all playerData to get the right name, and return it
		for (int i=0; i<players.size(); i=i+1) {
			playerData = players.get(i);
			if (playerData.getPlayerName().equals(playerName)) {
				return playerData;
			}
		}
		return null;
	}
	
}
