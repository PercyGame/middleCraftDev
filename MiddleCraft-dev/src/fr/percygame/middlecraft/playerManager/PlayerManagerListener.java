package fr.percygame.middlecraft.playerManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.percygame.middlecraft.Main;

public class PlayerManagerListener implements Listener{
	
	static Map<UUID, PlayerData> pl = Main.players;

	@EventHandler 
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPlayedBefore()) {
			PlayerData playerData = new PlayerData(p.getDisplayName(), p.getUniqueId(), Rank.WILD_MAN, "Wilderness", 50, null, Grade.PLAYER);
			PlayerManager.addPlayerToList(playerData);
			System.out.println(Main.players.toString());
		}
        
        PlayerData pd = pl.get(p.getUniqueId());
        if (p.getDisplayName() != pd.getPlayerName()) { // check if the player change his pseudo since the last connection
            pd.setPlayerName(p.getDisplayName());
        }
	}
	
}
