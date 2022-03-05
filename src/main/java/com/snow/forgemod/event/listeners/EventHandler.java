package com.snow.forgemod.event.listeners;

import com.snow.forgemod.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = "forgemod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler{


    @SubscribeEvent
    public static void rightClickEmpty(PlayerInteractEvent.RightClickEmpty event){
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();
        FireworkRocketEntity fw;
        if(Util.playerWearsAutoElytra(player) && player.isFallFlying() && !world.isClientSide)
             fw = new FireworkRocketEntity(world, Items.FIREWORK_ROCKET.getDefaultInstance(), player);

    }
}

