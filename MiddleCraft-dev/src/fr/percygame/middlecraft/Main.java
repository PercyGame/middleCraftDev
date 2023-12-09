package fr.percygame.middlecraft;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.percygame.middlecraft.books.BooksManager;
import fr.percygame.middlecraft.books.xuniaMenu.XuniaListener;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;
import fr.percygame.middlecraft.playerManager.PlayerManagerListener;
import fr.percygame.middlecraft.playerManager.economieManager.OrensGiveCommand;
import fr.percygame.middlecraft.playerManager.economieManager.OrensWithdrawCommand;
import fr.percygame.middlecraft.playerManager.economieManager.PayCommand;
import fr.percygame.middlecraft.town.TownData;
import fr.percygame.middlecraft.town.TownManager;
import fr.percygame.middlecraft.town.TownyCommand;
import fr.percygame.middlecraft.town.claimInteractListener;

public class Main extends JavaPlugin{
	
	Plugin INSTANCE;
	public static Map<UUID, PlayerData> players = new HashMap<>();
	public static Map<String, TownData> towns = new HashMap<>();
	public static Map<String, Inventory> menus = new HashMap<>();
	
	public Main() {
		this.INSTANCE = this;
	}
	
	@Override
	public void onEnable() {
		new PlayerManager(INSTANCE);
		new TownManager(INSTANCE);
		
		getServer().getPluginManager().registerEvents(new MCDListener(), this);
		getServer().getPluginManager().registerEvents(new XuniaListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerManagerListener(), this);
		getServer().getPluginManager().registerEvents(new claimInteractListener(), this);
		
		getCommand("pay").setExecutor(new PayCommand());
		getCommand("orens_give").setExecutor(new OrensGiveCommand());
		getCommand("orens_withdraw").setExecutor(new OrensWithdrawCommand());
		getCommand("t").setExecutor(new TownyCommand());
		
		CustomItemsManagers.createCrafts(INSTANCE);
		
		if (BooksManager.loadMenus()) {
			System.out.println("Menus loaded");
		}else {
			System.out.println("Error during loading of menus");
		}
		
		PlayerManager.loadPlayers();
		TownManager.loadTowns();
		
	}
	
	@Override
	public void onDisable() {
		if(PlayerManager.savePlayers()) { //saving players data, and checking if everything happened well
			System.out.println("Players data succefuly saved");
		}
		else {
			System.out.println("Error during players data saving");
		}
		
		if(TownManager.saveTowns()) {
			System.out.println("Town data succefuly saved");
		}
		else {
			System.out.println("Error during towns data saving");
		}
	}

}
