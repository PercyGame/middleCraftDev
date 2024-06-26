package fr.percygame.middlecraft.spellSystem.spell;

import java.util.List;
import java.util.UUID;

import fr.percygame.middlecraft.spellSystem.rune.Rune;

//a spell is a group of rune used to cast magic
//it can be use like that (Material:paper), or in a spellbook
public class Spell {
	
	private String name;
	private UUID id;
	private List<Rune> runes;
	private UUID creator_id;
	
	public Spell(String name, UUID id, List<Rune> runes, UUID creator_id) {
		super();
		this.name = name;
		this.id = id;
		this.runes = runes;
		this.creator_id = creator_id;
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

	public List<Rune> getRunes() {
		return runes;
	}

	public void setRunes(List<Rune> runes) {
		this.runes = runes;
	}

	public UUID getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(UUID creator_id) {
		this.creator_id = creator_id;
	}
}
