package com.coding.sirjavlux.core;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.coding.sirjavlux.entity.CustomEntityBat;

public class MovingItem {

    private Item item;
    private CustomEntityBat bat;
    private Player p;
    
    public MovingItem(Player p, ItemStack itemStack, Location location) {
        this.bat = Main.getNMSService().spawnBat(p, location);
        this.item = Main.getNMSService().spawnItem(p, itemStack.clone(), location, bat);
        this.p = p;
    }
    
    private MovingItem(Player p, Item item, CustomEntityBat bat) {
    	 this.p = p;
    	this.item = item;
    	this.bat = bat;
    }

	public void despawn() {
		if (p.isOnline()) {
			Main.getNMSService().destroyEntity(p, item);
			Main.getNMSService().destroyEntity(p, bat);	
		}
    }

    public void teleport(Location location) {
    	if (p.isOnline()) Main.getNMSService().setLocation(p, bat, location);
    }

    public MovingItem clone() {
        return new MovingItem(p, item, bat);
    }
}
