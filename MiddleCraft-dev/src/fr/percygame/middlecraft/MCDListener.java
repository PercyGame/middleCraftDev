package fr.percygame.middlecraft;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.percygame.middlecraft.books.CodexXunia;
import fr.percygame.middlecraft.ressources.Silver;

public class MCDListener implements Listener {
	
	Random r = new Random();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.getInventory().addItem(CodexXunia.createCodexXunia());
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
	
	
	@EventHandler
	public void onOpenCodexXunia(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if (action == null) {
			return;
		}
		
		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			if (item.equals(CodexXunia.createCodexXunia())) {
				//ouvrir le menu
				p.openInventory(CodexXunia.createCodexXuniaMenu());
			}
		}
	}
	
	@EventHandler
	public void onCodexXuniaMainMenuClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack clicked = e.getCurrentItem();
		
		if (inv.equals(CodexXunia.createCodexXuniaMenu())) {
			if (clicked.hasItemMeta() && clicked.getItemMeta().hasDisplayName() 
					&& clicked.getItemMeta().getDisplayName() == ChatColor.GREEN + 
					"" + ChatColor.BOLD + "See usful Craft") {
				p.sendMessage("Craft");
			}
		}
		
	}
	
	
	
}
