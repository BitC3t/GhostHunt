package me.floof.here.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.floof.here.main.GhostHuntMain;

public class StartGame implements CommandExecutor {
	private final GhostHuntMain plugin;

	public StartGame(GhostHuntMain plugin) {
		this.plugin = plugin;
	}

	char c = '\u27BE';

	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard board = manager.getMainScoreboard();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("startgame")) {
			Bukkit.broadcastMessage(ChatColor.GOLD + "[" + c + "]" + ChatColor.GREEN
					+ "The game is beginning. Hunters will be blinded for 10 seconds, and will be frozen.");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamemode survival @a");
			for (Player p : Bukkit.getOnlinePlayers()) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnpoint " + p.getName());
				if (board.getPlayerTeam(p).getName().equals("Hunter")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"effect give " + p.getName() + " minecraft:blindness 15 255");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"effect give " + p.getName() + " minecraft:slowness 15 255");

				}
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

				@Override
				public void run() {

					char c = '\u27BE';
					Bukkit.broadcastMessage(
							ChatColor.GOLD + "[" + c + "] " + ChatColor.RED + "Hunters are now free. RUN AWAY GHOST.");

				}
			}, 300L);
		}
		return false;
	}

}
