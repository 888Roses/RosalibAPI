package net.rose.rosalib.api.util;

import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ShapeUtil {
    /**
     * Combines every {@link VoxelShape} in a single one using the {@link BooleanBiFunction#OR} function.
     *
     * @param shapes The shapes you wish to combine.
     * @return A new voxel shape being the combination of every shape provided, or null if no shape is provided.
     */
    public static VoxelShape combine(@NotNull VoxelShape... shapes) {
        if (Arrays.stream(shapes).findAny().isEmpty()) {
            return null;
        }

        var result = VoxelShapes.empty();
        for (var shape : shapes) result = VoxelShapes.combine(result, shape, BooleanBiFunction.OR);
        return result;
    }

    /**
     * Builds a voxel shape using a box layout (position and size) rather than a Bounds layout (min-max).
     *
     * @param x     The X position.
     * @param y     The Y position.
     * @param z     The Z position.
     * @param sizeX The X size.
     * @param sizeY The Y size.
     * @param sizeZ The Z size.
     * @return A new voxel shape representing the described box.
     * @apiNote Unlike {@link VoxelShapes#cuboid(double, double, double, double, double, double)}, the values range
     * between <b>0 and 16</b>.
     */
    public static VoxelShape box(double x, double y, double z, double sizeX, double sizeY, double sizeZ) {
        return VoxelShapes.cuboid(
                x / 16d, y / 16d, z / 16d,
                (x + sizeX) / 16d, (y + sizeY) / 16d, (z + sizeZ) / 16d
        );
    }

    /**
     * Builds a voxel shape using a given {@link Box} as a reference.
     *
     * @param box The dimension of the voxel shape.
     * @return A new voxel shape englobing the given box.
     */
    public static VoxelShape box(Box box) {
        return box(box.minX, box.minY, box.minZ, box.getXLength(), box.getYLength(), box.getZLength());
    }
}