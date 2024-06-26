package fr.percygame.middlecraft.town.chunkManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.town.TownData;

public class ChunkManager {
	
	static Map<UUID, TownData> t = Main.towns;
	static Map<String, ChunkData> claimedChunks = Main.claimedChunks;
	
	//used to map the chunk near the player
		int xChunkMappingLocation[] = {-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7};
		int yChunkMappingLocation[] = {-3, -2, -1, 0, 1, 2, 3};
	
	public static ChunkData getChunk(String chunkID) {
		
		for (TownData td : t.values()) {
			System.out.println(td.toString());
			Map<String, ChunkData> chunks = td.getChunks();
			if (chunks.containsKey(chunkID)) {
				return chunks.get(chunkID);
			}
		}
		
		return null;
	}
	
	//function to check if the targeted chunk is near the targeted town
	public static boolean isChunkTouchingTown(ChunkData chunk, TownData town) {
		
		String chunkID = chunk.getChunkID();
		Map<String, ChunkData> chunks = town.getChunks();
		
		System.out.println(chunkID);
		
		String[] chunkCo = chunkID.split("\\.", 0);
		
		int chunkX = Integer.valueOf(chunkCo[0]);
		int chunkZ = Integer.valueOf(chunkCo[1]);
		
		String northChunkID = chunkX + "." + (chunkZ-1);
		String southChunkID = chunkX + "." + (chunkZ+1);
		String eastChunkID = (chunkX+1) + "." + chunkZ;
		String westChunkID = (chunkX-1) + "." + chunkZ;
		
		if(chunks.containsKey(westChunkID)) return true;
		if(chunks.containsKey(eastChunkID)) return true;
		if(chunks.containsKey(northChunkID)) return true;
		if(chunks.containsKey(southChunkID)) return true;
		
		return false;
	}
	
	public static List<String> getChunksIDsFromHaskMap(Map<String, ChunkData> chunkMap){
		List<String> list = new ArrayList<>();
		
		chunkMap.forEach((id,cd) -> list.add(id));
		
		System.out.println("<locked chunks list> " + list);
		
		return list;
	}
	
	public static ChunkData getChunkForMap(ChunkData origine, int chunkIdentifier) {
		String[] origineCo = origine.getChunkID().split("\\.", 0);
		int chunkXSimpleIdentifier = 0;
		int idCopy = chunkIdentifier;
		for (int i=0;i<7;i++) {
			if ((idCopy - 15) < 0) {
				chunkXSimpleIdentifier = idCopy;
				break;
			}
			else {
				idCopy -= 15;
			}
		}
		
		int chunkYSimpleIdentifier = 0;
		if (chunkIdentifier<15) {
			chunkYSimpleIdentifier = 0;
		}
		else if (chunkIdentifier<30) {
			chunkYSimpleIdentifier = 1;
		}
		else if (chunkIdentifier<45) {
			chunkYSimpleIdentifier = 2;
		}
		else if (chunkIdentifier<60) {
			chunkYSimpleIdentifier = 3;
		}
		else if (chunkIdentifier<75) {
			chunkYSimpleIdentifier = 4;
		}
		else if (chunkIdentifier<90) {
			chunkYSimpleIdentifier = 5;
		}
		else {
			chunkYSimpleIdentifier = 6;
		}
		
		
		int x = Integer.valueOf(origineCo[0])+chunkXSimpleIdentifier;
		int y = Integer.valueOf(origineCo[1]+chunkYSimpleIdentifier);
		
		String chunkCo = x + "." + y;
		if (claimedChunks.containsKey(chunkCo)) {
			return Main.claimedChunks.get(chunkCo);
		}
		else {
			return new ChunkData(chunkCo, UUID.fromString("ef7f084e-bb8e-463a-900a-76ac64783c91"), ChunkType.COMMON);
		}
	}
}
