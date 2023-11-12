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


public class NeutralEssence {
	
	public static ItemStack createNeutralEssence(){
		ItemStack neutralEssence = new ItemStack(Material.ECHO_SHARD);
		ItemMeta neutralEssenceMeta = neutralEssence.getItemMeta();
		neutralEssenceMeta.setDisplayName(ChatColor.WHITE + "Neutral Essence");
		neutralEssenceMeta.setCustomModelData(1);
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_PURPLE + "Pure neutral essence");
		lore.add(ChatColor.DARK_PURPLE + "Can be used to summon other essences");
		neutralEssenceMeta.setLore(lore);
		
		neutralEssence.setItemMeta(neutralEssenceMeta);
		
		return neutralEssence;
	}
	
	
	public static boolean createNeutralEssenceCraft(Plugin plugin) {
		
		ShapedRecipe neutralEssenceRecipe = new ShapedRecipe(new NamespacedKey(plugin, "neutralEssence"), NeutralEssence.createNeutralEssence());
		neutralEssenceRecipe.shape(" c ", "cqc", " c ");
		neutralEssenceRecipe.setIngredient('c', new RecipeChoice.ExactChoice(Catalyst.createCatalyst()));
		neutralEssenceRecipe.setIngredient('q', Material.QUARTZ_BLOCK);
		
		Bukkit.addRecipe(neutralEssenceRecipe);
		
		
		return true;
	}

}
