package fr.percygame.middlecraft.ressources;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Silver {
	
	public static ItemStack createRawSilver() {
		ItemStack rawSilver = new ItemStack(Material.IRON_NUGGET);
		ItemMeta rawSilverMeta = rawSilver.getItemMeta();
		rawSilverMeta.setDisplayName(ChatColor.WHITE + "Raw Silver");
		rawSilverMeta.setCustomModelData(1);
		
		rawSilver.setItemMeta(rawSilverMeta);
		
		return rawSilver;
	}
	
	public static ItemStack createSilverIngot() {
		ItemStack silverIngot = new ItemStack(Material.IRON_INGOT);
		ItemMeta silverIngotMeta = silverIngot.getItemMeta();
		silverIngotMeta.setDisplayName("Silver Ingot");
		silverIngotMeta.setCustomModelData(3);
		
		silverIngot.setItemMeta(silverIngotMeta);
		
		return silverIngot;
	}
	
	public static boolean crateSilverIngotRecipe(Plugin plugin) {
		FurnaceRecipe silverIngotRecipe = new FurnaceRecipe(new NamespacedKey(plugin, "silverIngot"), Silver.createSilverIngot(), new RecipeChoice.ExactChoice(Silver.createRawSilver()), 100f, 600);
		Bukkit.addRecipe(silverIngotRecipe);
		
		return true;
	}

}
