package me.kiiya.dcontent.registry;

import java.util.Set;

import me.kiiya.dcontent.DungeonsContent;
import me.kiiya.dcontent.blocks.crate.CrateBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypes {

    public static final BlockEntityType<CrateBlockEntity> CRATE = register("crate", CrateBlockEntity::new, ModBlocks.CRATE);

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.BlockEntitySupplier<? extends T> factory, Block... validBlocks) {
        ResourceKey<BlockEntityType<?>> id = ResourceKey.create(BuiltInRegistries.BLOCK_ENTITY_TYPE.key(), DungeonsContent.id(name));

        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, new BlockEntityType<>(factory, Set.of(validBlocks)));
    }

    public static void initialize() {
        DungeonsContent.LOGGER.info("Registering Custom Block Entity Types for " + DungeonsContent.MOD_ID);
    }

}