package fr.percygame.middlecraft.town.chunkManager;

import java.util.UUID;

public class ChunkData {
	
	private String chunkID;
	private UUID town;
	private ChunkType chunkType;
	
	public ChunkData(String chunkID, UUID town, ChunkType chunkType) {
		this.chunkID = chunkID;
		this.town = town;
		this.chunkType = chunkType;
	}

	public String getChunkID() {
		return chunkID;
	}

	public void setChunkID(String chunkID) {
		this.chunkID = chunkID;
	}

	public UUID getTown() {
		return town;
	}

	public void setTown(UUID town) {
		this.town = town;
	}

	public ChunkType getChunkType() {
		return chunkType;
	}

	public void setChunkType(ChunkType chunkType) {
		this.chunkType = chunkType;
	}
	
	

}
