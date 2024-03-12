package fr.percygame.middlecraft.multiBlockStructures;

import java.util.List;
import java.util.UUID;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;


public class MultiBlockStructure {
	private UUID structureID;
	private Location origin;
	private List<Block> blocks;
	private List<Chunk> chunks; // used to avoid having to check every single block when a player try to break a block, just chech if it's in a chunk where there is a mbs
	private MultiBlockStructureType type;
	
	
	public MultiBlockStructure(UUID structureID, Location origin, List<Block> blocks, List<Chunk> chunks,
			MultiBlockStructureType type) {
		super();
		this.structureID = structureID;
		this.origin = origin;
		this.blocks = blocks;
		this.chunks = chunks;
		this.type = type;
	}


	public UUID getStructureID() {
		return structureID;
	}


	public void setStructureID(UUID structureID) {
		this.structureID = structureID;
	}


	public Location getOrigin() {
		return origin;
	}


	public void setOrigin(Location origin) {
		this.origin = origin;
	}


	public List<Block> getBlocks() {
		return blocks;
	}


	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}


	public List<Chunk> getChunks() {
		return chunks;
	}


	public void setChunks(List<Chunk> chunks) {
		this.chunks = chunks;
	}


	public MultiBlockStructureType getType() {
		return type;
	}


	public void setType(MultiBlockStructureType type) {
		this.type = type;
	}
}
