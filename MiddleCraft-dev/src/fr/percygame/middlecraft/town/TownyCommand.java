package fr.percygame.middlecraft.town;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import fr.percygame.middlecraft.playerManager.PlayerScoreboard;
import fr.percygame.middlecraft.playerManager.Rank;
import fr.percygame.middlecraft.playerManager.TempPlayerData;
import fr.percygame.middlecraft.playerManager.economieManager.TransactionManager;
import fr.percygame.middlecraft.town.chunkManager.ChunkData;
import fr.percygame.middlecraft.town.chunkManager.ChunkManager;
import fr.percygame.middlecraft.town.chunkManager.ChunkType;

public class TownyCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender CS, Command command, String alias, String[] args) {
		
		
		if (CS instanceof Player) { //check if the sender is a player (not the console)
			String cmd = args[0];
			PlayerData senderPD = PlayerManager.getPlayerByName(CS.getName());
			TempPlayerData senderTPD = Main.tempPlayerData.get(senderPD.getPlayerID());
			Player sender = Bukkit.getPlayer(CS.getName());
			Chunk currentPlayerChunk = sender.getWorld().getChunkAt(sender.getLocation());
			
			String chunkID = currentPlayerChunk.getX() + "." + currentPlayerChunk.getZ();
			
			Map<String, ChunkData> chunks = new HashMap<>();
						
			
			if (cmd.equals("create")) {//check if player want to create a new town
				if (!args[1].isBlank()) {//check if the sender specified a name for his town
					if (sender.getWorld().getName().equals("world")) {
						if(senderPD.getPlayerTown().equals(UUID.fromString("Wilderness"))) { //check if the player is already in a town
							if (!senderPD.getUnaccessibleChunckID().contains(chunkID)) {// check if the player can change things is his current chunk (btw if the chunk is already claimed)
								UUID newTownId = UUID.randomUUID(); // create here to be able to use it in the chunk data creation
								ChunkData chunk = new ChunkData(chunkID,newTownId, ChunkType.COMMON); //create a new chunkData, for the original chunk of the new town
								chunks.put(chunkID, chunk); //add the new chunk to the new town chunk list
								TownData newTown = new TownData(newTownId, args[1], sender.getUniqueId(), false, 9, chunks, TownRank.SETTLEMENT, 0); // create the new tonw
								if(TransactionManager.orensWithdraw(senderPD, 100, false)) {
									Main.towns.put(newTown.getId(), newTown); // add the new town to the town list
									senderPD.setPlayerTown(newTown.getId());
									senderPD.setPlayerRank(Rank.LORD);
									PlayerManager.addUnaccessibleChunkToAllPlayers(chunkID, newTown.getId());
									TownManager.saveTowns(); //save all towns in files
									PlayerManager.savePlayers();
									sender.sendMessage("Congratulation !");
									sender.sendMessage("You've just created " + ChatColor.AQUA + newTown.getTownName());
									sender.playSound(sender, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
									PlayerScoreboard.createScoreboard(sender);
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
					if (!senderPD.getPlayerTown().equals(UUID.fromString("Wilderness"))) {
						if (senderPD.getPlayerRank().equals(Rank.KING) || senderPD.getPlayerRank().equals(Rank.LORD) || senderPD.getPlayerRank().equals(Rank.OFFICIER)) { //check if the sender can claim for his town
							if (!senderPD.getUnaccessibleChunckID().contains(chunkID)) {
								TownData town = Main.towns.get(senderPD.getPlayerTown());
								ChunkData cd = ChunkManager.getChunk(chunkID);
								if(cd == null) { // check if the chunk isn't in a town
									if (town.getChunkLimit() > town.getChunks().size()) { //check if the town has hit it chunk limit
										ChunkData chunk = new ChunkData(chunkID, senderPD.getPlayerTown(), ChunkType.COMMON);
										if(ChunkManager.isChunkTouchingTown(chunk, town)) {
											int cost = (int) (100*Math.exp(town.getChunks().size()-10/8));
											if(TransactionManager.orensWithdraw(senderPD, cost, false)) {
												town.addChunks(chunk);
												PlayerManager.addUnaccessibleChunkToAllPlayers(chunkID, town.getId());
												TownManager.saveTowns(); //save all towns in files
												PlayerManager.savePlayers();
												sender.playSound(sender, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
												PlayerScoreboard.createScoreboard(sender);
												sender.sendMessage("[" + ChatColor.RED + "-" + ChatColor.RESET + "] " + cost + " ¤");
												sender.sendMessage("You have claimed " + cd.getChunkID());
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
			
			//untested
			if (cmd.equals("unclaim")) { //code to handle the unclaiming command
				if (sender.getWorld().getName().equals("world")) { // check if the player is in the overworld
					if (!senderPD.getPlayerTown().equals(UUID.fromString("Wilderness"))) { // check if the player have a town (Wilderness being default town
						if (senderPD.getPlayerRank().equals(Rank.KING) || senderPD.getPlayerRank().equals(Rank.LORD)) { // check if the sender is king or lord (officier can't unclaim)
							ChunkData cd = ChunkManager.getChunk(chunkID);
							if (cd != null) { //check if the chunk is in a town
								if (senderPD.getPlayerTown().equals(cd.getTown())) {
									// code to remove chunk from all player's unaccessible chunk list and delete chunkdata file
									PlayerManager.removeUnaccessibleChunkToAllPlayers(chunkID, senderPD.getPlayerTown());
									sender.sendMessage("You have unclaimed " + cd.getChunkID());
								}	
							}
							
						}
					}
				}
			}
			
			//unfinished (message sending to finish, add a massage on mailbox if the player is offline)
			if (cmd.equals("invite")) {
				UUID senderTownId = senderPD.getPlayerTown();
				String senderTownName = Main.towns.get(senderTownId).getTownName();
				
				try {
					Player target = Bukkit.getPlayer(args[1]);
					Main.tempPlayerData.get(target.getUniqueId()).setTownInvite(senderTownId);
					target.sendMessage("You've got an town joining invitation from the " + Main.towns.get(senderTownId).getTownRank().toString() + " " + senderTownName + " !");
					target.sendMessage("do /t invite-accept, or /t invite-dismiss");
				} catch (Exception e) {
					return false;
				}
			}
			
			//unfinished
			if (cmd.equals("invite-accept")) {
				if (senderPD.getPlayerTown().equals(UUID.fromString("Wilderness"))) { // check if the sender is already in a town
					senderPD.setPlayerTown(senderTPD.getTownInvite());
					//remove all unaccesible chunk from the town to the sender
				}
			}
			
			//unfinished
			if (cmd.equals("invite-dismiss")) {
				if(senderTPD.getTownInvite()!=null) {
					Player owner = Bukkit.getPlayer(Main.towns.get(senderTPD.getTownInvite()).getOwnerID()); //get the player instance of the town owner, to notify him that the sender refuse his invitation
					senderTPD.setTownInvite(null); //erase the town invitation, do delete it
					owner.sendMessage(senderPD.getPlayerName() + " has reject your invitation :(");
					
				}
				else {
					sender.sendMessage("You do not have any invitation running :(");
				}
			}
			
			//unfinished
			if (cmd.equals("")) {
				
			}
			
			
		}		
		return true;
	}

}
