package fr.percygame.middlecraft.books.xuniaMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class XuniaCraftMenu1 {
	
	static int returnButton = 0;
	
	public static Inventory createXuniaCraftMenu1() {
		String invTitle = "Craft";
		Inventory i = Bukkit.createInventory(null, 45, ChatColor.GOLD + invTitle);
		
		i.setItem(returnButton, createReturnButton());
		
		
		
		return i;
	}
	
	
	public static ItemStack createReturnButton() {
		ItemStack returnB = new ItemStack(Material.ARROW);
		ItemMeta returnBMeta = returnB.getItemMeta();
		returnBMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Back");
		returnB.setItemMeta(returnBMeta);
		return returnB;
	}
	
}
