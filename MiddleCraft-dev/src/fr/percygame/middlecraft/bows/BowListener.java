package fr.percygame.middlecraft.bows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
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
	private static List<Block> blocksInSphere = new ArrayList<>();
	
	
	public void createSphere(Location loc, Location newLoc) {
		for (int i=-1; i<=1; i++) {
			newLoc = new Location(loc.getWorld(), loc.getX()+3, loc.getY()+i, loc.getZ());
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+3, loc.getY()+i, loc.getZ()+1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+3, loc.getY()+i, loc.getZ()-1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+2, loc.getY()+i, loc.getZ()+2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+2, loc.getY()+i, loc.getZ()-2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-2, loc.getY()+i, loc.getZ()+2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-2, loc.getY()+i, loc.getZ()-2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-3, loc.getY()+i, loc.getZ()+1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-3, loc.getY()+i, loc.getZ()-1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+1, loc.getY()+i, loc.getZ()+3);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-1, loc.getY()+i, loc.getZ()+3);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-3, loc.getY()+i, loc.getZ());
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+i, loc.getZ()-3);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+i, loc.getZ()+3);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+1, loc.getY()+i, loc.getZ()-3);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-1, loc.getY()+i, loc.getZ()-3);
			blocksInSphere.add(newLoc.getBlock());
		}
		
		for (int i=-2; i<=2; i+=4) {
			newLoc = new Location(loc.getWorld(), loc.getX()+2, loc.getY()+i, loc.getZ());
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+2, loc.getY()+i, loc.getZ()+1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+2, loc.getY()+i, loc.getZ()-1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-2, loc.getY()+i, loc.getZ());
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-2, loc.getY()+i, loc.getZ()+1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-2, loc.getY()+i, loc.getZ()-1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+i, loc.getZ()+2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+1, loc.getY()+i, loc.getZ()+2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-1, loc.getY()+i, loc.getZ()+2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+i, loc.getZ()-2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+1, loc.getY()+i, loc.getZ()-2);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-1, loc.getY()+i, loc.getZ()-2);
			blocksInSphere.add(newLoc.getBlock());
		}
		
		for (int i=-3; i<=3; i+=6) {
			newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+i, loc.getZ());
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+1, loc.getY()+i, loc.getZ());
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+1, loc.getY()+i, loc.getZ()+1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()+1, loc.getY()+i, loc.getZ()-1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-1, loc.getY()+i, loc.getZ());
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-1, loc.getY()+i, loc.getZ()+1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX()-1, loc.getY()+i, loc.getZ()-1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+i, loc.getZ()+1);
			blocksInSphere.add(newLoc.getBlock());
			newLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+i, loc.getZ()-1);
			blocksInSphere.add(newLoc.getBlock());
		}
	}
	
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (e.getEntity().getShooter() instanceof Player) { //avoid triggering event if a skeleton (or others) shoot
			Player shooter = (Player) e.getEntity().getShooter(); //get the shooter (a player)
			if (e.getEntity().getType() == EntityType.ARROW) { //check if the projectile was an arrow
				Projectile arrow = e.getEntity();
				
				//if the arrow come from a brokilon bow
				if(arrows.get(arrow.getUniqueId()) == ArrowsEffects.BROKILON) {
					// put here code to execute when an brokilon bow shooted arrow land
					if (e.getHitEntity() != null && e.getEntity() instanceof Player) {
						shooter.playSound(shooter.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
						Player target = (Player) e.getHitEntity();
						target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
						target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 4));
						target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 4));
						target.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0));
					}
					
					
					else if (e.getHitEntity() != null) {
						Entity target = e.getEntity();
						shooter.playSound(shooter.getLocation(), Sound.ENTITY_BREEZE_HURT, 1f, 1f);
						target.teleport(target.getLocation().add(0, 10, 0));
					}
					
					else if (e.getHitBlock() != null) {
						shooter.playSound(shooter.getLocation(), Sound.BLOCK_VINE_BREAK, 1f, 1f);
						Block target = e.getHitBlock();
						Location loc = target.getLocation();
						Location newLoc = null;
						
						createSphere(loc, newLoc);
						
						for (Block block : blocksInSphere) {
							if (block.getType() == Material.AIR) {
								block.setType(Material.OAK_LEAVES);
							}
						}
						blocksInSphere.clear();
						arrows.remove(arrow.getUniqueId());
						
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
