package fr.percygame.middlecraft.town.chunkManager;

import java.util.Map;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.town.TownData;

public class ChunkManager {
	
	static Map<String, TownData> t = Main.towns;
	
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
		
		System.out.println(chunkCo[1]);
		System.out.println(chunkCo[0]);
		
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
	
}
