package fr.percygame.middlecraft.essences;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;


public class Catalyst {
	
	public static ItemStack createCatalyst() {
		ItemStack catalyst= new ItemStack(Material.AMETHYST_SHARD);
		ItemMeta catalystMeta = catalyst.getItemMeta();
		catalystMeta.setDisplayName(ChatColor.WHITE + "Catalyst");
		catalystMeta.setCustomModelData(1);
		
		catalyst.setItemMeta(catalystMeta);
		
		return catalyst;
		
	}
	
	public static boolean createCatalystCraft(Plugin plugin) {
		ShapedRecipe catalystRecipe = new ShapedRecipe(new NamespacedKey(plugin, "catalyst"), Catalyst.createCatalyst());
		catalystRecipe.shape("ggg", "gag", "ggg");
		catalystRecipe.setIngredient('g', Material.GLASS);
		catalystRecipe.setIngredient('a', Material.AMETHYST_SHARD);
		
		Bukkit.addRecipe(catalystRecipe);
		
		return true;
	}

}
