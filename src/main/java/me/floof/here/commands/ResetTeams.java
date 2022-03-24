package me.floof.here.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.floof.here.main.GhostHuntMain;
import net.md_5.bungee.api.ChatColor;

public class ResetTeams implements CommandExecutor {
	private final GhostHuntMain plugin;

	public ResetTeams(GhostHuntMain plugin) {
		this.plugin = plugin;
	}

	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard board = manager.getMainScoreboard();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if (cmd.getName().equalsIgnoreCase("resetteams")) {
			for (Team t : board.getTeams()) {
				t.unregister();
			}
			char c = '\u27BE';
			board.registerNewTeam("Ghost");
			board.registerNewTeam("Hunter");
			sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.GREEN
					+ "Reset all teams, removed all players from all teams.");
		}
		return true;
	}

}
