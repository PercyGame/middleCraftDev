package fr.percygame.middlecraft.town;

import java.util.Map;
import java.util.UUID;

import fr.percygame.middlecraft.town.chunkManager.ChunkData;

public class TownData {
	private String townName;
	private UUID ownerID;
	private int chunkLimit; //max numbers of claimed chunk. They can be what ever type the owner want
	private Map<String, ChunkData> chunks;
	private TownRank townRank;
	
	public TownData(String townName, UUID ownerID, int chunkLimit, Map<String, ChunkData> chunks, TownRank townRank) {
		this.townName = townName;
		this.ownerID = ownerID;
		this.chunkLimit = chunkLimit;
		this.chunks = chunks;
		this.townRank = townRank;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public UUID getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(UUID ownerID) {
		this.ownerID = ownerID;
	}

	public int getChunkLimit() {
		return chunkLimit;
	}

	public void setChunkLimit(int chunkLimit) {
		this.chunkLimit = chunkLimit;
	}

	public Map<String, ChunkData> getChunks() {
		return chunks;
	}

	public void setChunks(Map<String, ChunkData> chunks) {
		this.chunks = chunks;
	}
	
	public void addChunks(ChunkData chunk) {
		this.chunks.put(chunk.getChunkID(), chunk);
	}

	public TownRank getTownRank() {
		return townRank;
	}

	public void setTownRank(TownRank townRank) {
		this.townRank = townRank;
	}
	
	
}
