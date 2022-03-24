package me.floof.here.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.floof.here.commands.HunterTeam;
import me.floof.here.commands.ResetTeams;
import me.floof.here.commands.StartGame;
import me.floof.here.listeners.CompassEvent;
import me.floof.here.listeners.InteractEvent;
import me.floof.here.listeners.OnDeath;
import me.floof.here.listeners.OnJoin;

public class GhostHuntMain extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info(
				"Ghost Hunt Plugin developed by Floof#8983. All rights reserved, no redistribution allowed. If any bugs / reports are seen, please report them to Floo__f on Twitter / Floof#8983 on Discord.");

		// Listeners
		this.getServer().getPluginManager().registerEvents(new InteractEvent(), this);
		this.getServer().getPluginManager().registerEvents(new CompassEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OnDeath(), this);
		this.getServer().getPluginManager().registerEvents(new OnJoin(), this);

		// Commands
		this.getCommand("hunter").setExecutor(new HunterTeam(this));
		this.getCommand("ghost").setExecutor(new HunterTeam(this));
		this.getCommand("resetteams").setExecutor(new ResetTeams(this));
		this.getCommand("startgame").setExecutor(new StartGame(this));
	}

	@Override
	public void onDisable() {

	}

	public static JavaPlugin getInstance() {
		return JavaPlugin.getPlugin(GhostHuntMain.class);

	}
}
