package fr.percygame.middlecraft.playerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TempPlayerData {
	
	private UUID playerId;
	private float chaosGauge;
	private UUID lastMSGTarget; //to store the id of the last target of a msg from the player
	private List<mailBoxMessage> unreadMSG;
	private UUID townInvite; //to store the id of all the town that have invite the player (clear if the player is already in a town)

	public TempPlayerData(UUID playerId, float chaosGauge, UUID lastMSGTarget, UUID townInvite) {
		this.playerId = playerId;
		this.chaosGauge = chaosGauge;
		this.lastMSGTarget = lastMSGTarget;
		this.unreadMSG = new ArrayList<mailBoxMessage>();
		this.townInvite = townInvite;
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

	public List<mailBoxMessage> getUnreadMSG() {
		return unreadMSG;
	}

	public void setUnreadMSG(List<mailBoxMessage> unreadMSG) {
		this.unreadMSG = unreadMSG;
	}

	public UUID getTownInvite() {
		return townInvite;
	}

	public void setTownInvite(UUID townInvite) {
		this.townInvite = townInvite;
	}
}
