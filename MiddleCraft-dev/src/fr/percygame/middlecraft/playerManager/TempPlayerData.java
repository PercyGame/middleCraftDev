package fr.percygame.middlecraft.playerManager;

import java.util.UUID;

public class TempPlayerData {
	
	private UUID playerId;
	private float chaosGauge;

	public TempPlayerData(UUID playerId, float chaosGauge) {
		this.playerId = playerId;
		this.chaosGauge = chaosGauge;
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
}
