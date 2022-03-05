package com.snow.forgemod.item;

import com.snow.forgemod.ForgeMod;
import com.snow.forgemod.custom.ModArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ForgeMod.MOD_ID);

    public static final RegistryObject<Item> FOOD_HELMET = ITEMS.register("food_helmet",
            () -> new FoodArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD ,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    public static final RegistryObject<Item> FOOD_CHESTPLATE = ITEMS.register("food_chestplate",
            () -> new FoodArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST ,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    public static final RegistryObject<Item> FOOD_LEGGINGS = ITEMS.register("food_leggings",
            () -> new FoodArmorItem(ModArmorMaterial.FOOD, EquipmentSlotType.LEGS ,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    public static final RegistryObject<Item> FOOD_BOOTS = ITEMS.register("food_boots",
            () -> new FoodArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET ,new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    public static final RegistryObject<Item> AUTO_ELYTRA = ITEMS.register("auto_elytra",
            () -> new AutoElytra(ArmorMaterial.DIAMOND ,EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
