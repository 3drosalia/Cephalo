package io.github.marietheruby.cephalo.weapons;

import io.github.marietheruby.cephalo.Cephalo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class weaponRegistry {
	public static final Item SPLATTERSHOTJR = new Splattershot_Jr(new Item.Settings().group(ItemGroup.COMBAT).maxCount(64));
	public static void registerItems() {
		Registry.register(Registry.ITEM, new Identifier(Cephalo.MOD_ID, "splattershot_jr"), SPLATTERSHOTJR);

	}
}
