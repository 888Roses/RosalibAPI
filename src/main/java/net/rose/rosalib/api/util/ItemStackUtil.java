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

    /**
     * Gets and returns the translation key for the description of the given stack.
     *
     * @param stack The stack to get the description key of.
     * @return A string representing the translation key pointing to the description of that stack. That description key
     * is formed off of the {@link ItemStack#getTranslationKey()} of the given stack, added '.desc'.
     */
    public static String getDescriptionTranslationKey(@NotNull ItemStack stack) {
        return stack.getTranslationKey() + ".desc";
    }
}
