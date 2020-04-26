package com.coding.sirjavlux.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.coding.sirjavlux.entity.CustomEntityBat;

public class MovingItem {

    private Item item;
    private CustomEntityBat bat;
    private List<Player> players;
    
    public MovingItem(Player p, ItemStack itemStack, Location location) {
        this.bat = Main.getNMSService().spawnBat(p, location);
        this.item = Main.getNMSService().spawnItem(p, itemStack.clone(), location, bat);
        this.players = new ArrayList<>();
        players.add(p);
    }

    public MovingItem(ItemStack itemStack, Location location) {
        this.players = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
        	this.bat = Main.getNMSService().spawnBat(p, location);
        	this.item = Main.getNMSService().spawnItem(p, itemStack.clone(), location, bat);
            players.add(p);
        }
    }
    
    public MovingItem(List<Player> players, ItemStack itemStack, Location location) {
        this.players = new ArrayList<>();
        for (Player p : players) {
        	this.bat = Main.getNMSService().spawnBat(p, location);
        	this.item = Main.getNMSService().spawnItem(p, itemStack.clone(), location, bat);
            players.add(p);
        }
    }
    
    private MovingItem(List<Player> players, Item item, CustomEntityBat bat) {
    	this.players = players;
    	this.item = item;
    	this.bat = bat;
    }

	public void despawn() {
		for (Player p : players) {
			if (p.isOnline()) {
				Main.getNMSService().destroyEntity(p, item);
				Main.getNMSService().destroyEntity(p, bat);	
			}
		}
    }

    public void teleport(Location location) {
    	for (Player p : players) {
    		if (p.isOnline()) Main.getNMSService().setLocation(p, bat, location);
    	}
    }

    public MovingItem clone() {
        return new MovingItem(players, item, bat);
    }
}
