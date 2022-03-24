package me.floof.here.listeners;

import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.floof.here.main.GhostHuntMain;
import net.md_5.bungee.api.ChatColor;

public class InteractEvent implements Listener {

	HashMap<String, Long> inUse = new HashMap<String, Long>();
	int diff;

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		// Stick Meta
		ItemStack invisStick = new ItemStack(Material.STICK, 1);
		invisStick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		ItemMeta stickmeta = invisStick.getItemMeta();
		stickmeta.setCustomModelData(1);
		stickmeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.GREEN + "The Ghost Light");
		stickmeta.setLore(
				Arrays.asList(ChatColor.AQUA + "Makes you invisible for 8 seconds, cooldown lasts for 30 seconds."));

		invisStick.setItemMeta(stickmeta);

		if (p.getInventory().getItemInMainHand().equals(invisStick)) {
			char c = '\u27BE';
			if (inUse.containsKey(p.getName())) {

				p.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.RED + "You cannot use this effect for "
						+ TimeDiff(p.getName()) + " seconds.");
			}
			if (!(inUse.containsKey(p.getName()))) {
				Bukkit.broadcastMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.RED + p.getName()
						+ " has activated the Ghost Light Ability.");

				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"effect give " + p.getName() + " minecraft:invisibility 8 2 false");
				inUse.put(p.getName(), System.currentTimeMillis());
				TimeCheck(p.getName());

			}

		}
	}

	public void TimeCheck(final String s) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(GhostHuntMain.getInstance(), new Runnable() {

			@Override
			public void run() {
				inUse.remove(s);
				Player p = Bukkit.getPlayer(s);
				char c = '\u27BE';
				p.sendMessage(ChatColor.GOLD + "[" + c + "] " + ChatColor.GREEN
						+ "The Ghost Light Ability has been replenished and can be used again.");

			}
		}, 600L);

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(GhostHuntMain.getInstance(), new Runnable() {

			@Override
			public void run() {
				Player p = Bukkit.getPlayer(s);
				char c = '\u27BE';
				p.sendMessage(
						ChatColor.GOLD + "[" + c + "] " + ChatColor.RED + "You have lost your invisiblity effect.");
			}
		}, 160L);

	}

	public int TimeDiff(String s) {
		int difference = (30 - ((int) (System.currentTimeMillis() - inUse.get(s))) / 1000);

		return difference;

	}
}
