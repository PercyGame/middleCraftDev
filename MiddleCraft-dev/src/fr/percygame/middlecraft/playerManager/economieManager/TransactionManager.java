package fr.percygame.middlecraft.playerManager.economieManager;

import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;

public class TransactionManager {
	
	static Map<UUID, PlayerData> pl = Main.players;
	
	public static boolean orensTransfer(PlayerData sender, PlayerData reciver, int orensValue) {
		
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
