package me.kiiya.dcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.kiiya.dcontent.registry.ModBlockEntityTypes;
import me.kiiya.dcontent.registry.ModBlocks;
import me.kiiya.dcontent.registry.ModCreativeTabs;
import me.kiiya.dcontent.registry.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

public class DungeonsContent implements ModInitializer {
    public static final String MOD_ID = "dungeonscontent";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModSounds.initialize();
        ModBlockEntityTypes.initialize();
        ModCreativeTabs.initialize();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
