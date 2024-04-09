package fr.percygame.middlecraft.playerManager;

import java.util.List;
import java.util.UUID;

public class PlayerData {
	
	private String playerName;
	private UUID playerID;
	private Rank playerRank;
	private String playerTown;
	private int playerBalance;
	private List<String> unaccessibleChunckID;
	private Grade playerGrade;
	private int chaosLevel;
	private int chaosQtt;
	
	
	public PlayerData(String playerName, UUID playerID, Rank playerRank, String playerTown, int playerBalance, List<String> unaccessibleChunkID,
			Grade playerGrade, int chaoseLevel, int chaosQtt) {
		this.playerName = playerName;
		this.playerID = playerID;
		this.playerRank = playerRank;
		this.playerTown = playerTown;
		this.playerBalance = playerBalance;
		this.unaccessibleChunckID = unaccessibleChunkID;
		this.playerGrade = playerGrade;
		this.chaosLevel = chaoseLevel;
		this.chaosQtt = chaosQtt;
		
	}

	
	//get and set for each value
	
	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public UUID getPlayerID() {
		return playerID;
	}


	public void setPlayerID(UUID playerID) {
		this.playerID = playerID;
	}


	public Rank getPlayerRank() {
		return playerRank;
	}


	public void setPlayerRank(Rank playerRank) {
		this.playerRank = playerRank;
	}


	public String getPlayerTown() {
		return playerTown;
	}


	public void setPlayerTown(String playerTown) {
		this.playerTown = playerTown;
	}


	public int getPlayerBalance() {
		return playerBalance;
	}
	
	
	public List<String> getUnaccessibleChunckID() {
		return unaccessibleChunckID;
	}

	
	public void setUnaccessibleChunckID(List<String> unaccessibleChunckID) {
		this.unaccessibleChunckID = unaccessibleChunckID;
	}
	
	
	public void addUnaccessibleChunkID(String chunkID) {
		this.unaccessibleChunckID.add(chunkID);
	}
	
	public void removeUnaccessibleChunkID(String chunkID) {
		this.unaccessibleChunckID.remove(chunkID);
	}

	
	public void setPlayerBalance(int playerBalance) {
		this.playerBalance = playerBalance;
	}


	public Grade getPlayerGrade() {
		return playerGrade;
	}


	public void setPlayerGrade(Grade playerGrade) {
		this.playerGrade = playerGrade;
	}


	public int getChaosLevel() {
		return chaosLevel;
	}


	public void setChaosLevel(int chaosLevel) {
		this.chaosLevel = chaosLevel;
	}


	public int getChaosQtt() {
		return chaosQtt;
	}


	public void setChaosQtt(int chaosQtt) {
		this.chaosQtt = chaosQtt;
	}
	
	
	
	
}
