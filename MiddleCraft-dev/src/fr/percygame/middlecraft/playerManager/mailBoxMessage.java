package fr.percygame.middlecraft.playerManager;

import java.util.UUID;

public class mailBoxMessage {
	private UUID sender;
	private String content;
	private String time;
	
	
	public mailBoxMessage(UUID sender, String content, String time) {
		super();
		this.sender = sender;
		this.content = content;
		this.time = time;
	}


	public UUID getSender() {
		return sender;
	}


	public void setSender(UUID sender) {
		this.sender = sender;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
}
