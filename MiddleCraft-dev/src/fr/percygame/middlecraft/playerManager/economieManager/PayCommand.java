package fr.percygame.middlecraft.playerManager.economieManager;

import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;
import fr.percygame.middlecraft.playerManager.PlayerManager;

public class PayCommand implements CommandExecutor {

	static Map<UUID, PlayerData> pl = Main.players;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if (sender instanceof Player) {
			String targetName = args[0];
			int orensValue = Integer.valueOf(args[1]);
			PlayerData targetData = PlayerManager.getPlayerByName(targetName);
			PlayerData senderData = PlayerManager.getPlayerByName(sender.getName());

			TransactionManager.orensTransfer(senderData, targetData, orensValue);
			return true;
		}
		return false;		
	}

}
