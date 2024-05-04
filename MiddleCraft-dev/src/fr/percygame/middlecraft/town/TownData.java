package fr.percygame.middlecraft.town;

import java.util.Map;
import java.util.UUID;

import fr.percygame.middlecraft.town.chunkManager.ChunkData;

public class TownData {
	private UUID id;
	private String townName;
	private UUID ownerID;
	private boolean allowFightForVisitor;
	private int chunkLimit; //max numbers of claimed chunk. They can be what ever type the owner want
	private Map<String, ChunkData> chunks;
	private TownRank townRank;
	private int townBalance;
	
	public TownData(UUID id, String townName, UUID ownerID, boolean allowFightForVisitor, int chunkLimit, Map<String, ChunkData> chunks, TownRank townRank, int townBalance) {
		this.id = id;
		this.townName = townName;
		this.ownerID = ownerID;
		this.allowFightForVisitor = allowFightForVisitor;
		this.chunkLimit = chunkLimit;
		this.chunks = chunks;
		this.townRank = townRank;
		this.townBalance = townBalance;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public boolean isAllowFightForVisitor() {
		return allowFightForVisitor;
	}

	public void setAllowFightForVisitor(boolean allowFightForVisitor) {
		this.allowFightForVisitor = allowFightForVisitor;
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
	
	public void removeChunk(ChunkData chunk) {
		this.chunks.remove(chunk.getChunkID());
	}

	public TownRank getTownRank() {
		return townRank;
	}

	public void setTownRank(TownRank townRank) {
		this.townRank = townRank;
	}

	public int getTownBalance() {
		return townBalance;
	}

	public void setTownBalance(int townBalance) {
		this.townBalance = townBalance;
	}
	
	
}
