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
}
