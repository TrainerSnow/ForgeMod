package com.snow.forgemod.util;

import com.snow.forgemod.item.AutoElytra;
import com.snow.forgemod.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Random;

public class Util {

    public static void sendMessageToPlayer(PlayerEntity player, String message, World world){
        if(world.isClientSide){
            ITextComponent tC = new StringTextComponent(message);
            player.displayClientMessage(tC, true);
        }
    }

    public static boolean chance(int percentage){
        int i = (int) (Math.random() * 100);
        return i < percentage;
    }

    public static int random(int bound){
        return (int) (Math.random() * bound);
    }

    public static boolean playerHasFullFoodArmor(PlayerEntity player){
        return
                player.inventory.armor.get(3).getItem().equals(ModItems.FOOD_HELMET.get()) &&
                        player.inventory.armor.get(2).getItem().equals(ModItems.FOOD_CHESTPLATE.get()) &&
                        player.inventory.armor.get(1).getItem().equals(ModItems.FOOD_LEGGINGS.get()) &&
                        player.inventory.armor.get(0).getItem().equals(ModItems.FOOD_BOOTS.get());
    }

    public static int findSlotFromItem(PlayerEntity player, Item item){
        for(int i = 0; i < player.inventory.items.size(); i++){
            if(player.inventory.items.get(i).getItem().equals(item)){
                return i;
            }
        }
        return -1;
    }

    public static boolean playerWearsAutoElytra(PlayerEntity player){
        return player.inventory.armor.get(2).getItem() instanceof AutoElytra;
    }
}
