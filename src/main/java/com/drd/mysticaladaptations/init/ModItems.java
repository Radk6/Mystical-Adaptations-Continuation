package com.drd.mysticaladaptations.init;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagradditions.item.EssencePaxelItem;
import com.blakebr0.mysticalagriculture.item.EssenceWateringCanItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceBootsItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceChestplateItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceHelmetItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceLeggingsItem;
import com.blakebr0.mysticalagriculture.item.tool.*;
import com.drd.mysticaladaptations.MysticalAdaptations;
import com.drd.mysticaladaptations.api.crop.ModCropTier;
import com.drd.mysticaladaptations.item.InsaniumUpgradeItem;
import com.drd.mysticaladaptations.lib.ModItemTier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ModItems {
    public static final List<Supplier<BlockItem>> BLOCK_ENTRIES = new ArrayList<>();
    public static final Map<DeferredHolder<Item, Item>, Supplier<Item>> ENTRIES = new LinkedHashMap<>();
    public static final Map<DeferredHolder<Item, Item>, Supplier<Item>> GEAR_ENTRIES = new LinkedHashMap<>();

    public static final DeferredHolder<Item, Item> INSANIUM_UPGRADE = register("insanium_upgrade", InsaniumUpgradeItem::new);
    public static final DeferredHolder<Item, Item> INSANIUM_SWORD = registerGear("insanium_sword", () -> new EssenceSwordItem(ModItemTier.INSANIUM, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_PICKAXE = registerGear("insanium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.INSANIUM, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_SHOVEL = registerGear("insanium_shovel", () -> new EssenceShovelItem(ModItemTier.INSANIUM, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_AXE = registerGear("insanium_axe", () -> new EssenceAxeItem(ModItemTier.INSANIUM, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_HOE = registerGear("insanium_hoe", () -> new EssenceHoeItem(ModItemTier.INSANIUM, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_STAFF = registerGear("insanium_staff", () -> new EssenceStaffItem(6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_WATERING_CAN = registerGear("insanium_watering_can", () -> new EssenceWateringCanItem(11, 0.45, ModCropTier.SIX.getTextColor()));
    public static final DeferredHolder<Item, Item> INSANIUM_BOW = registerGear("insanium_bow", () -> new EssenceBowItem(ModItemTier.INSANIUM, 6, 2, 1.80F, 3.0F));
    public static final DeferredHolder<Item, Item> INSANIUM_CROSSBOW = registerGear("insanium_crossbow", () -> new EssenceCrossbowItem(ModItemTier.INSANIUM, 6, 2, 1.80F, 3.0F));
    public static final DeferredHolder<Item, Item> INSANIUM_SHEARS = registerGear("insanium_shears", () -> new EssenceShearsItem(ModItemTier.INSANIUM, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_FISHING_ROD = registerGear("insanium_fishing_rod", () -> new EssenceFishingRodItem(ModItemTier.INSANIUM, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_SICKLE = registerGear("insanium_sickle", () -> new EssenceSickleItem(ModItemTier.INSANIUM, 7, ModCropTier.SIX.getTextColor(), 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_SCYTHE = registerGear("insanium_scythe", () -> new EssenceScytheItem(ModItemTier.INSANIUM, 7, ModCropTier.SIX.getTextColor(), 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_PAXEL = registerGear("insanium_paxel", () -> new EssencePaxelItem(ModItemTier.INSANIUM, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_HELMET = registerGear("insanium_helmet", () -> new EssenceHelmetItem(ModArmorMaterials.INSANIUM, 400, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_CHESTPLATE = registerGear("insanium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterials.INSANIUM, 400, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_LEGGINGS = registerGear("insanium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterials.INSANIUM, 400, 6, 2));
    public static final DeferredHolder<Item, Item> INSANIUM_BOOTS = registerGear("insanium_boots", () -> new EssenceBootsItem(ModArmorMaterials.INSANIUM, 400, 6, 2));

    @SubscribeEvent
    public void onRegisterItems(RegisterEvent event) {
        event.register(Registries.ITEM, registry -> {
            BLOCK_ENTRIES.stream().map(Supplier::get).forEach(item -> {
                var id = BuiltInRegistries.BLOCK.getKey(item.getBlock());
                registry.register(id, item);
            });

            ENTRIES.forEach((reg, item) -> {
                registry.register(reg.getId(), item.get());
            });

            GEAR_ENTRIES.forEach((reg, item) -> {
                registry.register(reg.getId(), item.get());
            });
        });
    }

    private static DeferredHolder<Item, Item> register(String name) {
        return register(name, BaseItem::new);
    }

    private static DeferredHolder<Item, Item> register(String name, Supplier<Item> item) {
        var id = MysticalAdaptations.resource(name);
        var holder = DeferredHolder.create(Registries.ITEM, id);
        ENTRIES.put(holder, item);
        return holder;
    }

    private static DeferredHolder<Item, Item> registerGear(String name, Supplier<? extends Item> item) {
        var id = MysticalAdaptations.resource(name);
        var holder = DeferredHolder.create(Registries.ITEM, id);
        GEAR_ENTRIES.put(holder, item::get);
        return holder;
    }
}
