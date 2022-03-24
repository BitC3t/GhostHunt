package me.floof.here.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class OnDeath implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();

		if (board.getPlayerTeam(p).getName().equals("Ghost")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a times 20 200 20");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"title @a title {\"text\":\"Hunters Win!\",\"color\":\"gold\"}");

		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();

		if (board.getPlayerTeam(p).getName().equals("Hunter")) {
			p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
		}

		if (board.getPlayerTeam(p).getName().equals("Hunter")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"kill @e[type=item, nbt={Item:{id:\"minecraft:compass\"}}]");
		}
	}

}
