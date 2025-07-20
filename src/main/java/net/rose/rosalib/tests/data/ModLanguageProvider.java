package net.rose.rosalib.tests.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.rose.rosalib.api.datagen.Lang;
import net.rose.rosalib.tests.init.ModItems;

public class ModLanguageProvider extends Lang {
    public ModLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    protected void generate() {
        item(ModItems.TESTING_ITEM, "Testing Item", "Press %s to pet your dog.\nPress %s to materialize dog food.");
    }
}
