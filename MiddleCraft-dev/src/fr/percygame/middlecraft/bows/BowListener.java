package fr.percygame.middlecraft.bows;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class BowListener implements Listener {
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (e.getEntity().getShooter() instanceof Player) { //avoid triggering event if a skeleton (or others) shoot
			Player shooter = (Player) e.getEntity().getShooter(); //get the shooter (a player)
			if (e.getEntity().getType() == EntityType.ARROW) { //check if the projectile was an arrow
				ItemStack bow = shooter.getInventory().getItemInMainHand(); //get the bow used (carful, this may contain other itemstack than bows
				//declaration of all bows
				ItemStack brokilonBow = BrokilonBow.createBrokilonBow();
				//...
				
				// handeling brokilon Bow actions
				if (bow.getItemMeta().equals(brokilonBow.getItemMeta())) {
					//insert code about what to do
				}
				
				//handeling ...
				//if (...)
			}
			
		}
	}

}
