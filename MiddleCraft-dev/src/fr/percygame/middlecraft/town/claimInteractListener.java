package fr.percygame.middlecraft.town;

import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;

public class claimInteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			Player p = e.getPlayer();
			PlayerData pd = Main.players.get(p.getUniqueId());
			List<String> chunks = pd.getUnaccessibleChunckID();
			Chunk loc = e.getClickedBlock().getChunk();
			String chunkId = loc.getX() + "." + loc.getZ();
			
			chunks.forEach((id) -> System.out.println(id));
			System.out.println(chunkId);
			
			if(chunks.contains(chunkId)) {
				System.out.println("action locked");
				e.setCancelled(true);
			}	
		}
		
		
	}
	
}
