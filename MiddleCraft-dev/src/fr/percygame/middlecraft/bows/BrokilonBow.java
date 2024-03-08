package fr.percygame.middlecraft.bows;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// the Brokilon Bow is a forest bow that stun ennemis when they got hit, or create some temporary foliage if the bow hit some block
public class BrokilonBow {
	
	public static ItemStack createBrokilonBow() {
		ItemStack brokilonBow = new ItemStack(Material.BOW);
		ItemMeta brokilonBowMeta = brokilonBow.getItemMeta();
		brokilonBowMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.DARK_GREEN + "Brokilon Bow");
		brokilonBowMeta.setUnbreakable(true);
		brokilonBow.setItemMeta(brokilonBowMeta);
		
		return brokilonBow;
	}
	
}
