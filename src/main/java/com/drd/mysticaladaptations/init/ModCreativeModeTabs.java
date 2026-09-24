package com.drd.mysticaladaptations.init;

import com.blakebr0.cucumber.util.FeatureFlagDisplayItemGenerator;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.drd.mysticaladaptations.MysticalAdaptations;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MysticalAdaptations.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = REGISTRY.register("creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mysticaladaptations"))
            .icon(() -> new ItemStack(ModItems.INSANIUM_SWORD.get()))
            .displayItems(FeatureFlagDisplayItemGenerator.create((parameters, output) -> {
                output.accept(ModItems.INSANIUM_UPGRADE);
                output.accept(ModItems.INSANIUM_SWORD);
                output.accept(ModItems.INSANIUM_PICKAXE);
                output.accept(ModItems.INSANIUM_SHOVEL);
                output.accept(ModItems.INSANIUM_AXE);
                output.accept(ModItems.INSANIUM_HOE);
                //output.accept(ModItems.INSANIUM_STAFF);
                output.accept(ModItems.INSANIUM_WATERING_CAN);
                output.accept(ModItems.INSANIUM_BOW);
                output.accept(ModItems.INSANIUM_CROSSBOW);
                output.accept(ModItems.INSANIUM_SHEARS);
                output.accept(ModItems.INSANIUM_FISHING_ROD);
                output.accept(ModItems.INSANIUM_SICKLE);
                output.accept(ModItems.INSANIUM_SCYTHE);
                output.accept(ModItems.INSANIUM_PAXEL);
                output.accept(ModItems.INSANIUM_HELMET);
                output.accept(ModItems.INSANIUM_CHESTPLATE);
                output.accept(ModItems.INSANIUM_LEGGINGS);
                output.accept(ModItems.INSANIUM_BOOTS);

                var registry = MysticalAgricultureAPI.getAugmentRegistry();
                for (var augment : registry.getAugments()) {
                    var id = augment.getId();
                    if (id != null && id.getPath().startsWith("adaptations_")) {
                        var item = augment.getItem();
                        if (item != null) {
                            output.accept(item);
                        }
                    }
                }
            }))
            .build());
}
