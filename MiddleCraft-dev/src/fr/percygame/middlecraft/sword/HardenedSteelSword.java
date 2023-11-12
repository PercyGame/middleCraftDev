package fr.percygame.middlecraft.sword;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class HardenedSteelSword {


	public static ItemStack createHardenedSteelSword() {
		ItemStack hardenedSteelSword = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta hardenedSteelSwordMeta = hardenedSteelSword.getItemMeta();
		hardenedSteelSwordMeta.setDisplayName(ChatColor. GOLD + "Hardened Steel Sword");
		hardenedSteelSwordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		hardenedSteelSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 23, true);
		hardenedSteelSwordMeta.addEnchant(Enchantment.KNOCKBACK, 6, true);
		hardenedSteelSwordMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 4, true);
		hardenedSteelSwordMeta.addEnchant(Enchantment.SWEEPING_EDGE, 4, true);
		hardenedSteelSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, 14, true);
		hardenedSteelSwordMeta.setUnbreakable(true);
		hardenedSteelSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		
		hardenedSteelSwordMeta.setCustomModelData(1);
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_PURPLE + "Masterfully forged, this sword");
		lore.add(ChatColor.DARK_PURPLE + "will serve the brave in battle.");
		hardenedSteelSwordMeta.setLore(lore);

		hardenedSteelSword.setItemMeta(hardenedSteelSwordMeta);

		return hardenedSteelSword;
	}

	public static boolean createHardenedSteelSwordCraft(Plugin plugin) {
		BlastingRecipe hardenedSteelSwordRecipe = new BlastingRecipe(new NamespacedKey(plugin, "hardenedSteelSword"), HardenedSteelSword.createHardenedSteelSword(), new RecipeChoice.ExactChoice(SteelSword.createSteelSword()), 500f, 1200);

		Bukkit.addRecipe(hardenedSteelSwordRecipe);
		
		return true;
	}
}