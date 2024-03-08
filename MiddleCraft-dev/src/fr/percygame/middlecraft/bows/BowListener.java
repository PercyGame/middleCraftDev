package fr.percygame.middlecraft.bows;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BowListener implements Listener {
	
	public static Map<UUID, ArrowsEffects> arrows = new HashMap<>();
	
	
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (e.getEntity().getShooter() instanceof Player) { //avoid triggering event if a skeleton (or others) shoot
			Player shooter = (Player) e.getEntity().getShooter(); //get the shooter (a player)
			if (e.getEntity().getType() == EntityType.ARROW) { //check if the projectile was an arrow
				Projectile arrow = e.getEntity();
				
				//if the arrow come from a brokilon bow
				if(arrows.get(arrow.getUniqueId()) == ArrowsEffects.BROKILON) {
					// put here code to execute when an brokilon bow shooted arrow land
					if (e.getHitEntity() != null) {
						Player target = (Player) e.getHitEntity();
						target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
						target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 4));
						target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 4));
						target.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0));
					}
					else if (e.getHitBlock() != null) {
						Block target = e.getHitBlock();
						shooter.sendMessage("YOU HAVE TO HANDLE THIS PART OF THE CODE");
						Location targetLoc = target.getLocation();
						System.out.println(targetLoc.getBlock().getType());
					}
				}
			}
			
		}
	}

	
	@EventHandler
	public void onArrowShoot(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player shooter = (Player) e.getEntity().getShooter();
			if (e.getEntity().getType() == EntityType.ARROW) {
				ItemStack bow = shooter.getInventory().getItemInMainHand(); //get the bow used (carful, this may contain other itemstack than bows
				//declaration of all bows
				ItemStack brokilonBow = BrokilonBow.createBrokilonBow();
				//...
				
				// handeling brokilon Bow actions
				if (bow.getItemMeta().equals(brokilonBow.getItemMeta())) {
					arrows.put(e.getEntity().getUniqueId(), ArrowsEffects.BROKILON);
				}
				
			}
		}
	}

}
