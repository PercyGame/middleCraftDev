package fr.percygame.middlecraft;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.percygame.middlecraft.books.xuniaMenu.XuniaListener;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManagerListener;

public class Main extends JavaPlugin{
	
	Plugin INSTANCE;
	public static Map<UUID, PlayerData> players = new HashMap<>();
	
	public Main() {
		this.INSTANCE = this;
	}
	
	@Override
	public void onEnable() {
		System.out.println("MiddleCraft dev is starting...");
		getServer().getPluginManager().registerEvents(new MCDListener(), this);
		getServer().getPluginManager().registerEvents(new XuniaListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerManagerListener(), this);
		
		CustomItemsManagers.createCrafts(INSTANCE);
		
	}

}
