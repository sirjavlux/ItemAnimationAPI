package com.coding.sirjavlux.core;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.coding.sirjavlux.entity.CustomEntityBat;

import java.util.ArrayList;

public class NMSService {

    public static World getByBukkitWorld(org.bukkit.World world) {
        return ((CraftWorld) world).getHandle();
    }

    public CustomEntityBat spawnBat(Player player, Location location) {
        CustomEntityBat bat = new CustomEntityBat(location);
        bat.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
        PacketPlayOutSpawnEntityLiving spawnEntity = new PacketPlayOutSpawnEntityLiving(bat);
        sendPacket(spawnEntity, player);

        bat.setInvisible(true);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(bat.getId(), bat.getDataWatcher(), true);
        sendPacket(metadata, player);

        return bat;
    }

    public org.bukkit.entity.Item spawnItem(Player player, ItemStack itemStack, Location location, CustomEntityBat bat) {
        EntityItem item = new EntityItem(((CraftWorld) location.getWorld()).getHandle());
        item.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
        item.setItemStack(CraftItemStack.asNMSCopy(itemStack));
        PacketPlayOutSpawnEntity spawnEntity = new PacketPlayOutSpawnEntity(item, 2, 1);
        sendPacket(spawnEntity, player);

        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(item.getId(), item.getDataWatcher(), true);
        sendPacket(metadata, player);

        bat.passenger = item;
        PacketPlayOutAttachEntity attachEntity = new PacketPlayOutAttachEntity(0, item, bat);
        sendPacket(attachEntity, player);
        return (org.bukkit.entity.Item) item.getBukkitEntity();
    }

    public void destroyEntity(Player player, org.bukkit.entity.Entity entity) {
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(((CraftEntity) entity).getHandle().getId());
        sendPacket(destroy, player);
    }

    public void destroyEntity(Player player, Entity entity) {
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(entity.getId());
        sendPacket(destroy, player);
    }
    
    public void setLocation(Player player, Entity entity, Location location) {
        entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(entity);
        sendPacket(teleport, player);
    }

    public void sendParticle(Player player, Location location, String particleName) {
        PacketPlayOutWorldParticles particle = new PacketPlayOutWorldParticles(
                EnumParticle.valueOf(particleName.toUpperCase()),
                true,
                (float) location.getX(),
                (float) location.getY(),
                (float) location.getZ(),
                0, 0, 0, 1, 0);
        sendPacket(particle, player);
    }

    public void playSound(Player player, String sound, int pitch) {
        PacketPlayOutNamedSoundEffect soundEffect = new PacketPlayOutNamedSoundEffect(sound,
                player.getLocation().getX(),
                player.getLocation().getY(),
                player.getLocation().getZ(),
                1,
                pitch);
        sendPacket(soundEffect, player);
    }

    public void changeChest(Player player, Block block, boolean open) {
        PacketPlayOutBlockAction blockAction = null;
        try {
            blockAction = new PacketPlayOutBlockAction(
                    new BlockPosition(
                            block.getLocation().getBlockX(),
                            block.getLocation().getBlockY(),
                            block.getLocation().getBlockZ()),
                    CraftMagicNumbers.getBlock(block),
                    1,
                    open ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sendPacket(blockAction, player);
    }

    public void explode(Player player, Location location) {
        PacketPlayOutExplosion explosion = new PacketPlayOutExplosion(
                location.getX(),
                location.getY(),
                location.getZ(),
                4,
                new ArrayList<>(),
                new Vec3D(0, 0, 0)
        );
        sendPacket(explosion, player);
    }

    @SuppressWarnings("rawtypes")
	private void sendPacket(Packet packet, Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
