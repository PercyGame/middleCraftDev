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
}
