package fr.percygame.middlecraft.sword;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import fr.percygame.middlecraft.ressources.Steel;

public class SteelSword{

	public static ItemStack createSteelSword(){

		ItemStack steelSword = new ItemStack(Material.IRON_SWORD);
		ItemMeta steelSwordMeta = steelSword.getItemMeta();
		steelSwordMeta.setDisplayName(ChatColor.BLUE + "Steel Sword");
		steelSwordMeta.setCustomModelData(1);
		steelSwordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		steelSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 11, true);
		steelSwordMeta.addEnchant(Enchantment.DURABILITY, 5, true);
		
		
		steelSword.setItemMeta(steelSwordMeta);

		return steelSword;
	}

	public static boolean createSteelSwordCraft(Plugin plugin){
		ShapedRecipe steelSwordRecipe = new ShapedRecipe(new NamespacedKey(plugin, "steelSword"), SteelSword.createSteelSword());
		steelSwordRecipe.shape(" s ", " s ", " b ");
		steelSwordRecipe.setIngredient('s', new RecipeChoice.ExactChoice(Steel.createSteelIngot()));
		steelSwordRecipe.setIngredient('b', Material.STICK);

		Bukkit.addRecipe(steelSwordRecipe);
		
		return true;
	}

}