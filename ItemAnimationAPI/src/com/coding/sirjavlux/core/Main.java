package com.coding.sirjavlux.core;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.coding.sirjavlux.entity.CustomEntityBat;
import com.coding.sirjavlux.entity.RegisterEntity;

public class Main extends JavaPlugin {

	private static NMSService nmsService;
	private String consolePrefix = ChatColor.GRAY + "[ItemAnimationAPI]";
	
	@Override
	public void onEnable() {
		nmsService = new NMSService();
		RegisterEntity.registerEntity("Bat", 65, CustomEntityBat.class);
		
		this.getServer().getConsoleSender().sendMessage(consolePrefix + ChatColor.GREEN + " Successfully enabled!");
	}
	
	@Override
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(consolePrefix + ChatColor.RED + " Disabled!");
	}
	
	public static NMSService getNMSService() {
		return nmsService;
	}
}
