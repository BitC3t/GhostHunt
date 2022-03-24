package me.floof.here.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.floof.here.main.GhostHuntMain;
import net.md_5.bungee.api.ChatColor;

public class CompassEvent implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCompassClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Player pTar = null;
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();

		for (Player p1 : Bukkit.getOnlinePlayers()) {
			if (board.getPlayerTeam(p1) == null) {
				return;
			}
			if (board.getPlayerTeam(p1).getName().equals("Ghost")) {
				pTar = p1;

			}
		}

		if (pTar == null) {
			return;
		}
		if (p.getInventory().getItemInMainHand().equals(new ItemStack(Material.COMPASS))) {
			char c = '\u27BE';
			p.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.GREEN + "Compass is now tracking "
					+ pTar.getName() + ".");
		}
		final ItemStack held = p.getInventory().getItemInMainHand();
		if (held == null || held.getItemMeta() == null) {
			return;
		}
		if (held.getItemMeta().getDisplayName().equals(ChatColor.BLACK + "The Ghost Tracker")) {
			char c = '\u27BE';
			p.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.GREEN + "Compass is now tracking "
					+ pTarget.getName() + ".");

		}

		if (pTar.getWorld().getName().equalsIgnoreCase("world_nether")) {
			for (Player p2 : Bukkit.getOnlinePlayers()) {
				if (p2.getWorld().getName().equalsIgnoreCase("world_nether")) {
					final ItemStack held2 = p2.getInventory().getItemInMainHand();
					if (held == null) {
						return;
					}
					if (held.getType().equals(Material.COMPASS)) {

						CompassMeta meta = (CompassMeta) held.getItemMeta();
						meta.setDisplayName(ChatColor.BLACK + "The Ghost Tracker");
						meta.setLodestone(pTarget.getLocation());
						meta.setLodestoneTracked(false);
						held.setItemMeta(meta);
						System.out.print("test");

					}
				}
			}
		}

	}

	Player pTarget = null;

	@SuppressWarnings("deprecation")
	@EventHandler

	public void onMove(PlayerMoveEvent ex) {

		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();

		for (Player p1 : Bukkit.getOnlinePlayers()) {
			if (board.getPlayerTeam(p1) == null) {
				return;
			}
			if (board.getPlayerTeam(p1).getName().equals("Ghost")) {
				pTarget = p1;

			}
			final ItemStack held = p1.getInventory().getItemInMainHand();
			if (held == null) {
				return;
			}
			if (held.getType().equals(Material.COMPASS)) {
				if (p1.getWorld().getName().equalsIgnoreCase("world")) {
					if (held.getItemMeta().getDisplayName().equals(ChatColor.BLACK + "The Ghost Tracker")) {
						p1.getInventory().remove(held);
						p1.getInventory().setItemInMainHand(new ItemStack(Material.COMPASS));
					}
				}
			}
		}

		if (pTarget != null) {
			if (pTarget.getWorld().getName().equalsIgnoreCase("world")) {
				pTarget.getWorld().setSpawnLocation(pTarget.getLocation().getBlockX(),
						pTarget.getLocation().getBlockY(), pTarget.getLocation().getBlockZ());

			}

		}

	}

}
