package fr.percygame.middlecraft.playerManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerManagerListener implements Listener{

	@EventHandler 
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPlayedBefore()) {
			//créer un nouveau fichier pour le joueur
		}
	}
	
}
