package com.snow.forgemod.item;

import com.snow.forgemod.util.Strings;
import com.snow.forgemod.util.Util;
import com.sun.jna.platform.unix.X11;
import javafx.stage.Screen;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AutoElytra extends ArmorItem {


    public AutoElytra(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItemStack = playerIn.inventory.getSelected();
        if (!worldIn.isClientSide){
            if (heldItemStack.getItem().equals(this)) {
                int itemSlot = Util.findSlotFromItem(playerIn, Items.FIREWORK_ROCKET);
                if (itemSlot != -1) {
                    playerIn.inventory.removeItem(itemSlot, 1);
                    if (heldItemStack.hasTag()) {
                        setCharge(heldItemStack, getCharge(heldItemStack) + 1);
                    } else {
                        setCharge(heldItemStack, 0);
                    }
                    return ActionResult.success(heldItemStack);
                }
            }
        }
        return ActionResult.fail(heldItemStack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Charge: " + getCharge(stack) ));
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        return true;
    }



    private int getCharge(ItemStack stack){
        return stack.hasTag() ? stack.getTag().getInt(Strings.ROCKET_CHARGE) : 0;
    }

    private ItemStack setCharge(ItemStack stack, int amount){
        if(stack.hasTag()){
            stack.getTag().putInt(Strings.ROCKET_CHARGE, amount);
            return stack;
        }else{
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt(Strings.ROCKET_CHARGE, 0);
            stack.setTag(nbt);
            return stack;
        }
    }
}
