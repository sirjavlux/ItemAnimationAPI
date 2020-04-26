package com.coding.sirjavlux.entity;

import net.minecraft.server.v1_7_R4.EntityBat;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;

public class CustomEntityBat_1_7_R4 extends EntityBat implements CustomEntityBat {

    public CustomEntityBat_1_7_R4(Location location) {
    	super(((CraftWorld) location.getWorld()).getHandle());
        super.motX = 0.0;
        super.motY = 0.0;
        super.motZ = 0.0;
        this.a(0.0f, 0.0f);
        setAsleep(false);
    }

    @Override
    public void b(final NBTTagCompound nbttagcompound) {
    }

    @Override
    public boolean c(final NBTTagCompound nbttagcompound) {
        return false;
    }

    @Override
    public boolean d(final NBTTagCompound nbttagcompound) {
        return false;
    }

    @Override
    public void e(final NBTTagCompound nbttagcompound) {
    }
    @Override
    public void makeSound(final String sound, final float f1, final float f2) {
    }

    public Location getLocation(World world) {
        return new Location(world, locX, locY, locZ);
    }
}
