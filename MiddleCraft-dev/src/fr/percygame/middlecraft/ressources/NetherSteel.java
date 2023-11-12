package fr.percygame.middlecraft.ressources;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class NetherSteel {
	
	public static ItemStack createNetherSteelIngot() {
		ItemStack netherSteelIngot = new ItemStack(Material.IRON_INGOT);
		ItemMeta netherSteelIngotMeta = netherSteelIngot.getItemMeta();
		netherSteelIngotMeta.setDisplayName("Nether Steel Ingot");
		netherSteelIngotMeta.setCustomModelData(2);
		
		
		netherSteelIngot.setItemMeta(netherSteelIngotMeta);
		
		return netherSteelIngot;
	}
	
	public static boolean createNetherSteelIngotCraft(Plugin plugin) {
		
		ShapedRecipe netherSteelIngotRecipe = new ShapedRecipe(new NamespacedKey(plugin, "netherSteelIngot"), NetherSteel.createNetherSteelIngot());
		netherSteelIngotRecipe.shape("ccc","nin","ccc");
		netherSteelIngotRecipe.setIngredient('c', Material.COAL_BLOCK);
		netherSteelIngotRecipe.setIngredient('n', Material.NETHERITE_INGOT);
		netherSteelIngotRecipe.setIngredient('i', Material.IRON_INGOT);
		
		Bukkit.addRecipe(netherSteelIngotRecipe);
		
		return true;
	}
	
}
