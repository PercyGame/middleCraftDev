package fr.percygame.middlecraft.playerManager;

import java.util.Map;
import java.util.UUID;

public class PlayerData {
	
	private String playerName;
	private UUID playerID;
	private Rank playerRank;
	private String playerTown;
	private int playerBalance;
	private Map<String, Boolean> accessibleChunckID;
	private Grade playerGrade;
	
	
	public PlayerData(String playerName, UUID playerID, Rank playerRank, String playerTown, int playerBalance, Map<String, Boolean> accessibleChunkID,
			Grade playerGrade) {
		this.playerName = playerName;
		this.playerID = playerID;
		this.playerRank = playerRank;
		this.playerTown = playerTown;
		this.playerBalance = playerBalance;
		this.accessibleChunckID = accessibleChunkID;
		this.playerGrade = playerGrade;
		
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


	public void setPlayerBalance(int playerBalance) {
		this.playerBalance = playerBalance;
	}


	public Map<String, Boolean> getAccessibleChunckID() {
		return accessibleChunckID;
	}


	public void setAccessibleChunckID(Map<String, Boolean> accessibleChunckID) {
		this.accessibleChunckID = accessibleChunckID;
	}


	public Grade getPlayerGrade() {
		return playerGrade;
	}


	public void setPlayerGrade(Grade playerGrade) {
		this.playerGrade = playerGrade;
	}
	
	
	
	
}
