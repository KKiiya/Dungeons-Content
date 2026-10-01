package me.kiiya.dcontent.registry;

import java.util.function.Function;

import me.kiiya.dcontent.DungeonsContent;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {

    public static final Block TUFFITE = register(
		ModBlockItemIds.TUFFITE,
		Block::new,
		BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY)
                                      .instrument(NoteBlockInstrument.BASEDRUM)
                                      .sound(SoundType.TUFF)
                                      .requiresCorrectToolForDrops()
                                      .strength(1.5F, 6.0F)
    );

    public static final Block TUFFITE_SLAB = register(
        ModBlockItemIds.TUFFITE_SLAB,
        SlabBlock::new,
        BlockBehaviour.Properties.ofFullCopy(TUFFITE)
    );

    public static final Block TUFFITE_STAIRS = register(
        ModBlockItemIds.TUFFITE_STAIRS,
        properties -> new StairBlock(TUFFITE.defaultBlockState(), properties),
        BlockBehaviour.Properties.ofFullCopy(TUFFITE)
    );

    public static final Block TUFFITE_PILLAR = register(
		ModBlockItemIds.TUFFITE_PILLAR,
		RotatedPillarBlock::new,
		BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY)
                                      .instrument(NoteBlockInstrument.BASEDRUM)
                                      .sound(SoundType.TUFF)
                                      .requiresCorrectToolForDrops()
                                      .strength(1.5F, 6.0F)
    );

    public static final Block WOODEN_CRATE = register(
        ModBlockItemIds.WOODEN_CRATE, 
        Block::new,
        BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD)
                                    .instrument(NoteBlockInstrument.BASS)
                                    .sound(SoundType.WOOD)
                                    .strength(2.5F)
                                    .ignitedByLava());

	private static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		// Create the block instance
		Block block = blockFactory.apply(properties.setId(id));

		return Registry.register(BuiltInRegistries.BLOCK, id, block);
	}

    private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		// Create the block instance
		Block block = register(id.block(), blockFactory, properties);

		// Create the block item instance
		BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
		Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

		return block;
	}

    public static void initialize() {
        DungeonsContent.LOGGER.info("Registering Custom Blocks for " + DungeonsContent.MOD_ID);
	}

}
