package fr.percygame.middlecraft.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.percygame.middlecraft.town.TownData;

public class TownDataSerializationManager {

	private Gson gson;
	
	public TownDataSerializationManager() {
		this.gson = createGsonInstance();
	}
	
	private Gson createGsonInstance() {
		return new GsonBuilder()
				.setPrettyPrinting()
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
				
	}
	
	public String serialize(TownData td) {
		return this.gson.toJson(td);
	}
	
	
	public TownData deserialise(String json) {
		return this.gson.fromJson(json, TownData.class);
	}
	
}
