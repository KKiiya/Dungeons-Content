package me.kiiya.dcontent.registry;

import me.kiiya.dcontent.DungeonsContent;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class ModBlockItemIds {

	private static BlockItemId create(String name) {
		Identifier id = DungeonsContent.id(name);
		return BlockItemId.create(id, id);
	}

	public static final BlockItemId TUFFITE = create("tuffite");
	public static final BlockItemId TUFFITE_PILLAR = create("tuffite_pillar");
	public static final BlockItemId TUFFITE_SLAB = create("tuffite_slab");
	public static final BlockItemId TUFFITE_STAIRS = create("tuffite_stairs");
	public static final BlockItemId STONE_PILLAR = create("stone_pillar");
	public static final BlockItemId CRATE = create("crate");
	public static final BlockItemId WILDGRASS_BLOCK = create("wildgrass_block");
	public static final BlockItemId WILDGRASS_PATH = create ("wildgrass_path");
	public static final BlockItemId WILDSOIL = create("wildsoil");
	public static final BlockItemId WILDSOIL_PATH = create("wildsoil_path");
	public static final BlockItemId WILD_PODZOL = create("wild_podzol");
}
