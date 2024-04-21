package fr.percygame.middlecraft.playerManager;

import java.text.DecimalFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.percygame.middlecraft.Main;

public class PlayerScoreboard{
	
	public static String mapChaosToVisualGauge(Player p) {
		String visualGauge="";
		PlayerData pd = Main.players.get(p.getUniqueId());
		TempPlayerData tpd = Main.tempPlayerData.get(p.getUniqueId());
		int value = (int) (tpd.getChaosGauge()/pd.getChaosQtt()*16);
		
		if (value == 16) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■■■■■■■■■";
		}
		if (value == 15) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■■■■■■■■" + ChatColor.LIGHT_PURPLE + "■";
		}
		if (value == 14) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■■■■■■■" + ChatColor.LIGHT_PURPLE + "■■";
		}
		if (value == 13) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■■■■■■" + ChatColor.LIGHT_PURPLE + "■■■";
		}
		if (value == 12) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■■■■■" + ChatColor.LIGHT_PURPLE + "■■■■";
		}
		if (value == 11) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■■■■" + ChatColor.LIGHT_PURPLE + "■■■■■";
		}
		if (value == 10) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■■■" + ChatColor.LIGHT_PURPLE + "■■■■■■";
		}
		if (value == 9) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■■" + ChatColor.LIGHT_PURPLE + "■■■■■■■";
		}
		if (value == 8) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■■" + ChatColor.LIGHT_PURPLE + "■■■■■■■■";
		}
		if (value == 7) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■■" + ChatColor.LIGHT_PURPLE + "■■■■■■■■■";
		}
		if (value == 6) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■■" + ChatColor.LIGHT_PURPLE + "■■■■■■■■■■";
		}
		if (value == 5) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■■" + ChatColor.LIGHT_PURPLE + "■■■■■■■■■■■";
		}
		if (value == 4) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■■" + ChatColor.LIGHT_PURPLE + "■■■■■■■■■■■■";
		}
		if (value == 3) {
			visualGauge = ChatColor.DARK_PURPLE + "■■■" + ChatColor.LIGHT_PURPLE + "■■■■■■■■■■■■■■";
		}
		if (value == 2) {
			visualGauge = ChatColor.DARK_PURPLE + "■■" + ChatColor.LIGHT_PURPLE + "■■■■■■■■■■■■■■■";
		}
		if (value == 1) {
			visualGauge = ChatColor.DARK_PURPLE + "■" + ChatColor.LIGHT_PURPLE + "■■■■■■■■■■■■■■■■";
		}
		if (value == 0) {
			visualGauge = ChatColor.LIGHT_PURPLE + "■■■■■■■■■■■■■■■■";
		}
		
		
		return visualGauge;
	}
	
	public static void createScoreboard(Player player) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		ScoreboardManager sbm = Bukkit.getScoreboardManager();
		Scoreboard sb = sbm.getNewScoreboard();
		Objective objective = sb.registerNewObjective("general", Criteria.create("dummy"), "general");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "MIDDLECRAFT");
		
		String playerTownName;
		
		if(!Main.players.get(player.getUniqueId()).getPlayerTown().equals(UUID.fromString("Wilderness"))) {
			playerTownName = Main.towns.get(Main.players.get(player.getUniqueId()).getPlayerTown()).getTownName();
		}
		else {
			playerTownName = "Wilderness";
		}
		
		Score townDisplay = objective.getScore(ChatColor.BLUE + "" + ChatColor.BOLD + "►Town " + ChatColor.RESET + ChatColor.AQUA + playerTownName);
		Score balanceDisplay = objective.getScore(ChatColor.BLUE + "" + ChatColor.BOLD + "►Orens " + ChatColor.RESET + ChatColor.AQUA + Main.players.get(player.getUniqueId()).getPlayerBalance() + "¤");
		Score separator2 = objective.getScore(ChatColor.GOLD + "◈--------------◈");
		Score chaosTitle = objective.getScore(ChatColor.BLUE + "" + ChatColor.BOLD + "►Chaos");
		Score chaosVisualGauge = objective.getScore(mapChaosToVisualGauge(player));
		Score chaosTextualGauge = objective.getScore(ChatColor.AQUA + " " + df.format(((Main.tempPlayerData.get(player.getUniqueId()).getChaosGauge()*100))/Main.players.get(player.getUniqueId()).getChaosQtt()) + " % " + "(" + Main.tempPlayerData.get(player.getUniqueId()).getChaosGauge() +"/" + Main.players.get(player.getUniqueId()).getChaosQtt() + ")");
		Score chaosLevel = objective.getScore(ChatColor.BLUE + "" + ChatColor.BOLD + "►Level " + ChatColor.RESET + ChatColor.AQUA + Main.players.get(player.getUniqueId()).getChaosLevel());
		
		townDisplay.setScore(6);
		balanceDisplay.setScore(5);
		separator2.setScore(4);
		chaosTitle.setScore(3);
		chaosVisualGauge.setScore(2);
		chaosTextualGauge.setScore(1);
		chaosLevel.setScore(0);
		
		
		player.setScoreboard(sb);
	}
	
	
	
}
