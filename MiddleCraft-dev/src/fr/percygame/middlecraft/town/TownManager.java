package fr.percygame.middlecraft.town;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.Plugin;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.utils.FileUtils;
import fr.percygame.middlecraft.utils.TownDataSerializationManager;

public class TownManager {
	

	static File saveDir; // create the path for saving files
	final static TownDataSerializationManager tdsm = new TownDataSerializationManager();
	
	//constructor
	public TownManager(Plugin plugin) {
		saveDir = new File(plugin.getDataFolder(), "/TownData/"); // attribute the saving path a folder
	}
	
	//function to save a specific Town in file
	private static boolean saveTown(UUID townId, TownData td) {
		File f = new File(saveDir, townId + ".json"); // create object of save file
		final String json = tdsm.serialize(td); // serialize TownData to json syntaxe
		FileUtils.save(f, json); // saveing data in json syntaxe in the right file
		return true;
	}
		
	//function to save all towns
	public static boolean saveTowns() {
		//always use try catch when dealing with files
		try {
			Main.towns.forEach((townId, td) -> saveTown(townId, td)); //for each data in t, saving it using saveTown
		}
		catch (Exception e){
			return false; // return that something went wrong
		}
			
		return true; //return that everything is good
	}
		
	//function to load Towns from files (when the plugin starts)
	public static boolean loadTowns() {
		
		try {
			File[] files = saveDir.listFiles(); // get a list of every file in the townData save folder
			if (!files.equals(null)) { //check if their is actually files to load
				for (File file : files) { //do the following actions for each files
					String json = FileUtils.loadContent(file); //read data stored in file
					TownData td = tdsm.deserialise(json); //convert data readed into TownData object
					Main.towns.put(td.getId(), td); //add converted data to the Map pl 
				}
					
				return true; //return that every thing happened well
			}
		} catch (Exception e) {
			return false; //return that something went wrong
		}
		
		return false; //return that something went wrong
		
	}
	
	public static UUID getTownByName(String townName) {
		// cycle throught the Main.towns HashMap, to check each ones names, and return the uuid, when it has find the matching one
		for(Map.Entry<UUID, TownData> mapEntry : Main.towns.entrySet()) {
			if (mapEntry.getValue().getTownName().equals(townName)) {
				return mapEntry.getKey();
			}
		}
		return null;
	}
	
}
