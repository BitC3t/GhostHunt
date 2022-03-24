package me.floof.here.listeners;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setResourcePack("https://www.dropbox.com/s/brgfxm7ojvobz53/Floof%27s%20Custom%20Pack.zip?dl=1");
	}

	@EventHandler
	public void dropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		ItemStack invisStick = new ItemStack(Material.STICK, 1);
		invisStick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		ItemMeta stickmeta = invisStick.getItemMeta();
		stickmeta.setCustomModelData(1);
		stickmeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.GREEN + "The Ghost Light");
		stickmeta.setLore(
				Arrays.asList(ChatColor.AQUA + "Makes you invisible for 8 seconds, cooldown lasts for 30 seconds."));

		invisStick.setItemMeta(stickmeta);

		if (e.getItemDrop().getItemStack().getType().equals(Material.COMPASS)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You cannot drop this item!");
		}
		if (e.getItemDrop().getItemStack().equals(invisStick)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You cannot drop this item!");
		}
	}

}
