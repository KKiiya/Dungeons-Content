package me.kiiya.dcontent.creativemodtab;

import me.kiiya.dcontent.DungeonsContent;
import me.kiiya.dcontent.registry.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    /*
    public static final CreativeModeTab TUFFITE_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(DungeonsContent.MOD_ID, "dungeon_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TUFFITE))
                    .title(Component.translatable("creativemodetab.dungeonscontent.dungeon_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.TUFFITE);
                        // output.accept(ModBlocks.TUFFITE_PILLAR);
                    }).build());
    */

    public static final ResourceKey<CreativeModeTab> DUNGEONS_CONTENT_TAB_KEY = ResourceKey.create(
		BuiltInRegistries.CREATIVE_MODE_TAB.key(),
        DungeonsContent.id("creative_tab")
    );
    
    public static final CreativeModeTab DUNGEONS_CONTENT_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.TUFFITE.asItem()))
            .title(Component.translatable("creativemodetab.dungeonscontent.creative_tab"))
            .displayItems((params, output) -> {
                output.accept(ModBlocks.TUFFITE.asItem());
            })
            .build();

    public static void initialize() {
        DungeonsContent.LOGGER.info("Registering Creative Mode Tabs for " + DungeonsContent.MOD_ID);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DUNGEONS_CONTENT_TAB_KEY, DUNGEONS_CONTENT_TAB);
    }

}
