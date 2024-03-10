package fr.percygame.middlecraft.playerManager.economieManager;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;
import fr.percygame.middlecraft.playerManager.PlayerScoreboard;

public class TransactionManager {
	
	static Map<UUID, PlayerData> pl = Main.players;
	
	public static boolean orensTransfer(PlayerData sender, PlayerData reciver, int orensValue) {
		PlayerScoreboard.createScoreboard(Bukkit.getPlayer(sender.getPlayerID()));
		PlayerScoreboard.createScoreboard(Bukkit.getPlayer(reciver.getPlayerID()));
		int senderBalance = sender.getPlayerBalance();
		int reciverBalance = reciver.getPlayerBalance();
		
		if (senderBalance >= orensValue) { //check if sender have engouth orens on balance
			sender.setPlayerBalance(senderBalance -= orensValue);
			reciver.setPlayerBalance(reciverBalance += orensValue);
			PlayerManager.savePlayers(); //save players data after each transaction
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean orensGive(PlayerData target, int orensValue) {
		int targetBalance = target.getPlayerBalance();
		PlayerScoreboard.createScoreboard(Bukkit.getPlayer(target.getPlayerID()));
		
		target.setPlayerBalance(targetBalance += orensValue);
		
		return true;
	}
	
	public static boolean orensWithdraw(PlayerData target, int orensValue, boolean allowSubZero) {
		int targetBalance = target.getPlayerBalance();
		PlayerScoreboard.createScoreboard(Bukkit.getPlayer(target.getPlayerID()));
		
		if(targetBalance >= orensValue) {
			target.setPlayerBalance(targetBalance -= orensValue);
			return true;
		}
		else {
			if(allowSubZero) {
				target.setPlayerBalance(0);
				return true;
			}
			return false;
			
		}
	}
	
}
