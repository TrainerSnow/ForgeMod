package com.snow.forgemod.item;

import com.snow.forgemod.block.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class PoopItem extends Item {
    public PoopItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        BlockPos pos = playerIn.getPosition();
        pos = pos.offset(Direction.DOWN, 1);


        if(worldIn.isRemote()){
            worldIn.setBlockState(pos, ModBlocks.POOP_BLOCK.get().getDefaultState());
        }
        if(!worldIn.isRemote()){
            ITextComponent message = new StringTextComponent("BliBlaBlub");
            playerIn.sendMessage(message, playerIn.getUniqueID());
            worldIn.setBlockState(pos, ModBlocks.POOP_BLOCK.get().getDefaultState());
        }

        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}
