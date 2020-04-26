package com.coding.sirjavlux.entity;

import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.EntityBat;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.World;

import com.coding.sirjavlux.core.NMSService;

public class CustomEntityBat extends EntityBat {

    public CustomEntityBat(Location location) {
        super(NMSService.getByBukkitWorld(location.getWorld()));

        super.motX = 0.0;
        super.motY = 0.0;
        super.motZ = 0.0;
        this.a(0.0f, 0.0f);
        super.a(new AxisAlignedBB(0,0,0,0,0,0));
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
