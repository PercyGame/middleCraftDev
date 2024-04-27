package fr.percygame.middlecraft.playerManager;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.percygame.middlecraft.Main;

public class PlayerManagerListener implements Listener{

	private Player p;

	@EventHandler 
	public void onJoin(PlayerJoinEvent e) {
		p = e.getPlayer();
		if (!p.hasPlayedBefore()) {
			//
			//
			//think to add all the existing claimed chunk to the list of unacessible chunks /!\
			//
			//
			PlayerData playerData = new PlayerData(p.getName(), p.getUniqueId(), Rank.WILD_MAN, UUID.fromString("Wilderness"), 50, new ArrayList<String>(), Grade.PLAYER, 0, 128);
			TempPlayerData tempPlayerData = new TempPlayerData(p.getUniqueId(), 128, null, null);
			PlayerManager.addPlayerToList(playerData, tempPlayerData);
		}
        
		PlayerScoreboard.createScoreboard(p);
        PlayerData pd = Main.players.get(p.getUniqueId());
        if (!p.getName().equals(pd.getPlayerName())) { // check if the player change his pseudo since the last connection
            pd.setPlayerName(p.getName());
        }
        
        TempPlayerData tpd = Main.tempPlayerData.get(p);
        
        if(tpd.getUnreadMSG().size()==0) {
        	p.sendMessage("You don't have any message to read.");
        }
        else {
        	p.sendMessage("You have " + tpd.getUnreadMSG().size() + " new messages.");
        	
        	for(mailBoxMessage i : tpd.getUnreadMSG()) {
        		p.sendMessage("<From " + Bukkit.getPlayer(i.getSender()).getName() + "> :");
        		p.sendMessage(ChatColor.DARK_BLUE + i.getContent());
        		tpd.getUnreadMSG().remove(i); //may cause problem, feature to be checked
        	}
        }
	}
	
}
