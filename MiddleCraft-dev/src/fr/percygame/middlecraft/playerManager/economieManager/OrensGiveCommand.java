package fr.percygame.middlecraft.playerManager.economieManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OrensGiveCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		String targetName = args[0];
		int orensValue = Integer.valueOf(args[1]);
		
		
		return false;
	}

}
