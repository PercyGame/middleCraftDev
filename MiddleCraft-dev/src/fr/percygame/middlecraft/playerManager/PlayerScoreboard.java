package fr.percygame.middlecraft.playerManager;

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
	
	public static void createScoreboard(Player player) {
		ScoreboardManager sbm = Bukkit.getScoreboardManager();
		Scoreboard sb = sbm.getNewScoreboard();
		Objective objective = sb.registerNewObjective("general", Criteria.create("dummy"), "general");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "MIDDLECRAFT");
		
		Score separator1 = objective.getScore("");
		Score townDisplay = objective.getScore("Town : " + Main.players.get(player.getUniqueId()).getPlayerTown());
		Score balanceDisplay = objective.getScore("Orens : " + Main.players.get(player.getUniqueId()).getPlayerBalance());
		Score separator2 = objective.getScore("________________");
		Score chaosTitle = objective.getScore("Chaos");
		Score chaosVisualGauge = objective.getScore("||||||||||||||||");
		Score chaosTextualGauge = objective.getScore(((Main.tempPlayerData.get(player.getUniqueId()).getChaosGauge()*100))/Main.players.get(player.getUniqueId()).getChaosQtt() + " % " + "(" + Main.tempPlayerData.get(player.getUniqueId()).getChaosGauge() +"/" + Main.players.get(player.getUniqueId()).getChaosQtt() + ")");
		Score chaosLevel = objective.getScore("Level : " + Main.players.get(player.getUniqueId()).getChaosLevel());
		
		separator1.setScore(14);
		townDisplay.setScore(13);
		balanceDisplay.setScore(12);
		separator2.setScore(11);
		chaosTitle.setScore(10);
		chaosVisualGauge.setScore(9);
		chaosTextualGauge.setScore(8);
		chaosLevel.setScore(7);
		
		
		player.setScoreboard(sb);
	}
	
}
