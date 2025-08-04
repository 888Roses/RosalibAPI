package net.rose.rosalib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.rose.rosalib.api.type.Range;
import org.jetbrains.annotations.NotNull;

public class SoundUtil {
    /**
     * Plays a sound similarly using {@link World#playSound(Entity, BlockPos, SoundEvent, SoundCategory, float, float)}
     * as a base.
     *
     * @param world    The world to play the sound in.
     * @param event    The sound to play.
     * @param category The category of the sound, used to independently lower mix the volume of the sound in audio
     *                 settings.
     * @param pos      Where in the world that sound should be played.
     * @param volume   How loud that sound should be.
     * @param pitch    The pitch of the sound to be played.
     */
    public static void playSound(@NotNull World world, @NotNull SoundEvent event, @NotNull SoundCategory category,
                                 Vec3d pos, Range volume, Range pitch) {
        if (world.isClient) return;
        final var effectiveVolume = (float) volume.get(world.getRandom());
        final var effectivePitch = (float) pitch.get(world.getRandom());
        world.playSound(null, pos.x, pos.y, pos.z, event, category, effectiveVolume, effectivePitch);
    }

    /**
     * Plays a sound similarly using {@link World#playSound(Entity, BlockPos, SoundEvent, SoundCategory, float, float)}
     * as a base.
     *
     * @param world    The world to play the sound in.
     * @param event    The sound to play.
     * @param category The category of the sound, used to independently lower mix the volume of the sound in audio
     *                 settings.
     * @param pos      Where in the world that sound should be played.
     * @param volume   How loud that sound should be.
     * @param pitch    The pitch of the sound to be played.
     */
    public static void playSound(@NotNull World world, @NotNull SoundEvent event, @NotNull SoundCategory category,
                                 Vec3d pos, double volume, double pitch) {
        playSound(world, event, category, pos, Range.of(volume), Range.of(pitch));
    }

    /**
     * Plays a sound similarly using {@link World#playSound(Entity, BlockPos, SoundEvent, SoundCategory, float, float)}
     * as a base.
     *
     * @param world    The world to play the sound in.
     * @param event    The sound to play.
     * @param category The category of the sound, used to independently lower mix the volume of the sound in audio
     *                 settings.
     * @param pos      Where in the world that sound should be played.
     * @param volume   How loud that sound should be.
     */
    public static void playSound(@NotNull World world, @NotNull SoundEvent event, @NotNull SoundCategory category,
                                 Vec3d pos, double volume) {
        playSound(world, event, category, pos, Range.of(volume), Range.of(1));
    }

    /**
     * Plays a sound similarly using {@link World#playSound(Entity, BlockPos, SoundEvent, SoundCategory, float, float)}
     * as a base.
     *
     * @param world    The world to play the sound in.
     * @param event    The sound to play.
     * @param category The category of the sound, used to independently lower mix the volume of the sound in audio
     *                 settings.
     * @param pos      Where in the world that sound should be played.
     */
    public static void playSound(@NotNull World world, @NotNull SoundEvent event, @NotNull SoundCategory category,
                                 Vec3d pos) {
        playSound(world, event, category, pos, 1);
    }

    /**
     * Plays a sound on the client, using {@link net.minecraft.client.sound.SoundManager#play(SoundInstance)} as a
     * base.
     *
     * @param event    The sound to play.
     * @param category The category of the sound to play, in order to allow mixing the volume of that sound
     *                 independently of the other volumes in audio settings.
     * @param pos      Where in the world should that sound be played.
     * @param volume   How loud should that sound be.
     * @param pitch    The pitch of the sound to be played.
     * @param delay    In how many ticks should that sound be played.
     * @apiNote This method may only be called on the {@link EnvType#CLIENT} side.
     * @see #playClientSound(SoundEvent, SoundCategory, Vec3d, Range, Range)
     */
    @Environment(EnvType.CLIENT)
    public static void playClientSound(@NotNull SoundEvent event, @NotNull SoundCategory category, Vec3d pos,
                                       Range volume, Range pitch, int delay) {
        final var soundManager = MinecraftClient.getInstance().getSoundManager();
        if (soundManager == null) return;

        final var random = SoundInstance.createRandom();
        final var effectiveVolume = (float) volume.get(random);
        final var effectivePitch = (float) pitch.get(random);
        final var instance = new PositionedSoundInstance(event, category, effectiveVolume, effectivePitch, random,
                pos.x, pos.y, pos.z);
        soundManager.play(instance, delay);
    }

    /**
     * Plays a sound on the client, using {@link net.minecraft.client.sound.SoundManager#play(SoundInstance)} as a
     * base.
     *
     * @param event    The sound to play.
     * @param category The category of the sound to play, in order to allow mixing the volume of that sound
     *                 independently of the other volumes in audio settings.
     * @param pos      Where in the world should that sound be played.
     * @param volume   How loud should that sound be.
     * @param pitch    The pitch of the sound to be played.
     * @param delay    In how many ticks should that sound be played.
     * @apiNote This method may only be called on the {@link EnvType#CLIENT} side.
     * @see #playClientSound(SoundEvent, SoundCategory, Vec3d, Range, Range)
     */
    @Environment(EnvType.CLIENT)
    public static void playClientSound(@NotNull SoundEvent event, @NotNull SoundCategory category, Vec3d pos,
                                       double volume, double pitch, int delay) {
        playClientSound(event, category, pos, Range.of(volume), Range.of(pitch), delay);
    }

