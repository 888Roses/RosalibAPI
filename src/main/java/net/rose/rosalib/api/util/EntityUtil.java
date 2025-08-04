package net.rose.rosalib.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

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

    /**
     * Makes sure that the hit point of the given hit result is in range of the source position.
     *
     * @param result      The {@link HitResult} to check for.
     * @param sourcePos   The position from which the hit result was gotten.
     * @param maxDistance The maximum distance from the source position.
     * @return The hit result if it was in range of the source position, or
     * {@link BlockHitResult#createMissed(Vec3d, Direction, BlockPos)} otherwise.
     */
    // Code adapted from:
    // https://www.reddit.com/r/fabricmc/comments/190daft/why_mccameraentityraycast_doesnt_detect_entity/
    private static HitResult ensureTargetInRange(@NotNull HitResult result, Vec3d sourcePos, double maxDistance) {
        final var pos = result.getPos();

        if (!pos.isInRange(sourcePos, maxDistance)) {
            final var direction = Direction.getFacing(pos.x - sourcePos.x, pos.y - sourcePos.y, pos.z - sourcePos.z);
            return BlockHitResult.createMissed(pos, direction, BlockPos.ofFloored(pos));
        }

        return result;
    }

    /**
     * Executes a raycast from the position and rotation of the entity, and checks if it hits any block, fluid or
     * entity. This method is an extension to the native {@link Entity#raycast(double, float, boolean)}, which does not
     * handle entity checks.
     *
     * @param entity         The entity from which you wish to raycast.
     * @param maxDistance    The maximum distance from the entity at which a hit target can be.
     * @param includeFluids  Whether to stop raycasting when hitting a fluid or not.
     * @param tickDelta      The current delta of that frame. Pass 0 if unsure.
     * @param validateEntity Additional check performed on any potential hit entity.
     * @return A {@link HitResult} containing information on the raycast result.
     */
    public static @NotNull HitResult raycastIncludeEntity(@NotNull Entity entity, float maxDistance,
                                                          boolean includeFluids, float tickDelta,
                                                          Predicate<Entity> validateEntity) {
        var result = entity.raycast(maxDistance, tickDelta, includeFluids);
        if (result.getType() == HitResult.Type.MISS) {
            final var rotation = entity.getRotationVec(tickDelta);
            final var direction = rotation.multiply(maxDistance);
            final var box = entity.getBoundingBox().stretch(rotation.multiply(maxDistance)).expand(1);
            final var entityRaycast = ProjectileUtil.raycast(entity, rotation, direction, box,
                    e -> !e.isSpectator() && e.canHit() && validateEntity.test(e), MathHelper.square(maxDistance));
            if (entityRaycast != null) result = entityRaycast;
        }

        return ensureTargetInRange(result, entity.getPos(), maxDistance);
    }

    /**
     * Executes a raycast from the position and rotation of the entity, and checks if it hits any block, fluid or
     * entity. This method is an extension to the native {@link Entity#raycast(double, float, boolean)}, which does not
     * handle entity checks.
     *
     * @param entity        The entity from which you wish to raycast.
     * @param maxDistance   The maximum distance from the entity at which a hit target can be.
     * @param includeFluids Whether to stop raycasting when hitting a fluid or not.
     * @param tickDelta     The current delta of that frame. Pass 0 if unsure.
     * @return A {@link HitResult} containing information on the raycast result.
     * @apiNote This is a version of {@link #raycastIncludeEntity(Entity, float, boolean, float, Predicate)} in which
     * the predicate always returns true.
     * @see #raycastIncludeEntity(Entity, float, boolean, float, Predicate)
     */
    public static @NotNull HitResult raycastIncludeEntity(@NotNull Entity entity, float maxDistance,
                                                          boolean includeFluids, float tickDelta) {
        return raycastIncludeEntity(entity, maxDistance, includeFluids, tickDelta, e -> true);
    }

    /**
     * Executes a raycast from the position and rotation of the entity, and checks if it hits any block, fluid or
     * entity. This method is an extension to the native {@link Entity#raycast(double, float, boolean)}, which does not
     * handle entity checks.
     *
     * @param entity        The entity from which you wish to raycast.
     * @param maxDistance   The maximum distance from the entity at which a hit target can be.
     * @param includeFluids Whether to stop raycasting when hitting a fluid or not.
     * @return A {@link HitResult} containing information on the raycast result.
     * @apiNote This is a version of {@link #raycastIncludeEntity(Entity, float, boolean, float)} in which the tickDelta
     * is always 0.
     * @see #raycastIncludeEntity(Entity, float, boolean, float)
     */
    public static @NotNull HitResult raycastIncludeEntity(@NotNull Entity entity, float maxDistance,
                                                          boolean includeFluids) {
        return raycastIncludeEntity(entity, maxDistance, includeFluids, 0);
    }
}