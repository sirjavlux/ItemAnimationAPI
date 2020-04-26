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
        this.bat = Main.getVersionManager().getService().spawnBat(p, location);
        this.item = Main.getVersionManager().getService().spawnItem(p, itemStack.clone(), location, bat);
        this.p = p;
    }
    
    private MovingItem(Player p, Item item, CustomEntityBat bat) {
    	 this.p = p;
    	this.item = item;
    	this.bat = bat;
    }

	public void despawn() {
		if (p.isOnline()) {
			Main.getVersionManager().getService().destroyEntity(p, item);
			Main.getVersionManager().getService().destroyEntity(p, bat.getBukkitEntity());	
		}
    }

    public void teleport(Location location) {
    	if (p.isOnline()) Main.getVersionManager().getService().setLocation(p, bat.getBukkitEntity(), location);
    }

    public MovingItem clone() {
        return new MovingItem(p, item, bat);
    }
    
    public void setItemStack(ItemStack item) {
    	this.item.setItemStack(item);
    	Main.getVersionManager().getService().setItemStack(this);
    }
    
    public Item getItem() {
    	return item;
    }
    
    public Player getPlayer() {
    	return p;
    }
}
