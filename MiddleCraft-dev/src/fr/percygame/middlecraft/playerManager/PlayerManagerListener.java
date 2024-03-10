package fr.percygame.middlecraft.playerManager;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.percygame.middlecraft.Main;

public class PlayerManagerListener implements Listener{

	@EventHandler 
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPlayedBefore()) {
			PlayerData playerData = new PlayerData(p.getName(), p.getUniqueId(), Rank.WILD_MAN, "Wilderness", 50, new ArrayList<String>(), Grade.PLAYER, 0, 128);
			PlayerManager.addPlayerToList(playerData);
			System.out.println(Main.players.toString());
		}
        
		PlayerScoreboard.createScoreboard(p);
        PlayerData pd = Main.players.get(p.getUniqueId());
        if (!p.getName().equals(pd.getPlayerName())) { // check if the player change his pseudo since the last connection
            pd.setPlayerName(p.getName());
        }
	}
	
}
