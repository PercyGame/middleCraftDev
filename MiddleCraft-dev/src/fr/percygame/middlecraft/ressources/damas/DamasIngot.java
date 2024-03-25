package fr.percygame.middlecraft.ressources.damas;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DamasIngot {
	
	public static ItemStack createDamasIngot(Player blacksmith) {
		ItemStack damasIngot = new ItemStack(Material.IRON_INGOT);
		ItemMeta damasIngotMeta = damasIngot.getItemMeta();
		damasIngotMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.DARK_GRAY + "Damas Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("Ingot forged with many hours of work");
		lore.add("Enables the manufacture of high-quality materials");
		lore.add("Blacksmith : <" + ChatColor.DARK_PURPLE + blacksmith.getDisplayName() + ChatColor.RESET + ">");
		damasIngotMeta.setLore(lore);
		return damasIngot;
	}

}
