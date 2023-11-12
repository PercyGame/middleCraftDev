package fr.percygame.middlecraft.potion;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Blizzard {
	
	public static ItemStack createBlizzard() {
		
		ItemStack potion = new ItemStack(Material.POTION);
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Potion for witcher...");
		lore.add("Legend has it that it gives");
		lore.add("enhanced combat capabilities.");
		meta.setColor(Color.BLACK);
		meta.setDisplayName(ChatColor.GOLD + "Blizzard Potion");
		meta.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 3600, 0), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1800, 2), false);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 360, 4), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 12000, 0), true);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		meta.setLore(lore);
		
		potion.setItemMeta(meta);
		
		return potion;
	}
	
	public static boolean createBlizzardCraft(Plugin plugin) {
		ShapedRecipe blizzard = new ShapedRecipe(new NamespacedKey(plugin, "blizzard"), Blizzard.createBlizzard());
		blizzard.shape("pgp", "tmt", "fbf");
		blizzard.setIngredient('p', Material.POISONOUS_POTATO);
		blizzard.setIngredient('g', Material.GOLDEN_APPLE);
		
		ItemStack mundane = new ItemStack(Material.POTION);
		PotionMeta mundaneMeta = (PotionMeta) mundane.getItemMeta();
		mundaneMeta.setBasePotionType(PotionType.MUNDANE);
		mundane.setItemMeta(mundaneMeta);
		blizzard.setIngredient('m', new RecipeChoice.ExactChoice(mundane));
		
		ItemStack thick = new ItemStack(Material.POTION);
		PotionMeta thickMeta = (PotionMeta) thick.getItemMeta();
		thickMeta.setBasePotionType(PotionType.THICK);
		thick.setItemMeta(thickMeta);
		blizzard.setIngredient('t', new RecipeChoice.ExactChoice(thick));
		
		blizzard.setIngredient('b', Material.BLAZE_ROD);
		blizzard.setIngredient('f', Material.FIRE_CHARGE);
		
		Bukkit.addRecipe(blizzard);
		return true;
	}

}
