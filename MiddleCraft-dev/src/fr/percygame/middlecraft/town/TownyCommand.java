package fr.percygame.middlecraft.town;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;
import fr.percygame.middlecraft.playerManager.Rank;
import fr.percygame.middlecraft.playerManager.economieManager.TransactionManager;
import fr.percygame.middlecraft.town.chunkManager.ChunkData;
import fr.percygame.middlecraft.town.chunkManager.ChunkManager;
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
				if (!args[1].isBlank()) {//check if the sender specified a name for his town
					if (sender.getWorld().getName().equals("world")) {
						if(senderPD.getPlayerTown().equals("Wilderness")) { //check if the player is already in a town
							if (!senderPD.getUnaccessibleChunckID().contains(chunkID)) {// check if the player can change things is his current chunk (btw if the chunk is already claimed)
								ChunkData chunk = new ChunkData(chunkID, args[1], ChunkType.COMMON); //create a new chunkData, for the original chunk of the new town
								chunks.put(chunkID, chunk); //add the new chunk to the new town chunk list
								TownData newTown = new TownData(args[1], sender.getUniqueId(), 9, chunks, TownRank.SETTLEMENT); // create the new tonw
								if(TransactionManager.orensWithdraw(senderPD, 100, false)) {
									t.put(newTown.getTownName(), newTown); // add the new town to the town list
									senderPD.setPlayerTown(newTown.getTownName());
									senderPD.setPlayerRank(Rank.LORD);
									PlayerManager.addUnaccessibleChunkToAllPlayers(chunkID, newTown.getTownName());
									TownManager.saveTowns(); //save all towns in files
									PlayerManager.savePlayers();
									sender.sendMessage("Congratulation !");
									sender.sendMessage("You've just created " + ChatColor.AQUA + newTown.getTownName());
									sender.playSound(sender, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
								}
								else {
									sender.sendMessage(ChatColor.RED + "You need 100¤ to create a new town");
									sender.playSound(sender, Sound.ENTITY_STRIDER_STEP, 1f, 1f);
								}
								
							}
						}
					}
					
				}
				else {
					sender.sendMessage("You have to specified a name for your town");
				}
				
			}
			
			
			if (cmd.equals("claim")) {
				System.out.println(sender.getWorld().getName());
				if (sender.getWorld().getName().equals("world")) {
					if (!senderPD.getPlayerTown().equals("Wilderness")) {
						if (senderPD.getPlayerRank().equals(Rank.KING) || senderPD.getPlayerRank().equals(Rank.LORD) || senderPD.getPlayerRank().equals(Rank.OFFICIER)) { //check if the sender can claim for his town
							if (!senderPD.getUnaccessibleChunckID().contains(chunkID)) {
								TownData town = t.get(senderPD.getPlayerTown());
								ChunkData cd = ChunkManager.getChunk(chunkID);
								if(cd == null) { // check if the chunk isn't in a town
									if (town.getChunkLimit() > town.getChunks().size()) { //check if the town has hit it chunk limit
										ChunkData chunk = new ChunkData(chunkID, senderPD.getPlayerTown(), ChunkType.COMMON);
										if(ChunkManager.isChunkTouchingTown(chunk, town)) {
											int cost = (int) (100*Math.E*((town.getChunks().size()-10)/8));
											if(TransactionManager.orensWithdraw(senderPD, cost, false)) {
												town.addChunks(chunk);
												PlayerManager.addUnaccessibleChunkToAllPlayers(chunkID, town.getTownName());
												TownManager.saveTowns(); //save all towns in files
												PlayerManager.savePlayers();
											}
											else {
												sender.sendMessage("You need " + cost + "¤ to claim new chunks");
												sender.playSound(sender, Sound.ENTITY_STRIDER_STEP, 1f, 1f);
											}
											
										}
									}
									else {
										sender.sendMessage("Your town hit it claim limit.");
										sender.sendMessage("You can't claim new chunks now");
										sender.playSound(sender, Sound.ENTITY_STRIDER_STEP, 1f, 1f);
									}
									
								}
							}
						}
					}
				}
				
			}
			
			
		}		
		return true;
	}

}
