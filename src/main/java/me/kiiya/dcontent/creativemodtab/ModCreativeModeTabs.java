package me.kiiya.dcontent.creativemodtab;

import me.kiiya.dcontent.DungeonsContent;
import me.kiiya.dcontent.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    
    public static final CreativeModeTab TUFFITE_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(DungeonsContent.MOD_ID, "dungeon_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TUFFITE))
                    .title(Component.translatable("creativemodetab.dungeonscontent.dungeon_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.TUFFITE);
                        output.accept(ModBlocks.TUFFITE_PILLAR);
                    }).build());

    public static void registerModCreativeModeTabs() {
        DungeonsContent.LOGGER.info("Registering Creative Mode Tabs for " + DungeonsContent.MOD_ID);
    }

}
