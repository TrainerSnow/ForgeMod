package com.snow.forgemod.block;

import com.snow.forgemod.item.ModItems;
import com.snow.forgemod.util.Util;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.HashMap;

public class PoopCrafterBlock extends Block {

    private static final HashMap<Integer, String> errorMessages = new HashMap<>();
    private static final int DIAMOND_ERROR = 0;

    public PoopCrafterBlock(Properties properties) {
        super(properties);
        errorMessages.put(DIAMOND_ERROR, "Du musst ein Diamant in der Hand halten.");
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!usesDiamond(player)){
            showError(DIAMOND_ERROR ,player, worldIn);
            return ActionResultType.FAIL;
        }
        if(!arrangementIsRight(worldIn, pos ,player)){
            return ActionResultType.FAIL;
        }

        ItemStack stack = player.inventory.getCurrentItem();
        startCraftingProcess(worldIn, pos);
        summonRocket(stack, worldIn, hit);
        return ActionResultType.SUCCESS;
    }

    private void summonRocket(ItemStack stack, World worldIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            Vector3d vector3d = hit.getHitVec();
            Direction direction = hit.getFace();
            FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(worldIn, null, vector3d.x + (double) direction.getXOffset() * 0.15D, vector3d.y + (double) direction.getYOffset() * 0.15D, vector3d.z + (double) direction.getZOffset() * 0.15D, stack);
            worldIn.addEntity(fireworkrocketentity);
        }
    }

    private void startCraftingProcess(World worldIn, BlockPos pos) {
        craftPoop(worldIn, pos);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.ASH, d0, d1, d2, 0.5D, 0.5D, 0.5D);
    }

    private void craftPoop(World worldIn, BlockPos pos) {
        ItemEntity toSpawnPoop = new ItemEntity(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.POOP_ITEM.get()));
        if(!worldIn.isRemote()){
            worldIn.addEntity(toSpawnPoop);
        }
        if(worldIn.isRemote()){
                worldIn.addEntity(toSpawnPoop);
        }
    }

    private boolean arrangementIsRight(World worldIn, BlockPos pos, PlayerEntity player) {
        return goldBlocks(worldIn, pos, player) && ironBlocks(worldIn, pos, player) && lapisBlocks(worldIn, pos, player);
    }

    private boolean lapisBlocks(World worldIn, BlockPos pos, PlayerEntity player) {

        BlockPos posOne = pos.offset(Direction.NORTH, 2).offset(Direction.EAST, 2);
        boolean cornerOne = worldIn.getBlockState(posOne).equals(Blocks.LAPIS_BLOCK.getDefaultState());

        BlockPos posTwo = pos.offset(Direction.NORTH, 2).offset(Direction.WEST, 2);
        boolean cornerTwo = worldIn.getBlockState(posTwo).equals(Blocks.LAPIS_BLOCK.getDefaultState());

        BlockPos posThree = pos.offset(Direction.SOUTH, 2).offset(Direction.EAST, 2);
        boolean cornerThree = worldIn.getBlockState(posThree).equals(Blocks.LAPIS_BLOCK.getDefaultState());

        BlockPos posFour = pos.offset(Direction.SOUTH, 2).offset(Direction.WEST, 2);
        boolean cornerFour = worldIn.getBlockState(posFour).equals(Blocks.LAPIS_BLOCK.getDefaultState());

        boolean isWellSetup = cornerOne && cornerTwo && cornerThree && cornerFour;

        if(!isWellSetup){
            Util.sendMessageToPlayer(player, "Denke daran die Lapislazuliblöcke richtig zu platzieren!", worldIn);
        }

        return isWellSetup;
    }

    private boolean ironBlocks(World worldIn, BlockPos pos, PlayerEntity player) {
        pos = pos.offset(Direction.DOWN);

        BlockPos posOne = pos.offset(Direction.NORTH, 1).offset(Direction.EAST, 1);
        boolean cornerOne = worldIn.getBlockState(posOne).equals(Blocks.IRON_BLOCK.getDefaultState());

        BlockPos posTwo = pos.offset(Direction.NORTH, 1).offset(Direction.WEST, 1);
        boolean cornerTwo = worldIn.getBlockState(posTwo).equals(Blocks.IRON_BLOCK.getDefaultState());

        BlockPos posThree = pos.offset(Direction.SOUTH, 1).offset(Direction.EAST, 1);
        boolean cornerThree = worldIn.getBlockState(posThree).equals(Blocks.IRON_BLOCK.getDefaultState());

        BlockPos posFour = pos.offset(Direction.SOUTH, 1).offset(Direction.WEST, 1);
        boolean cornerFour = worldIn.getBlockState(posFour).equals(Blocks.IRON_BLOCK.getDefaultState());

        boolean isWellSetup = cornerOne && cornerTwo && cornerThree && cornerFour;

        if(!isWellSetup){
            Util.sendMessageToPlayer(player, "Denke daran die Eisenblöcke richtig zu platzieren!", worldIn);
        }

        return isWellSetup;
    }

    private boolean goldBlocks(World worldIn, BlockPos pos, PlayerEntity player) {
        pos = pos.offset(Direction.DOWN);

        BlockPos posOne = pos.offset(Direction.NORTH, 1);
        boolean cornerOne = worldIn.getBlockState(posOne).equals(Blocks.GOLD_BLOCK.getDefaultState());

        BlockPos posTwo = pos.offset(Direction.SOUTH, 1);
        boolean cornerTwo = worldIn.getBlockState(posTwo).equals(Blocks.GOLD_BLOCK.getDefaultState());

        BlockPos posThree = pos.offset(Direction.EAST, 1);
        boolean cornerThree = worldIn.getBlockState(posThree).equals(Blocks.GOLD_BLOCK.getDefaultState());

        BlockPos posFour = pos.offset(Direction.WEST, 1);
        boolean cornerFour = worldIn.getBlockState(posFour).equals(Blocks.GOLD_BLOCK.getDefaultState());

        boolean isWellSetup = cornerOne && cornerTwo && cornerThree && cornerFour;

        if(!isWellSetup){
            Util.sendMessageToPlayer(player, "Denke daran die Goldblöcke richtig zu platzieren!", worldIn);
        }

        return isWellSetup;
    }

    private boolean usesDiamond(PlayerEntity player) {
        return player.inventory.getCurrentItem().getItem().equals(Items.DIAMOND);
    }

    private void showError(int errorType, PlayerEntity player, World world){
        Util.sendMessageToPlayer(player, errorMessages.get(errorType), world);
    }
}
