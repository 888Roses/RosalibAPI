package net.rose.rosalib.api.util;

import org.joml.Quaternionf;

/**
 * Collection of utility methods regarding {@link Quaternionf}s.
 */
public class QuaternionUtil {
    /**
     * Creates a new {@link Quaternionf} and rotates it using the XYZ angle rotation.
     *
     * @param x X angle of the rotation.
     * @param y Y angle of the rotation.
     * @param z Z angle of the rotation.
     * @return The created {@link Quaternionf} with its rotations applied.
     * @apiNote The given angles are measured in degrees. See {@link #eulerAngleRad(double, double, double)} to use
     * radians instead.
     * @see #eulerAngleRad(double, double, double)
     */
    public static Quaternionf eulerAngleDeg(double x, double y, double z) {
        return eulerAngleRad(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z));
    }

    /**
     * Creates a new {@link Quaternionf} and rotates it using the XYZ angle rotation.
     *
     * @param x X angle of the rotation.
     * @param y Y angle of the rotation.
     * @param z Z angle of the rotation.
     * @return The created {@link Quaternionf} with its rotations applied.
     * @apiNote The given angles are measured in radians. See {@link #eulerAngleDeg(double, double, double)} to use
     * degrees instead.
     * @see #eulerAngleDeg(double, double, double)
     */
    public static Quaternionf eulerAngleRad(double x, double y, double z) {
        return new Quaternionf().rotationXYZ((float) x, (float) y, (float) z);
    }
}