package net.rose.rosalib.api.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class EntityUtil {
    /**
     * Checks whether the given living entity is in creative mode or not. That implies checking if the entity is a
     * player entity first.
     *
     * @param livingEntity The entity to check for.
     * @return True if the entity is in creative mode, False otherwise.
     */
    public static boolean isInCreativeMode(@NotNull LivingEntity livingEntity) {
        return livingEntity instanceof PlayerEntity player && player.isCreative();
    }
}
