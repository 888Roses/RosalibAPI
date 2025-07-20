package net.rose.rosalib.api.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.rose.rosalib.common.Rosalib;

import java.util.List;

public abstract class Lang extends FabricLanguageProvider {
    protected Lang(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        this.lang = translationBuilder;
        generate();
    }

    protected abstract void generate();

    protected TranslationBuilder lang;

    /**
     * Generates the translation for an item group with the given group name.
     *
     * @param groupName The id of the group.
     * @param key       The translated name of the group.
     */
    protected void itemGroup(String groupName, String key) {
        lang.add("itemgroups." + groupName, key);
    }

    /**
     * Generates the translation for an item group which group name is the ID of this Rosalib API instance's linked
     * mod.
     *
     * @param key The translated name of the group.
     * @see Rosalib#getLinkedId()
     */
    protected void itemGroup(String key) {
        itemGroup(Rosalib.getLinkedId(), key);
    }

    /**
     * Generates translation keys for a death message.
     *
     * @param name The ID name of that sound message.
     * @param desc The text shown in chat when a named or owned entity dies by this death message.
     */
    protected void deathMessage(String name, String desc) {
        lang.add("death.attack." + name, desc);
    }

    /**
     * Generates a translation key for a given sound for it to have a subtitle in-game.
     *
     * @param soundEvent The sound to generate a subtitle for.
     * @param subtitle   The subtitle of the sound as shown to the player in-game.
     */
    protected void sound(SoundEvent soundEvent, String subtitle) {
        lang.add(soundEvent.getId().toTranslationKey("subtitles"), subtitle);
    }

    /**
     * Generates a lang key for a given block.
     *
     * @param block The block you wish to generate a lang key for.
     * @param name  The name of that block as shown to the player in-game.
     */
    protected void block(Block block, String name) {
        lang.add(block, name);
    }

    /**
     * Generates a lang key for a given block, similarly to how {@link Lang#block(Block, String)} does it. Adds a
     * description to it. Please note that this description <b>is not automatically appended to the block tooltip.</b>
     * Rather, the while the key is being generated you still need to override
     * {@link Block#appendTooltip(ItemStack, BlockView, List, TooltipContext)} and use said translation key to have your
     * description be shown in-game.
     *
     * @param block The block you wish to generate a lang key for.
     * @param name  The name of that block as shown to the player in-game.
     * @param desc  The description of that block as shown to the player in-game.
     */
    protected void block(Block block, String name, String desc) {
        block(block, name);
        lang.add(block.getTranslationKey() + ".desc", desc);
    }

    /**
     * Generates an item lang key for the item, added a suffix. This means that for the item "banana_split" with the
     * suffix "burnt", the generated lang key will be "banana_split_burnt".
     *
     * @param item   The item for which you wish to generate translation.
     * @param suffix A string added to the end of the translation key of the item. If this string does not start with a
     *               '.' character, it will automatically be added to it.
     * @param name   The name of the item as shown in-game.
     */
    protected void subItem(Item item, String suffix, String name) {
        if (!suffix.startsWith(".")) suffix = "." + suffix;
        lang.add(item.getTranslationKey() + suffix, name);
    }

    /**
     * Generates an item lang key for an item's name and description. Please note that this description <b>is not
     * automatically appended to the item tooltip.</b> Rather, the while the key is being generated you still need to
     * override {@link Item#appendTooltip(ItemStack, World, List, TooltipContext)} and use said translation key to have
     * your description be shown in-game.
     *
     * @param item         The item for which you wish to generate translation.
     * @param name         The name of the item as shown in-game.
     * @param descriptions The description lines of the item as shown in-game.
     */
    protected void item(Item item, String name, String... descriptions) {
        lang.add(item.getTranslationKey(), name);

        var descCount = 0;
        for (var description : descriptions) {
            final var key = "desc" + (descCount > 0 ? String.valueOf(descCount) : "");
            subItem(item, key, description);
            descCount++;
        }
    }

    /**
     * Generates the language keys for an advancement.
     *
     * @param path The ID name of the advancement.
     * @param name The in-game literal name of the advancement as shown to the player in the advancement tab.
     * @param desc The description of the advancement as shown to the player in the advancement tab.
     */
    protected void advancement(String path, String name, String desc) {
        lang.add("advancements.pvp_rework." + path, name);
        lang.add("advancements.pvp_rework." + path + ".desc", desc);
    }
}