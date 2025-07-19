package net.rose.rosalib.api.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.rose.rosalib.api.datagen.Lang;
import net.rose.rosalib.api.util.ItemStackUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represents a normal {@link Item} with a description. The description is defined in your {@link Lang}, or by hand in
 * your lang file using the translation key of the stack, added '.desc' at the end.
 *
 * @see ItemStackUtil#getDescriptionTranslationKey(ItemStack)
 */
public class ItemWithDescription extends Item {
    private final Object[] descriptionArguments;

    /**
     * Creates a new instance of that item.
     *
     * @param settings             The settings of that item.
     * @param descriptionArguments Values used by the description when formatting it. Can be left empty.
     */
    public ItemWithDescription(Settings settings, Object... descriptionArguments) {
        super(settings);
        this.descriptionArguments = descriptionArguments;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        final var descriptionKey = ItemStackUtil.getDescriptionTranslationKey(stack);
        final var translation = Text.translatable(descriptionKey, descriptionArguments);
        tooltip.add(translation.formatted(Formatting.DARK_GRAY));
    }
}
