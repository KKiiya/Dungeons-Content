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
import net.minecraft.world.level.block.SoundType;
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

    public static final Block TUFFITE_PILLAR = register(
		ModBlockItemIds.TUFFITE_PILLAR,
		RotatedPillarBlock::new,
		BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY)
                                      .instrument(NoteBlockInstrument.BASEDRUM)
                                      .sound(SoundType.TUFF)
                                      .requiresCorrectToolForDrops()
                                      .strength(1.5F, 6.0F)
    );

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
