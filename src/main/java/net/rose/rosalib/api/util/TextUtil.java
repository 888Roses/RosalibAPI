package net.rose.rosalib.api.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TextUtil {
    public static @NotNull MutableText inBrackets(@NotNull Text text, @Nullable Style bracketStyle) {
        if (bracketStyle == null) bracketStyle = Style.EMPTY.withFormatting(Formatting.DARK_GRAY);
        final var lh = Text.literal("[").setStyle(bracketStyle);
        final var rh = Text.literal("]").setStyle(bracketStyle);
        return lh.append(text).append(rh);
    }

    public static MutableText getUseText() {
        final var useOption = MinecraftClient.getInstance().options.useKey.getTranslationKey();
        final var optionText = Text.translatable(useOption).formatted(Formatting.GRAY);
        return TextUtil.inBrackets(optionText, null);
    }
}
