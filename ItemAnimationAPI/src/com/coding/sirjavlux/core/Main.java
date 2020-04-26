package com.coding.sirjavlux.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.coding.sirjavlux.entity.CustomEntityBat;
import com.coding.sirjavlux.entity.RegisterEntity;

public class Main extends JavaPlugin implements Listener {

	private static NMSService nmsService;
	private String consolePrefix = ChatColor.GRAY + "[ItemAnimationAPI]";
	
	@Override
	public void onEnable() {
		nmsService = new NMSService();
		RegisterEntity.registerEntity("Bat", 65, CustomEntityBat.class);
		
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getConsoleSender().sendMessage(consolePrefix + ChatColor.GREEN + " Successfully enabled!");
	}
	
	@Override
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(consolePrefix + ChatColor.RED + " Disabled!");
	}
	
	public static NMSService getNMSService() {
		return nmsService;
	}
	
	@EventHandler 
	public void pJoin(PlayerJoinEvent e) {
		//plugin test animation
		new BukkitRunnable() {
			Location center = e.getPlayer().getLocation().clone();
			double r = 1.3;
			MovingItem item = new MovingItem(e.getPlayer(), new ItemStack(Material.ANVIL), center);
			int degrees = 0;
			@Override
			public void run() {
				degrees += 2;
				double angle = degrees * Math.PI / 180;
	            double x = center.getX() + r * Math.cos(angle);
	            double z = center.getZ() + r * Math.sin(angle);
	            double y = center.getY();
	            item.teleport(new Location(Bukkit.getWorld("world"), x, y, z));
				if (degrees >= 360) degrees = 0;
			}
			
		}.runTaskTimer(this, 1, 1);
	}
}
