package com.snow.forgemod.block;

import com.snow.forgemod.item.ModItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;


public class CustomBlock extends Block {
    public CustomBlock(Properties properties) {
        super(properties);
    }


    @SuppressWarnings("deprecation")
    @Override
    @MethodsReturnNonnullByDefault
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        boolean hasDiamond = player.inventory.getCurrentItem().getItem().equals(Items.DIAMOND);
        boolean hasAmethyst = player.inventory.getCurrentItem().getItem().equals(new ItemStack(ModItems.AMETHYST.get()).getItem());
        int currentSlot = player.inventory.currentItem;
        BlockPos playerPos = player.getPosition();

        if(worldIn.isRemote){
            if(hasDiamond){
                player.inventory.decrStackSize(currentSlot, 1);
                worldIn.addEntity(new ItemEntity(worldIn, playerPos.getX(), playerPos.getY(), playerPos.getZ(), new ItemStack(ModItems.AMETHYST.get())));
            }
            if(hasAmethyst){
                player.inventory.decrStackSize(currentSlot, 1);
                worldIn.addEntity(new ItemEntity(worldIn, playerPos.getX(), playerPos.getY(), playerPos.getZ(), new ItemStack(Items.NETHER_STAR)));
            }
        }
        if(!worldIn.isRemote){
            if(hasDiamond){
                player.inventory.decrStackSize(currentSlot, 1);
                worldIn.addEntity(new ItemEntity(worldIn, playerPos.getX(), playerPos.getY(), playerPos.getZ(), new ItemStack(ModItems.AMETHYST.get())));
            }
            if(hasAmethyst){
                player.inventory.decrStackSize(currentSlot, 1);
                worldIn.addEntity(new ItemEntity(worldIn, playerPos.getX(), playerPos.getY(), playerPos.getZ(), new ItemStack(Items.NETHER_STAR)));
            }
        }
        return ActionResultType.SUCCESS;
    }
}
