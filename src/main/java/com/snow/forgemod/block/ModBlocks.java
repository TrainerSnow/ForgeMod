package com.snow.forgemod.block;

import com.snow.forgemod.ForgeMod;
import com.snow.forgemod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ForgeMod.MOD_ID);

    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(4).harvestTool(ToolType.HOE)));

    public static final RegistryObject<Block> CUSTOM_BLOCK = registerBlock("custom_block",
            () -> new CustomBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(4).harvestTool(ToolType.AXE)));

    public static final RegistryObject<Block> POOP_BLOCK = registerBlock("poop_block",
            () -> new PoopBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(4).harvestTool(ToolType.SHOVEL)));

    public static final RegistryObject<Block> POOP_CRAFTER_BLOCK = registerBlock("poop_crafter_block",
            () -> new PoopCrafterBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(4).harvestTool(ToolType.PICKAXE)));

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private static <T extends Block>RegistryObject<T>registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(ItemGroup.MISC)));
    }


}
