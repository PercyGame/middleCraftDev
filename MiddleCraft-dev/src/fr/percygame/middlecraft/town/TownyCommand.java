package fr.percygame.middlecraft.town;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;
import fr.percygame.middlecraft.town.chunkManager.ChunkData;
import fr.percygame.middlecraft.town.chunkManager.ChunkType;

public class TownyCommand implements CommandExecutor {
	

	static Map<String, TownData> t = Main.towns;

	@Override
	public boolean onCommand(CommandSender CS, Command command, String alias, String[] args) {
		
		
		if (CS instanceof Player) { //check if the sender is a player (not the console)
			String cmd = args[0];
			PlayerData senderPD = PlayerManager.getPlayerByName(CS.getName());
			Player sender = Bukkit.getPlayer(CS.getName());
			Chunk currentPlayerChunk = sender.getWorld().getChunkAt(sender.getLocation());
			
			String chunkID = currentPlayerChunk.getX() + "." + currentPlayerChunk.getZ();
			
			Map<String, ChunkData> chunks = new HashMap<>();
			
			
			
			if (cmd.equals("create")) {//check if player want to create a new town
				if (!args[1].isBlank()) { //check if the sender specified a name for his town
					if(senderPD.getPlayerTown().equals("Wilderness")) { //check if the player is already in a town
						if (!senderPD.getUnaccessibleChunckID().contains(chunkID)) {// check if the player can change things is his current chunk (btw if the chunk is already claimed)
							ChunkData chunk = new ChunkData(chunkID, args[1], ChunkType.COMMON); //create a new chunkData, for the original chunk of the new town
							chunks.put(chunkID, chunk); //add the new chunk to the new town chunk list
							TownData newTown = new TownData(args[1], sender.getUniqueId(), 9, chunks, TownRank.SETTLEMENT); // create the new tonw
							t.put(newTown.getTownName(), newTown); // add the new town to the town list
							senderPD.setPlayerTown(newTown.getTownName());
							PlayerManager.addUnaccessibleChunkToAllPlayers(chunkID, newTown.getTownName());
						}
					}
				}
				else {
					sender.sendMessage("You have to specified a name for your town");
				}
				
			}
			
			
			if (cmd.equals("claim")) {
				if (!senderPD.getPlayerTown().equals("Wilderness")) {
					if (t.get(senderPD.getPlayerName()).getOwnerID().equals(senderPD.getPlayerID())) { //check if the sender is the owner of his town
						//to code ...
					}
				}
			}
			
			
		}		
		return true;
	}

}
