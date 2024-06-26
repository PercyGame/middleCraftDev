package fr.percygame.middlecraft.spellSystem.spellBook;

import java.util.Map;
import java.util.UUID;

import fr.percygame.middlecraft.spellSystem.spell.Spell;

//a spellbook is a container for many spell
public class Spellbook {
	
	private String name;
	private UUID owner;
	private Map<UUID, Spell> spells;
	
	public Spellbook(String name, UUID owner, Map<UUID, Spell> spells) {
		super();
		this.name = name;
		this.owner = owner;
		this.spells = spells;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public Map<UUID, Spell> getSpells() {
		return spells;
	}

	public void setSpells(Map<UUID, Spell> spells) {
		this.spells = spells;
	}
}
