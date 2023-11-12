package fr.percygame.middlecraft;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	Plugin INSTANCE;
	
	public Main() {
		this.INSTANCE = this;
	}
	
	@Override
	public void onEnable() {
		System.out.println("MiddleCraft dev is starting...");
		getServer().getPluginManager().registerEvents(new MCDListener(), this);
		
		CustomItemsManagers.createCrafts(INSTANCE);
		
	}

}
