package net.rose.rosalib.api.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains utility methods regarding {@link Enchantment}s.
 */
public class EnchantmentUtil {
    /**
     * Checks if the given {@link ItemStack} has the enchantment applied to it or not.
     *
     * @param stack       The stack to check for.
     * @param enchantment The enchantment to check.
     * @return Whether the level of the given enchantment on the given stack is greater than 0 (is applied) or not.
     */
    public static boolean hasEnchantment(@NotNull ItemStack stack, @NotNull Enchantment enchantment) {
        return EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }

    /**
     * Creates a new enchantment builder used to easily enchant an item stack with multiple enchantments or to perform
     * various checks on said item stack.
     *
     * @return A new {@link Builder}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class used to easily enchant an item stack with multiple enchantments or to perform various checks on
     * said item stack.
     */
    public static class Builder {
        private Builder() {}

        /**
         * Contains every {@link Enchantment} in this builder as well as their level.
         */
        private final Map<Enchantment, Integer> enchantments = new HashMap<>();

        /**
         * Contains every enchantment registered in this builder, alongside their level.
         *
         * @return A new HashMap containing the {@link #enchantments} map of this builder.
         */
        public @NotNull Map<Enchantment, Integer> toMap() {
            return new HashMap<>(this.enchantments);
        }

        /**
         * Adds an {@link Enchantment} to the enchantments contained in this builder, alongside its level. If the
         * enchantment is already contained in the builder, replaces the current level of that enchantment by the new
         * one.
         *
         * @param enchantment The enchantment to add to this builder.
         * @param level       The level of that enchantment.
         * @return This builder, for chaining.
         */
        public @NotNull Builder add(@NotNull Enchantment enchantment, int level) {
            if (this.enchantments.containsKey(enchantment)) {
                this.enchantments.replace(enchantment, level);
                return this;
            }

            this.enchantments.put(enchantment, level);
            return this;
        }

        /**
         * A version of {@link #add(Enchantment, int)} with 1 as the level.
         */
        public @NotNull Builder add(@NotNull Enchantment enchantment) {
            return this.add(enchantment, 1);
        }

        /**
         * Enchants the given {@link ItemStack} with the enchantments contained in this builder.
         *
         * @param stack The item stack to enchant.
         * @return The enchanted item stack.
         */
        public @NotNull ItemStack enchant(@NotNull ItemStack stack) {
            EnchantmentHelper.set(this.enchantments, stack);
            return stack;
        }

        /**
         * Checks whether the given stack has any of the enchantments contained in this builder or not.
         *
         * @param stack The item stack to check for.
         * @return True if the item stack has any of the enchantments contained in this builder, false otherwise.
         */
        public boolean hasAnyEnchantment(@NotNull ItemStack stack) {
            final var enchantmentsInStack = EnchantmentHelper.get(stack);
            return enchantmentsInStack.keySet().stream().anyMatch(enchantments::containsKey);
        }

        /**
         * Checks whether the given stack has all the enchantments contained in this builder or not.
         *
         * @param stack The item stack to check for.
         * @return True if the item stack has all the enchantments contained in this builder, false otherwise.
         */
        public boolean hasAllEnchantments(@NotNull ItemStack stack) {
            final var enchantmentsInStack = EnchantmentHelper.get(stack);
            return enchantmentsInStack.keySet().stream().allMatch(enchantments::containsKey);
        }
    }
}