package fr.percygame.middlecraft.town.chunkManager;

public class ChunkData {
	
	private String chunkID;
	private String town;
	private ChunkType chunkType;
	
	public ChunkData(String chunkID, String town, ChunkType chunkType) {
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public ChunkType getChunkType() {
		return chunkType;
	}

	public void setChunkType(ChunkType chunkType) {
		this.chunkType = chunkType;
	}
	
	

}
