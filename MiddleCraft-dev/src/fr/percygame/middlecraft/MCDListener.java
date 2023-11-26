package fr.percygame.middlecraft;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import fr.percygame.middlecraft.books.CodexXunia;
import fr.percygame.middlecraft.ressources.Silver;

public class MCDListener implements Listener {
	
	Random r = new Random();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.getInventory().addItem(CodexXunia.createCodexXunia());
		
		// affichage d'un titre au joueur lors de la connection
		p.sendTitle("§6MiddleCraft", "§9...Where history begin...", 10, 100, 5);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDER_EYE_DEATH, 1f, 1f);
	}
	
	@EventHandler
	public void onMineIronOre(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if (p.getGameMode() == GameMode.SURVIVAL)	{			
			if(e.getBlock().getType().equals(Material.IRON_ORE)) {
				
				if (!p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
					int n = r.nextInt(10);
					if (n == 0) {
						p.getWorld().dropItem(e.getBlock().getLocation(), Silver.createRawSilver());
					}
				}
				else {
					return;
				}
			}
		}
	}	
}
