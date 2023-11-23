package fr.percygame.middlecraft.books;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CodexXunia {
	
	public static ItemStack createCodexXunia(){
		ItemStack codexXunia = new ItemStack(Material.BOOK);
		ItemMeta codexXuniaMeta = codexXunia.getItemMeta();
		codexXuniaMeta.setCustomModelData(1);
		codexXuniaMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Codex Xunia");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Written by Ovyrine, this ragged parchment");
		lore.add("will guide you and give you all");
		lore.add("the information you need.");
		codexXuniaMeta.setLore(lore);
		
		codexXunia.setItemMeta(codexXuniaMeta);
		
		return codexXunia;
	}
	
	public static Inventory createCodexXuniaMenu() {
		Inventory i = Bukkit.createInventory(null, 45, ChatColor.GOLD + "Codex Xunia");
		
		ItemStack glass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		ItemStack craft = new ItemStack(Material.CRAFTING_TABLE);
		ItemMeta craftMeta = craft.getItemMeta();
		craftMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "See usful Craft");
		craft.setItemMeta(craftMeta);
		
		i.setItem(0, glass);
		i.setItem(1, glass);
		i.setItem(2, glass);
		i.setItem(3, glass);
		i.setItem(4, glass);
		i.setItem(5, glass);
		i.setItem(6, glass);
		i.setItem(7, glass);
		i.setItem(8, glass);

		i.setItem(36, glass);
		i.setItem(37, glass);
		i.setItem(38, glass);
		i.setItem(39, glass);
		i.setItem(40, glass);
		i.setItem(41, glass);
		i.setItem(42, glass);
		i.setItem(43, glass);
		i.setItem(44, glass);
		
		//co des items possibles : 10 12 14 16
		//						   28 30 32 34
		
		i.setItem(10, craft);
		
		return i;		
	}
}
