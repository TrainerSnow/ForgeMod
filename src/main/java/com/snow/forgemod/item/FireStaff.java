package com.snow.forgemod.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class FireStaff extends BowItem {

    public FireStaff(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            Vector3d vector3d = player.getViewVector(1.0F);
            double d2 = -(player.getX() - (player.getX() + vector3d.x * 4.0D));
            double d3 = -(player.getY(1.0F) - (player.getY(0)));
            double d4 = -(player.getZ() - (player.getZ() + vector3d.z * 4.0D));
            worldIn.levelEvent(null, 1016, player.blockPosition(), 0);

            FireballEntity fireballentity = new FireballEntity(worldIn, player, d2, d3, d4);
            fireballentity.explosionPower = 1;
            fireballentity.setPos(player.getX() + vector3d.x * 4.0D, player.getY(0.5D) + 0.5D, fireballentity.getZ() + vector3d.z * 4.0D);
            worldIn.addFreshEntity(fireballentity);
        }
    }
}
