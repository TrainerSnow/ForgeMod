package com.snow.forgemod.item;

import com.snow.forgemod.item.ModItems;
import com.snow.forgemod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class FoodArmorItem extends ArmorItem {

    private short iteration = 0;


    public FoodArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        iteration++;

        if(iteration == 20){
            iteration = 0;
            if(Util.playerHasFullFoodArmor(player) && player.getFoodData().getFoodLevel() < 15){
                Util.sendMessageToPlayer(player, "GUT GEMACHT", world);

                consumeFoodItem(player, world);
                notifyPlayerFoodEaten(player, world);
            }
        }
    }

    private void notifyPlayerFoodEaten(PlayerEntity player, World world) {
        Util.sendMessageToPlayer(player, "Essen gegessen", world);
    }

    private void consumeFoodItem(PlayerEntity player, World world) {
        for(int i = 0; i < player.inventory.items.size(); i++){
            Item item = player.inventory.items.get(i).getItem();
            if(item.isEdible()){
                player.eat(world, new ItemStack(item));
                player.inventory.removeItem(i, 1);
                player.sendMessage(new StringTextComponent("You just ate one " + item.getName(new ItemStack(item)).getString()), net.minecraft.util.Util.NIL_UUID);

            }
        }
    }


}
