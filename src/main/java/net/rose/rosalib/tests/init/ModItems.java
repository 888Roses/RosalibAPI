package net.rose.rosalib.tests.init;

import net.minecraft.item.Item;
import net.rose.rosalib.api.registry.ItemRegistry;
import net.rose.rosalib.api.util.TextUtil;
import net.rose.rosalib.common.Rosalib;

public class ModItems extends ItemRegistry {
    public static Item TESTING_ITEM = itemWithDescription(
            "testing_item",
            () -> TextUtil.getKeyBindingText(o -> o.attackKey, true),
            () -> TextUtil.getKeyBindingText(o -> o.useKey, true)
    );

    public static void initialize() {
        Rosalib.info("Initialized Rosalib Mod Items!");
    }
}
