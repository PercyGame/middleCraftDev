package fr.percygame.middlecraft.playerManager.economieManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;

public class OrensWithdrawCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		String targetName = args[0];
		int orensValue = Integer.valueOf(args[1]);
		
		PlayerData targetPD = PlayerManager.getPlayerByName(targetName);
		
		TransactionManager.orensWithdraw(targetPD, orensValue, true);
		
		return false;
	}

}
