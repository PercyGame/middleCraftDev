package fr.percygame.middlecraft.town;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;

public class claimInteractListener implements Listener {
	
	Map<UUID, PlayerData> pl = Main.players;
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			Player p = e.getPlayer();
			PlayerData pd = pl.get(p.getUniqueId());
			List<String> chunks = pd.getUnaccessibleChunckID();
			Chunk loc = e.getClickedBlock().getChunk();
			String chunkId = loc.getX() + "." + loc.getZ();
			
			if(chunks.contains(chunkId)) {
				e.setCancelled(true);
			}	
		}
		
		
	}
	
}
