package net.rose.rosalib.api.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.rose.rosalib.api.item.ItemWithDescription;
import net.rose.rosalib.common.Rosalib;

import java.util.function.Supplier;

public class ItemRegistry {
    /**
     * Registers the given item at the path in the namespace currently used by this instance of Rosalib API.
     * @param path The ID of the item in-game (I.E. this is what you would use in a '/give' command).
     * @param item
     * @return
     * @see Rosalib#getLinkedId()
     * @see Rosalib#isLinked()
     */
    protected static Item item(String path, Item item) {
        final var id = Rosalib.id(path);
        return Registry.register(Registries.ITEM, id, item);
    }

    protected static Item item(String path, Item.Settings settings) {
        return item(path, new Item(settings));
    }

    protected static Item item(String path) {
        return item(path, new Item.Settings());
    }

    @SafeVarargs
    protected static Item itemWithDescription(String path, Item.Settings settings, Supplier<Object>... arguments) {
        return item(path, new ItemWithDescription(settings, arguments));
    }

    @SafeVarargs
    public static Item itemWithDescription(String path, Supplier<Object>... arguments) {
        return itemWithDescription(path, new Item.Settings(), arguments);
    }
}