    /**
     * Plays a sound on the client, using {@link net.minecraft.client.sound.SoundManager#play(SoundInstance)} as a
     * base.
     *
     * @param event    The sound to play.
     * @param category The category of the sound to play, in order to allow mixing the volume of that sound
     *                 independently of the other volumes in audio settings.
     * @param pos      Where in the world should that sound be played.
     * @param volume   How loud should that sound be.
     * @param delay    In how many ticks should that sound be played.
     * @apiNote This method may only be called on the {@link EnvType#CLIENT} side.
     * @see #playClientSound(SoundEvent, SoundCategory, Vec3d, Range, Range)
     */
    @Environment(EnvType.CLIENT)
    public static void playClientSound(@NotNull SoundEvent event, @NotNull SoundCategory category, Vec3d pos,
                                       double volume, int delay) {
        playClientSound(event, category, pos, Range.of(volume), Range.of(1), delay);
    }

    /**
     * Plays a sound on the client, using {@link net.minecraft.client.sound.SoundManager#play(SoundInstance)} as a
     * base.
     *
     * @param event    The sound to play.
     * @param category The category of the sound to play, in order to allow mixing the volume of that sound
     *                 independently of the other volumes in audio settings.
     * @param pos      Where in the world should that sound be played.
     * @param delay    In how many ticks should that sound be played.
     * @apiNote This method may only be called on the {@link EnvType#CLIENT} side.
     * @see #playClientSound(SoundEvent, SoundCategory, Vec3d, Range, Range)
     */
    @Environment(EnvType.CLIENT)
    public static void playClientSound(@NotNull SoundEvent event, @NotNull SoundCategory category, Vec3d pos,
                                       int delay) {
        playClientSound(event, category, pos, 1, delay);
    }

    /**
     * Plays a sound on the client, using {@link net.minecraft.client.sound.SoundManager#play(SoundInstance)} as a
     * base.
     *
     * @param event    The sound to play.
     * @param category The category of the sound to play, in order to allow mixing the volume of that sound
     *                 independently of the other volumes in audio settings.
     * @param pos      Where in the world should that sound be played.
     * @param volume   How loud should that sound be.
     * @param pitch    The pitch of the sound to be played.
     * @apiNote This method may only be called on the {@link EnvType#CLIENT} side.
     * @see #playClientSound(SoundEvent, SoundCategory, Vec3d, Range, Range)
     */
    @Environment(EnvType.CLIENT)
    public static void playClientSound(@NotNull SoundEvent event, @NotNull SoundCategory category, Vec3d pos,
                                       Range volume, Range pitch) {
        playClientSound(event, category, pos, volume, pitch, 0);
    }

    /**
     * Plays a sound on the client, using {@link net.minecraft.client.sound.SoundManager#play(SoundInstance)} as a
     * base.
     *
     * @param event    The sound to play.
     * @param category The category of the sound to play, in order to allow mixing the volume of that sound
     *                 independently of the other volumes in audio settings.
     * @param pos      Where in the world should that sound be played.
     * @param volume   How loud should that sound be.
     * @param pitch    The pitch of the sound to be played.
     * @apiNote This method may only be called on the {@link EnvType#CLIENT} side.
     * @see #playClientSound(SoundEvent, SoundCategory, Vec3d, Range, Range)
     */
    @Environment(EnvType.CLIENT)
    public static void playClientSound(@NotNull SoundEvent event, @NotNull SoundCategory category, Vec3d pos,
                                       double volume, double pitch) {
        playClientSound(event, category, pos, Range.of(volume), Range.of(pitch), 0);
    }

    /**
     * Plays a sound on the client, using {@link net.minecraft.client.sound.SoundManager#play(SoundInstance)} as a
     * base.
     *
     * @param event    The sound to play.
     * @param category The category of the sound to play, in order to allow mixing the volume of that sound
     *                 independently of the other volumes in audio settings.
     * @param pos      Where in the world should that sound be played.
     * @param volume   How loud should that sound be.
     * @apiNote This method may only be called on the {@link EnvType#CLIENT} side.
     * @see #playClientSound(SoundEvent, SoundCategory, Vec3d, Range, Range)
     */
    @Environment(EnvType.CLIENT)
    public static void playClientSound(@NotNull SoundEvent event, @NotNull SoundCategory category, Vec3d pos,
                                       double volume) {
        playClientSound(event, category, pos, Range.of(volume), Range.of(1), 0);
    }

    /**
     * Plays a sound on the client, using {@link net.minecraft.client.sound.SoundManager#play(SoundInstance)} as a
     * base.
     *
     * @param event    The sound to play.
     * @param category The category of the sound to play, in order to allow mixing the volume of that sound
     *                 independently of the other volumes in audio settings.
     * @param pos      Where in the world should that sound be played.
     * @apiNote This method may only be called on the {@link EnvType#CLIENT} side.
     * @see #playClientSound(SoundEvent, SoundCategory, Vec3d, Range, Range)
     */
    @Environment(EnvType.CLIENT)
    public static void playClientSound(@NotNull SoundEvent event, @NotNull SoundCategory category, Vec3d pos) {
        playClientSound(event, category, pos, 1, 0);
    }

    public static void playSoundWithDistance(
            @NotNull World world, @NotNull Vec3d pos,
            @NotNull SoundEvent event, @NotNull SoundCategory category,
            float volume, float pitch, float maxHearingDistance
    ) {
        if (!world.isClient) return;

        final var player = MinecraftClient.getInstance().player;
        if (player == null) return;

        final var distance = (float) pos.distanceTo(player.getEyePos());
        if (distance > maxHearingDistance) return;

        final var distancePercentage = distance / maxHearingDistance;
        final var effectiveVolume = MathHelper.clamp(1f - distancePercentage, 0, 1) * volume;
        MinecraftClient.getInstance().getSoundManager().play(new PositionedSoundInstance(
                event, category, effectiveVolume, pitch, player.getRandom(), player.getBlockPos()
        ));
    }
}
