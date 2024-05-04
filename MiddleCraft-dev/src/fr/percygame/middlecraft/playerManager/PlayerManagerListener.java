package fr.percygame.middlecraft.playerManager;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.town.chunkManager.ChunkManager;

public class PlayerManagerListener implements Listener{

	private Player p;

	@EventHandler 
	public void onJoin(PlayerJoinEvent e) {
		p = e.getPlayer();
		if (!p.hasPlayedBefore()) {
			List<String> unaccessbileChunks = ChunkManager.getChunksIDsFromHaskMap(Main.claimedChunks); //to add all the claimed chunk to the unaccisble chunk lis of the new player (to test)
			PlayerData playerData = new PlayerData(p.getName(), p.getUniqueId(), Rank.WILD_MAN, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), 50, unaccessbileChunks, Grade.PLAYER, 0, 128);
			TempPlayerData tempPlayerData = new TempPlayerData(p.getUniqueId(), 128, null, null);
			PlayerManager.addPlayerToList(playerData, tempPlayerData);
		}
        
		PlayerScoreboard.createScoreboard(p);
        PlayerData pd = Main.players.get(p.getUniqueId());
        if (!p.getName().equals(pd.getPlayerName())) { // check if the player change his pseudo since the last connection
            pd.setPlayerName(p.getName());
        }
        
        TempPlayerData tpd = Main.tempPlayerData.get(p.getUniqueId());
        
        
        // check if the player that just connect has message in his mailbox
        if(tpd.getUnreadMSG().size()==0) { //triggered if there is no messages
        	p.sendMessage("You don't have any message to read.");
        }
        else { //triggered if there are messages
        	p.sendMessage("You have " + tpd.getUnreadMSG().size() + " new messages."); // send the player an info whit the nomber of messages to read
        	
        	// for each messages to read
        	for(mailBoxMessage i : tpd.getUnreadMSG()) {
        		p.sendMessage("<From " + Bukkit.getPlayer(i.getSender()).getName() + "> :");//send the sender name
        		p.sendMessage(ChatColor.DARK_BLUE + i.getContent()); //send the message itself
        		tpd.getUnreadMSG().remove(i); //may cause problem, feature to be checked
        	}
        }
	}
	
}
