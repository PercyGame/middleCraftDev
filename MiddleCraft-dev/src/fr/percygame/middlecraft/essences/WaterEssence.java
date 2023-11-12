package fr.percygame.middlecraft.essences;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class WaterEssence {
	
	public static ItemStack createWaterEssence(){
		ItemStack waterEssence = new ItemStack(Material.ECHO_SHARD);
		ItemMeta waterEssenceMeta = waterEssence.getItemMeta();
		waterEssenceMeta.setDisplayName(ChatColor.WHITE + "Water Essence");
		waterEssenceMeta.setCustomModelData(3);
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_PURPLE + "Concentrated water essence");
		waterEssenceMeta.setLore(lore);
		
		waterEssence.setItemMeta(waterEssenceMeta);
		
		return waterEssence;
	}
	
	public static boolean createWaterEssenceCraft(Plugin plugin) {
		
		ShapedRecipe waterEssenceRecipe = new ShapedRecipe(new NamespacedKey(plugin, "waterEssence"), WaterEssence.createWaterEssence());
		waterEssenceRecipe.shape(" c ", "wnw", "bbb");
		waterEssenceRecipe.setIngredient('c', new RecipeChoice.ExactChoice(Catalyst.createCatalyst()));
		waterEssenceRecipe.setIngredient('w', Material.WATER_BUCKET);
		waterEssenceRecipe.setIngredient('n', new RecipeChoice.ExactChoice(NeutralEssence.createNeutralEssence()));
		waterEssenceRecipe.setIngredient('b', Material.BLUE_ICE);
		
		Bukkit.addRecipe(waterEssenceRecipe);
		
		
		return true;
	}
	
}
