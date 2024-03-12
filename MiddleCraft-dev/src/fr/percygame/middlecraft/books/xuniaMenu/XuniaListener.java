package fr.percygame.middlecraft.books.xuniaMenu;

import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.books.CodexXunia;

public class XuniaListener implements Listener {
	
	static Map<String, Inventory> menus = Main.menus;
	
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
				p.openInventory(menus.get("XuniaMainMenu"));
			}
		}
	}
	
	@EventHandler
	public void onCodexXuniaMainMenuClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		
		if (inv.equals(menus.get("XuniaMainMenu"))) {
			if (e.getSlot() == XuniaMainMenu.craftButton) {
				// ouvrir le menu des crafts n�1
				p.openInventory(menus.get("XuniaCraftMenu1"));
			}
		}
		
		if (inv.equals(menus.get("XuniaCraftMenu1"))) {
			if (e.getSlot() == XuniaCraftMenu1.returnButton) {
				p.openInventory(menus.get("XuniaMainMenu"));
			}
		}
	}
	
	
}
