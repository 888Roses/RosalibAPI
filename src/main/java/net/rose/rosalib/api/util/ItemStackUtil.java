package net.rose.rosalib.api.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Collection of utility methods regarding {@link ItemStack}s.
 */
public class ItemStackUtil {
    /**
     * Decrements the amount of item in the given stack unless the given entity is in creative mode. If the amount is
     * smaller or equal to 0, or if the owner is in creative mode, does not change the stack size.
     *
     * @param stack  The stack to decrement.
     * @param amount How many items to remove from the stack. Can be smaller or equal to 0, in which case this method
     *               will return early.
     * @param owner  The owner of the stack. This is used to determine whether the entity is in creative mode or not.
     *               Can be left null in case you do not wish to check for any entity.
     */
    public static void decrement(@NotNull ItemStack stack, int amount, @Nullable LivingEntity owner) {
        if (amount <= 0 || (owner != null && EntityUtil.isInCreativeMode(owner))) return;
        stack.decrement(amount);
    }
}
