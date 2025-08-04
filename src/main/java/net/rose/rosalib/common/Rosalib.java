package net.rose.rosalib.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.rose.rosalib.tests.RosalibTests;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rosalib implements ModInitializer {
    public static final String MOD_ID = "rosalib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Remember to disabled on builds!
        // RosalibTests.onInitialize();
    }

    // region Linking
    private static String linkedId;
    private static boolean isLinked;

    /**
     * Represents the namespace of the mod linked to this instance of Rosalib API.
     *
     * @return A string corresponding to the mod namespace linked to this Rosalib API instance.
     * @apiNote Can be null if {@link #isLinked()} is false.
     */
    public static @Nullable String getLinkedId() {
        return linkedId;
    }

    /**
     * Whether this instance of Rosalib API was linked to a mod's id or not.
     *
     * @return True if this Rosalib API instance was set up to work with a mod's id/namespace, false otherwise.
     */
    public static boolean isLinked() {
        return isLinked;
    }

    /**
     * Links Rosalib API to a given mod id to specify what {@link Identifier#getNamespace()} {@link Identifier} should
     * use. <b>This is mandatory for Rosalib to work with your mod</b>, and should be called before everything in
     * {@link ModInitializer#onInitialize()}.
     *
     * @param modId The namespace of your mod.
     */
    public static void link(String modId) {
        if (isLinked()) {
            warn(
                    "Link already linked Rosalib API to mod '{}' is not allowed (linked to mod '{}')!",
                    modId, getLinkedId()
            );

            return;
        }

        linkedId = modId;
        isLinked = true;

        info("Rosalib API linked to mod '{}'.", getLinkedId());
    }

    /**
     * Creates an {@link Identifier} using the {@link #getLinkedId()}.
     *
     * @param path The end part of the identifier, located after the namespace.
     * @return A new identifier using the {@link #getLinkedId()} as the namespace, or
     * {@link Identifier#DEFAULT_NAMESPACE} if no mod was linked.
     * @apiNote If this Rosalib API instance was not linked to a mod, throws an error and returns an identifier using
     * the {@link Identifier#DEFAULT_NAMESPACE} instead.
     * @see #isLinked()
     */
    public static Identifier id(String path) {
        if (!isLinked) {
            return Identifier.of(Identifier.DEFAULT_NAMESPACE, path);
        }

        return Identifier.of(linkedId, path);
    }

    // endregion

    // region Logging

    public static void info(String format, Object... args) {
        LOGGER.info(format, args);
    }

    public static void error(String msg, Object... args) {
        LOGGER.error(msg, args);
    }

    public static void warn(String msg, Object... args) {
        LOGGER.warn(msg, args);
    }

    // endregion
}