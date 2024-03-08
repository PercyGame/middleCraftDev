package fr.percygame.middlecraft;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.percygame.middlecraft.books.BooksManager;
import fr.percygame.middlecraft.books.xuniaMenu.XuniaListener;
import fr.percygame.middlecraft.bows.BowListener;
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
		getServer().getPluginManager().registerEvents(new BowListener(), this);
		
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
		
		
		//to save data every X time
		new BukkitRunnable() {
			
			@Override
			public void run() {
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
		}.runTaskTimerAsynchronously(INSTANCE, 0, 20*60*10);// run saving data every 10min, using an other thread
		
		
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
