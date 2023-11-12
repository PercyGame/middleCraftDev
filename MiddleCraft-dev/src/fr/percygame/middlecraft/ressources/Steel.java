package fr.percygame.middlecraft.ressources;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Steel {
	
	public static ItemStack createSteelIngot() {
		ItemStack steelIngot = new ItemStack(Material.IRON_INGOT);
		ItemMeta steelIngotMeta = steelIngot.getItemMeta();
		steelIngotMeta.setDisplayName("Steel Ingot");
		steelIngotMeta.setCustomModelData(1);
		
		steelIngot.setItemMeta(steelIngotMeta);
		
		return steelIngot;
	}
	
	public static ItemStack createSteelNugget() {
		ItemStack steelNugget = new ItemStack(Material.IRON_NUGGET);
		ItemMeta steelNuggetMeta = steelNugget.getItemMeta();
		steelNuggetMeta.setDisplayName("Steel Nugget");
		
		steelNugget.setItemMeta(steelNuggetMeta);
		
		return steelNugget;
	}
	
	public static boolean createSteelIngotCraft(Plugin plugin) {
		FurnaceRecipe steelIngotRecipe = new FurnaceRecipe(new NamespacedKey(plugin, "steelIngot"), Steel.createSteelIngot(), new RecipeChoice.ExactChoice(NetherSteel.createNetherSteelIngot()), 100f, 600);
		
		Bukkit.addRecipe(steelIngotRecipe);
		
		ShapedRecipe steelIngotRecipe2 = new ShapedRecipe(new NamespacedKey(plugin, "steelIngot2"), Steel.createSteelIngot());
		steelIngotRecipe2.shape("nnn", "nnn", "nnn");
		steelIngotRecipe2.setIngredient('n', new RecipeChoice.ExactChoice(Steel.createSteelNugget()));
		
		Bukkit.addRecipe(steelIngotRecipe2);
		
		
		return true;
	}
	
	public static boolean createSteelNuggetCraft(Plugin plugin) {
		ShapelessRecipe steelNuggetRecipe = new ShapelessRecipe(new NamespacedKey(plugin, "steelNugget"), Steel.createSteelNugget());
		steelNuggetRecipe.addIngredient(new RecipeChoice.ExactChoice(Steel.createSteelIngot()));
		
		
		return true;
	}
	

}
