package fr.percygame.middlecraft;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.percygame.middlecraft.books.xuniaMenu.XuniaListener;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;
import fr.percygame.middlecraft.playerManager.PlayerManagerListener;
import fr.percygame.middlecraft.playerManager.economieManager.OrensGiveCommand;
import fr.percygame.middlecraft.playerManager.economieManager.PayCommand;

public class Main extends JavaPlugin{
	
	Plugin INSTANCE;
	public static Map<UUID, PlayerData> players = new HashMap<>();
	
	public Main() {
		this.INSTANCE = this;
	}
	
	@Override
	public void onEnable() {
		System.out.println("MiddleCraft dev is starting...");
		new PlayerManager(INSTANCE);
		
		getServer().getPluginManager().registerEvents(new MCDListener(), this);
		getServer().getPluginManager().registerEvents(new XuniaListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerManagerListener(), this);
		
		getCommand("pay").setExecutor(new PayCommand());
		getCommand("orens_give").setExecutor(new OrensGiveCommand());
		
		CustomItemsManagers.createCrafts(INSTANCE);
		
		PlayerManager.loadPlayers();
		
	}
	
	@Override
	public void onDisable() {
		if(PlayerManager.savePlayers()) {
			System.out.println("Players data succefuly saved");
		}
		else {
			System.out.println("Error during players data saving");
		}
	}

}
