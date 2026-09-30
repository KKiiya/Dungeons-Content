package me.kiiya.dcontent.block;

import java.util.function.Function;

import me.kiiya.dcontent.DungeonsContent;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {

    public static final Block TUFFITE = registerBlock("tuffite",
                        properties -> new Block(properties.mapColor(MapColor.TERRACOTTA_GRAY)
                                                          .instrument(NoteBlockInstrument.BASEDRUM)
                                                          .sound(SoundType.TUFF)
                                                          .requiresCorrectToolForDrops()
                                                          .strength(1.5F, 6.0F)));

    public static final Block TUFFITE_PILLAR = registerBlock("tuffite_pillar",
                    properties -> new RotatedPillarBlock(properties.mapColor(MapColor.TERRACOTTA_GRAY)
                                                        .instrument(NoteBlockInstrument.BASEDRUM)
                                                        .sound(SoundType.TUFF)
                                                        .requiresCorrectToolForDrops()
                                                        .strength(1.5F, 6.0F)));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(DungeonsContent.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(DungeonsContent.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(DungeonsContent.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(DungeonsContent.MOD_ID, name)))));
    }
    
    public static void registerModBlocks () {
        DungeonsContent.LOGGER.info("Registering Mod Blocks for " + DungeonsContent.MOD_ID);
    }

}
