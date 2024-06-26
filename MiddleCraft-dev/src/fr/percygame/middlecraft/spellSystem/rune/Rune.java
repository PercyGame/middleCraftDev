package fr.percygame.middlecraft.spellSystem.rune;

import java.util.UUID;

// a rune is like an activation to a spell, it's the part of the spell that do somthing
// for instance, in a spell that harm a target, you could have a rune for the harming, and an other to increase duration, or whatever
public class Rune {
	
	private runeType type;
	private String name;
	private UUID id;
	private String[] lore;
	private runeEffect effect;
	
	
	public Rune(runeType type, String name, UUID id, String[] lore, runeEffect effect) {
		super();
		this.type = type;
		this.name = name;
		this.id = id;
		this.lore = lore;
		this.effect = effect;
	}


	public runeType getType() {
		return type;
	}


	public void setType(runeType type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String[] getLore() {
		return lore;
	}


	public void setLore(String[] lore) {
		this.lore = lore;
	}


	public runeEffect getEffect() {
		return effect;
	}


	public void setEffect(runeEffect effect) {
		this.effect = effect;
	}
	
	
}
