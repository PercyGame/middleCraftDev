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

public class FireEssence {
	
	public static ItemStack createFireEssence(){
		ItemStack fireEssence = new ItemStack(Material.ECHO_SHARD);
		ItemMeta fireEssenceMeta = fireEssence.getItemMeta();
		fireEssenceMeta.setDisplayName(ChatColor.WHITE + "Fire Essence");
		fireEssenceMeta.setCustomModelData(2);
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_PURPLE + "Concentrated fire essence");
		fireEssenceMeta.setLore(lore);
		
		fireEssence.setItemMeta(fireEssenceMeta);
		
		return fireEssence;
	}
	
	public static boolean createFireEssenceCraft(Plugin plugin) {
		ShapedRecipe fireEssenceRecipe = new ShapedRecipe(new NamespacedKey(plugin, "fireEssence"), FireEssence.createFireEssence());
		fireEssenceRecipe.shape(" c ", "fnf", "NNN");
		fireEssenceRecipe.setIngredient('c', new RecipeChoice.ExactChoice(Catalyst.createCatalyst()));
		fireEssenceRecipe.setIngredient('f', Material.FLINT_AND_STEEL);
		fireEssenceRecipe.setIngredient('n', new RecipeChoice.ExactChoice(NeutralEssence.createNeutralEssence()));
		fireEssenceRecipe.setIngredient('N', Material.NETHERRACK);
		
		Bukkit.addRecipe(fireEssenceRecipe);
		
		
		return true;
		
	}

}
