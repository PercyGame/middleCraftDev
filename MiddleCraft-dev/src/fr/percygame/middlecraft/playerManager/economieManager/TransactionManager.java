package fr.percygame.middlecraft.playerManager.economieManager;

import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;

public class TransactionManager {
	
	static Map<UUID, PlayerData> pl = Main.players;
	
	public static boolean orensTransfer(Player sender, Player reciver, int orensValue) {
		
		UUID senderID = sender.getUniqueId(); //get sender id
		UUID reciverID = reciver.getUniqueId(); //get reciver id
		
		PlayerData senderPD = pl.get(senderID); //get sender data
		PlayerData reciverPD = pl.get(reciverID); //get reciver data
		
		int senderBalance = senderPD.getPlayerBalance();
		int reciverBalance = reciverPD.getPlayerBalance();
		
		if (senderBalance >= orensValue) { //check if sender have engouth orens on balance
			senderPD.setPlayerBalance(senderBalance -= orensValue);
			reciverPD.setPlayerBalance(reciverBalance += orensValue);
			PlayerManager.savePlayers(); //save players data after each transaction
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean orensGive(Player target, int orensValue) {
		UUID targetId = target.getUniqueId();
		PlayerData targetPD = pl.get(targetId);
		int targetBalance = targetPD.getPlayerBalance();
		
		targetPD.setPlayerBalance(targetBalance += orensValue);
		
		return true;
	}
	
	public static boolean orensWithdraw(Player target, int orensValue) {
		UUID targetId = target.getUniqueId();
		PlayerData targetPD = pl.get(targetId);
		int targetBalance = targetPD.getPlayerBalance();
		
		if(targetBalance >= orensValue) {
			targetPD.setPlayerBalance(targetBalance -= orensValue);
			return true;
		}
		else {
			targetPD.setPlayerBalance(0);
			return true;
		}
	}
	
}
