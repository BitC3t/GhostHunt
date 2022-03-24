package me.floof.here.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.floof.here.main.GhostHuntMain;
import net.md_5.bungee.api.ChatColor;

public class HunterTeam implements CommandExecutor {
	private final GhostHuntMain plugin;

	public HunterTeam(GhostHuntMain plugin) {
		this.plugin = plugin;
	}

	char c = '\u27BE';
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard board = manager.getMainScoreboard();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Adding player to Hunter Team
		if (cmd.getName().equalsIgnoreCase("hunter")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.BOLD + "" + ChatColor.RED
						+ "Invalid command usage. Use /hunter <player-name>.");
				return true;
			}
			if (args.length >= 2) {
				sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.BOLD + "" + ChatColor.RED
						+ "Invalid command usage. Use /hunter <player-name>.");
				return true;
			}
			if (args.length == 1) {

				if (board.getTeams().size() == 0) {
					sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD
							+ "You have not set any teams. Use /resetteams to create teams, and /hunter and /ghost to set players.");
					return true;
				}

				Player p = Bukkit.getServer().getPlayer(args[0]);

				if (!(Bukkit.getOnlinePlayers().contains(p))) {
					sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.BOLD + "" + ChatColor.RED
							+ "Player must be online to execute command.");
				}

				if (Bukkit.getOnlinePlayers().contains(p)) {
					if (board.getTeam("Hunter") != null) {
						board.getTeam("Hunter").addPlayer(p);
						sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.GREEN + p.getName()
								+ " has been added to the Hunter Team.");
						p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
					}
				}

			}

		}
		if (cmd.getName().equalsIgnoreCase("ghost")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.BOLD + "" + ChatColor.RED
						+ "Invalid command usage. Use /ghost <player-name>.");
				return true;
			}
			if (args.length >= 2) {
				sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.BOLD + "" + ChatColor.RED
						+ "Invalid command usage. Use /ghost <player-name>.");
				return true;
			}
			if (args.length == 1) {
				if (board.getTeams().size() == 0) {
					sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD
							+ "You have not set any teams. Use /resetteams to create teams, and /hunter and /ghost to set players.");
					return true;
				}

				Player p = Bukkit.getServer().getPlayer(args[0]);

				if (!(Bukkit.getOnlinePlayers().contains(p))) {
					sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.BOLD + "" + ChatColor.RED
							+ "Player must be online to execute command.");
				}

				if (Bukkit.getOnlinePlayers().contains(p)) {
					if (board.getTeam("Ghost") != null) {
						if (board.getTeam("Ghost").getSize() == 0) {
							board.getTeam("Ghost").addPlayer(p);
							ItemStack invisStick = new ItemStack(Material.STICK, 1);
							invisStick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
							ItemMeta stickmeta = invisStick.getItemMeta();
							stickmeta.setCustomModelData(1);
							stickmeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.GREEN + "The Ghost Light");
							stickmeta.setLore(Arrays.asList(ChatColor.AQUA
									+ "Makes you invisible for 8 seconds, cooldown lasts for 30 seconds."));

							invisStick.setItemMeta(stickmeta);
							p.getInventory().addItem(invisStick);
							sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.GREEN + p.getName()
									+ " has been added to the Ghost Team.");

						} else {
							sender.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.BOLD + "" + ChatColor.RED
									+ "There can be ONLY one hunter. Use /resetteams to reset all teams.");

						}

					}
				}
			}

		}
		return true;
	}

}
