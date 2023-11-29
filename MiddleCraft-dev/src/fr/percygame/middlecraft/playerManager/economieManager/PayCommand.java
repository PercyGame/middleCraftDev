package fr.percygame.middlecraft.playerManager.economieManager;

import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import fr.percygame.middlecraft.Main;
import fr.percygame.middlecraft.playerManager.PlayerData;

public class PayCommand implements CommandExecutor {

	static Map<UUID, PlayerData> pl = Main.players;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		String targetName = args[0];
		int orensValue = Integer.valueOf(args[1]);
		int numberOfPlayerData = pl.size();
		
		
		// do the transaction using a try catch
		return true;
	}

}
