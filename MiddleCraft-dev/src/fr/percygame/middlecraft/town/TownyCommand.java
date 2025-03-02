package fr.percygame.middlecraft.town;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
						if(senderPD.getPlayerTown().equals(UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"))) { //check if the player is already in a town
							if (!senderPD.getUnaccessibleChunckID().contains(chunkID)) {// check if the player can change things is his current chunk (btw if the chunk is already claimed)
								UUID newTownId = UUID.randomUUID(); // create here to be able to use it in the chunk data creation
								ChunkData chunk = new ChunkData(chunkID, newTownId, ChunkType.COMMON); //create a new chunkData, for the original chunk of the new town
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
					if (!senderPD.getPlayerTown().equals(UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"))) {
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
												sender.sendMessage("You have claimed " + chunk.getChunkID()); //avant, c'était cd à la place de chunk
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
					if (!senderPD.getPlayerTown().equals(UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"))) { // check if the player have a town (Wilderness being default town
						if (senderPD.getPlayerRank().equals(Rank.KING) || senderPD.getPlayerRank().equals(Rank.LORD)) { // check if the sender is king or lord (officier can't unclaim)
							ChunkData cd = ChunkManager.getChunk(chunkID);
							if (cd != null) { //check if the chunk is in a town
								if (senderPD.getPlayerTown().equals(cd.getTown())) {
									// code to remove chunk from all player's unaccessible chunk list and delete chunkdata file
									PlayerManager.removeUnaccessibleChunkToAllPlayers(chunkID, senderPD.getPlayerTown());
									sender.sendMessage("You have unclaimed " + cd.getChunkID());
									
									//remove chunk from the town's chunks list
									Main.towns.get(senderPD.getPlayerTown()).removeChunk(cd);
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
					target.sendMessage("You've are invited to join the " + Main.towns.get(senderTownId).getTownRank().toString() + " " + senderTownName + " !");
					target.sendMessage("do /t invite-accept, or /t invite-dismiss");
				} catch (Exception e) {
					return false;
				}
			}
			
			//unfinished
			if (cmd.equals("invite-accept")) {
				if (senderPD.getPlayerTown().equals(UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"))) { // check if the sender is already in a town
					senderPD.setPlayerTown(senderTPD.getTownInvite());
					sender.sendMessage("You are know in the " + Main.towns.get(senderTPD.getTownInvite()).getTownRank() + " " + Main.towns.get(senderTPD.getTownInvite()).getTownName()); //to finish
					Main.towns.get(senderPD.getPlayerTown()).getChunks().forEach((chunkId, cd) -> PlayerManager.removeUnaccessibleChunkToPlayer(senderPD, chunkId, null)); //not tested yet, could cause issus
				}
			}
			
			
			if (cmd.equals("invite-dismiss")) {
				if(senderTPD.getTownInvite()!=null) {
					Player owner = Bukkit.getPlayer(Main.towns.get(senderTPD.getTownInvite()).getOwnerID()); //get the player instance of the town owner, to notify him that the sender refuse his invitation
					senderTPD.setTownInvite(null); //erase the town invitation, do delete it
					owner.sendMessage(senderPD.getPlayerName() + " has reject your invitation :(");
					
				}
				else {
					sender.sendMessage("You do not have any invitation running :(");
					sender.playSound(sender, Sound.ENTITY_STRIDER_STEP, 1f, 1f);
				}
			}
			
			//unfinished
			if (cmd.equals("eject")) {
				if(!senderPD.getPlayerRank().equals(Rank.KING) || !senderPD.getPlayerRank().equals(Rank.LORD)) {
					sender.sendMessage(ChatColor.RED + "You need to be Lord or King to perform such commands");
					return false;
				}
				if(args[1].isBlank()) {
					sender.sendMessage("You have to specifiate a player name");
					return false;
				}
				
				if(!args[1].equals(sender.getName())) {
					sender.sendMessage("You can not eject yourself");
					return false;
				}
				
				try {
					Player target = Bukkit.getPlayer(args[1]);
					PlayerData targetPD = Main.players.get(target.getUniqueId());
					targetPD.setPlayerTown(UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"));
					targetPD.setPlayerRank(Rank.WILD_MAN);
					targetPD.setUnaccessibleChunckID(ChunkManager.getChunksIDsFromHaskMap(Main.claimedChunks));
					target.sendMessage("You have been ejected from " + Main.towns.get(senderPD.getPlayerTown()).getTownName());
					
				} catch (Exception e) {
					sender.sendMessage("You have to specifiate a valide player name");
					return false;
				}
			}
			
			
			
			// command that send the sender a message, to show him the local map of claimed chunks, look like this:
			/* 					 ______________
			 * .________________/Town Map (x.y)\________________.
			 *0-------	---------------		x > your position
			 *1-------	---------------		+ > your town's chunks
			 *2--\N/--	---------------		+ > other town's chunks
			 *3--W+E--	-------x-------		
			 *4--/S\--	---------------
			 *5-------	---------------
			 *6-------	---------------
			 * .________________________________________________.
			 * */
			//note that their are color, to know chunk's town
			//unfinished
			// to debug
			if (cmd.equals("map")) {
				sender.sendMessage("._______/Town Map (" + chunkID + ")\\_______.");
				sender.sendMessage(" ------- " + createLine(0, new ChunkData(chunkID, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), ChunkType.COMMON), senderPD));
				sender.sendMessage(" ------- " + createLine(1, new ChunkData(chunkID, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), ChunkType.COMMON), senderPD));
				sender.sendMessage(" --\\N/-- " + createLine(2, new ChunkData(chunkID, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), ChunkType.COMMON), senderPD));
				sender.sendMessage(" --W+E-- " + createLine(3, new ChunkData(chunkID, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), ChunkType.COMMON), senderPD));
				sender.sendMessage(" --/S\\-- " + createLine(4, new ChunkData(chunkID, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), ChunkType.COMMON), senderPD));
				sender.sendMessage(" ------- " + createLine(5, new ChunkData(chunkID, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), ChunkType.COMMON), senderPD));
				sender.sendMessage(" ------- " + createLine(6, new ChunkData(chunkID, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), ChunkType.COMMON), senderPD));
				sender.sendMessage("._____________________________.");
			}
			
			
			
			
			//unfinished
			if (cmd.equals("")) {
				
			}
			
			
			
		}		
		return true;
	}
	
	//Please note that lineId start by 0
	private static String createLine(int lineId, ChunkData origine, PlayerData sender) {
		List<String> line = new ArrayList<>();
		Map<UUID, ChatColor> townsInMap = new HashMap<>();
		ChatColor[] colors = {ChatColor.AQUA, ChatColor.GREEN, ChatColor.GRAY, ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW, ChatColor.WHITE};
		for (int i=0;i<15;i++) {
			
			ChunkData cd = ChunkManager.getChunkForMap(origine, i+(15*lineId));
			
			System.out.println(i);
			System.out.println(cd.getTown());
			
			if (!townsInMap.containsKey(cd.getTown())) {
				int colorNum = townsInMap.size()+1;
				townsInMap.put(cd.getTown(), colors[colorNum]); //attribut to the new town a free color (hope there will be enough)
			}
			
			
			if (cd.getTown().equals(UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"))) { //triggered if the chunk is in wilderness
				line.add("-");
			}
			else if (sender.getPlayerTown().equals(cd.getTown())) { //triggered if the chunk is in the sender's town
				line.add(ChatColor.GOLD + "+" + ChatColor.RESET);
			}
			else {
				line.add(townsInMap.get(cd.getTown()) + "+" + ChatColor.RESET);
			}
		}
		
		if (lineId == 3) {
			line.remove(7);
			line.add(7, ChatColor.DARK_AQUA + "x" + ChatColor.RESET);
		}
		
		return String.join("", line);
		
	}
}
