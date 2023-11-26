package fr.percygame.middlecraft.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.percygame.middlecraft.playerManager.PlayerData;

public class PlayerDataSerealizationManager {
	
	private Gson gson;
	
	public PlayerDataSerealizationManager() {
		this.gson = createGsonInstance();
	}
	
	private Gson createGsonInstance() {
		return new GsonBuilder()
				.setPrettyPrinting()
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
				
	}
	
	public String serialize(PlayerData pd) {
		return this.gson.toJson(pd);
	}
	
	
	public PlayerData deserialise(String json) {
		return this.gson.fromJson(json, PlayerData.class);
	}

}
