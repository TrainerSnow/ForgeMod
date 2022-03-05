package com.snow.forgemod.event.listeners;

import com.snow.forgemod.item.AutoElytra;
import com.snow.forgemod.util.Strings;
import com.snow.forgemod.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = "forgemod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler{


    @SubscribeEvent
    public static void rightClickEmpty(PlayerInteractEvent.RightClickEmpty event){
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();
        player.sendMessage(new StringTextComponent("Event fired"), net.minecraft.util.Util.DUMMY_UUID);
        if(Util.playerWearsAutoElytra(player)){
            player.sendMessage(new StringTextComponent("1"), net.minecraft.util.Util.DUMMY_UUID);
            if (player.isElytraFlying()) {
                player.sendMessage(new StringTextComponent("2"), net.minecraft.util.Util.DUMMY_UUID);
                world.addEntity(new FireworkRocketEntity(world, player.inventory.mainInventory.get(0), player));

            }
        }
    }
}
