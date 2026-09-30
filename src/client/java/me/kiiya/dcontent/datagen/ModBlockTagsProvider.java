package me.kiiya.dcontent.datagen;

import me.kiiya.dcontent.DungeonsContent;
import me.kiiya.dcontent.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BuiltInRegistries.BLOCK.getKey(ModBlocks.TUFFITE))
                .add(BuiltInRegistries.BLOCK.getKey(ModBlocks.TUFFITE_PILLAR));

        builder(BlockTags.NEEDS_STONE_TOOL)
                .add(BuiltInRegistries.BLOCK.getKey(ModBlocks.TUFFITE))
                .add(BuiltInRegistries.BLOCK.getKey(ModBlocks.TUFFITE_PILLAR));

    }
}