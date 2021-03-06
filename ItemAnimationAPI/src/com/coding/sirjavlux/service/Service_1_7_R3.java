package com.coding.sirjavlux.service;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.coding.sirjavlux.core.MovingItem;
import com.coding.sirjavlux.entity.CustomEntityBat;
import com.coding.sirjavlux.entity.CustomEntityBat_1_7_R3;

import net.minecraft.server.v1_7_R3.EntityItem;
import net.minecraft.server.v1_7_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_7_R3.*;

public class Service_1_7_R3 implements ServiceWrapper {
    @Override
    public CustomEntityBat spawnBat(Player player, Location location) {
        CustomEntityBat bat = new CustomEntityBat_1_7_R3(location);
        Entity entity = ((CraftEntity) bat.getBukkitEntity()).getHandle();
        entity.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
        PacketPlayOutSpawnEntityLiving spawnEntity = new PacketPlayOutSpawnEntityLiving((EntityLiving) entity);
        sendPacket(spawnEntity, player);

        entity.setInvisible(true);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(entity.getId(), entity.getDataWatcher(), true);
        sendPacket(metadata, player);

        return bat;
    }
    @Override 
    public void setItemStack(MovingItem mItem) {
    	EntityItem item = (EntityItem) ((CraftEntity) mItem.getItem()).getHandle();
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(item.getId(), item.getDataWatcher(), true);
        sendPacket(metadata, mItem.getPlayer());
    }
    @Override
    public org.bukkit.entity.Item spawnItem(Player player, ItemStack itemStack, Location location, CustomEntityBat bat) {
        EntityItem item = new EntityItem(((CraftWorld) location.getWorld()).getHandle());
        Entity entity = ((CraftEntity) bat.getBukkitEntity()).getHandle();
        item.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
        item.setItemStack(CraftItemStack.asNMSCopy(itemStack));
        PacketPlayOutSpawnEntity spawnEntity = new PacketPlayOutSpawnEntity(item, 2, 1);
        sendPacket(spawnEntity, player);

        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(item.getId(), item.getDataWatcher(), true);
        sendPacket(metadata, player);

        entity.passenger = item;
        PacketPlayOutAttachEntity attachEntity = new PacketPlayOutAttachEntity(0, item, entity);
        sendPacket(attachEntity, player);
        return (org.bukkit.entity.Item) item.getBukkitEntity();
    }
    @Override
    public void destroyEntity(Player player, org.bukkit.entity.Entity entity) {
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(((CraftEntity) entity).getHandle().getId());
        sendPacket(destroy, player);
    }
    @Override
    public void setLocation(Player player, org.bukkit.entity.Entity entityBukkit, Location location) {
    	Entity entity = ((CraftEntity) entityBukkit).getHandle();
        entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(entity);
        sendPacket(teleport, player);
    }
    @Override
    public void sendParticle(Player player, Location location, String particleName) {
        PacketPlayOutWorldParticles particle = new PacketPlayOutWorldParticles(
                particleName.toUpperCase(),
                (float) location.getX(),
                (float) location.getY(),
                (float) location.getZ(),
                0, 0, 0, 1, 0);
        sendPacket(particle, player);
    }
    @Override
    public void playSound(Player player, String sound, int pitch) {
        PacketPlayOutNamedSoundEffect soundEffect = new PacketPlayOutNamedSoundEffect(sound,
                player.getLocation().getX(),
                player.getLocation().getY(),
                player.getLocation().getZ(),
                1,
                pitch);
        sendPacket(soundEffect, player);
    }
    @Override
    public void explode(Player player, Location location) {
        PacketPlayOutExplosion explosion = new PacketPlayOutExplosion(
                location.getX(),
                location.getY(),
                location.getZ(),
                4,
                new ArrayList<>(),
                Vec3D.a(0, 0, 0)
        );
        sendPacket(explosion, player);
    }
    
	public void sendPacket(Packet packet, Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
