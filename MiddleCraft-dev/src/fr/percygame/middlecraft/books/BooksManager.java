package fr.percygame.middlecraft.books;

import java.util.Map;

import org.bukkit.inventory.Inventory;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.books.xuniaMenu.XuniaCraftMenu1;
import fr.percygame.middlecraft.books.xuniaMenu.XuniaMainMenu;

public class BooksManager {
	
	static Map<String, Inventory> menus = Main.menus;
	
	public static boolean loadMenus() {
		menus.put("XuniaMainMenu", XuniaMainMenu.createCodexXuniaMenu());
		menus.put("XuniaCraftMenu1", XuniaCraftMenu1.createXuniaCraftMenu1());
		
		return true;
	}
	
}
