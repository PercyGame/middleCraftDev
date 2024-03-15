package fr.percygame.middlecraft.playerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TempPlayerData {
	
	private UUID playerId;
	private float chaosGauge;
	private UUID lastMSGTarget; //to store the id of the last target of a msg from the player
	private List<String> unreadMSG;
	private List<UUID> townInvite; //to store the id of all the town that have invite the player (clear if the player is already in a town)

	public TempPlayerData(UUID playerId, float chaosGauge, UUID lastMSGTarget) {
		this.playerId = playerId;
		this.chaosGauge = chaosGauge;
		this.lastMSGTarget = lastMSGTarget;
		this.unreadMSG = new ArrayList<String>();
		this.townInvite = new ArrayList<UUID>();
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}

	public float getChaosGauge() {
		return chaosGauge;
	}

	public void setChaosGauge(float chaosGauge) {
		this.chaosGauge = chaosGauge;
	}

	public UUID getLastMSGTarget() {
		return lastMSGTarget;
	}

	public void setLastMSGTarget(UUID lastMSGTarget) {
		this.lastMSGTarget = lastMSGTarget;
	}

	public List<String> getUnreadMSG() {
		return unreadMSG;
	}

	public void setUnreadMSG(List<String> unreadMSG) {
		this.unreadMSG = unreadMSG;
	}

	public List<UUID> getTownInvite() {
		return townInvite;
	}

	public void setTownInvite(List<UUID> townInvite) {
		this.townInvite = townInvite;
	}
}
