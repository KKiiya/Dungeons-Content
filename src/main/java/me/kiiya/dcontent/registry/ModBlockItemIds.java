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
	public static final BlockItemId CRATE = create("crate");
}
