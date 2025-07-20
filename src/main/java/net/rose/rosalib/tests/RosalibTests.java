package net.rose.rosalib.tests;

import net.rose.rosalib.common.Rosalib;
import net.rose.rosalib.tests.init.ModItems;

public class RosalibTests {
    public static void onInitialize() {
        Rosalib.link(Rosalib.MOD_ID);

        ModItems.initialize();
    }
}
