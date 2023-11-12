package fr.percygame.middlecraft;

import org.bukkit.plugin.Plugin;

import fr.percygame.middlecraft.essences.Catalyst;
import fr.percygame.middlecraft.essences.FireEssence;
import fr.percygame.middlecraft.essences.NeutralEssence;
import fr.percygame.middlecraft.essences.WaterEssence;
import fr.percygame.middlecraft.potion.Blizzard;
import fr.percygame.middlecraft.ressources.NetherSteel;
import fr.percygame.middlecraft.ressources.Steel;
import fr.percygame.middlecraft.sword.HardenedSteelSword;
import fr.percygame.middlecraft.sword.SteelSword;

public class CustomItemsManagers {
	
	public static boolean createCrafts(Plugin plugin) {
		
		Blizzard.createBlizzardCraft(plugin);
		NetherSteel.createNetherSteelIngotCraft(plugin);
		Steel.createSteelIngotCraft(plugin);
		Steel.createSteelNuggetCraft(plugin);
		SteelSword.createSteelSwordCraft(plugin);
		HardenedSteelSword.createHardenedSteelSwordCraft(plugin);
		Catalyst.createCatalystCraft(plugin);
		NeutralEssence.createNeutralEssenceCraft(plugin);
		FireEssence.createFireEssenceCraft(plugin);
		WaterEssence.createWaterEssenceCraft(plugin);
		
		return true;
	}
	
}
