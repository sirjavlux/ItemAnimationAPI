package com.coding.sirjavlux.service;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.coding.sirjavlux.entity.CustomEntityBat;

public interface ServiceWrapper {
    public CustomEntityBat spawnBat(Player player, Location location);
    public org.bukkit.entity.Item spawnItem(Player player, ItemStack itemStack, Location location, CustomEntityBat bat);
    public void destroyEntity(Player player, Entity entity);
    public void setLocation(Player player, Entity entity, Location location);
    public void sendParticle(Player player, Location location, String particleName);
    public void playSound(Player player, String sound, int pitch);
    public void changeChest(Player player, Block block, boolean open);
    public void explode(Player player, Location location);
}
