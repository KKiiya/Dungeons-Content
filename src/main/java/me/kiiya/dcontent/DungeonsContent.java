package me.kiiya.dcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.kiiya.dcontent.block.ModBlocks;
import me.kiiya.dcontent.creativemodtab.ModCreativeModeTabs;
import net.fabricmc.api.ModInitializer;

public class DungeonsContent implements ModInitializer {
    public static final String MOD_ID = "dungeonscontent";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModCreativeModeTabs.registerModCreativeModeTabs();
    }
}
