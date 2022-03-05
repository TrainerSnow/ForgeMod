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
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            Vector3d vector3d = player.getLook(1.0F);
            double d2 = -(player.getPosX() - (player.getPosX() + vector3d.x * 4.0D));
            double d3 = -(player.getPosYHeight(1.0F) - (player.getPosYHeight(0)));
            double d4 = -(player.getPosZ() - (player.getPosZ() + vector3d.z * 4.0D));
            worldIn.playEvent(null, 1016, player.getPosition(), 0);

            FireballEntity fireballentity = new FireballEntity(worldIn, player, d2, d3, d4);
            fireballentity.explosionPower = 1;
            fireballentity.setPosition(player.getPosX() + vector3d.x * 4.0D, player.getPosYHeight(0.5D) + 0.5D, fireballentity.getPosZ() + vector3d.z * 4.0D);
            worldIn.addEntity(fireballentity);
        }
    }
}
