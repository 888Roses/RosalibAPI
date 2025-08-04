package net.rose.rosalib.api.util;

import org.joml.Quaternionf;

/**
 * Collection of utility methods regarding {@link Quaternionf}s.
 */
public class QuaternionUtil {
    /**
     * Creates a new {@link Quaternionf} using the x, y and z euler angles as a rotation. Those angles are measured in
     * degrees.
     *
     * @param x The X rotation of the quaternion.
     * @param y The Y rotation of the quaternion.
     * @param z The Z rotation of the quaternion.
     * @return A new quaternion using those x y and z rotations.
     * @see #eulerAngleRad(double, double, double)
     */
    public static Quaternionf eulerAngleDeg(double x, double y, double z) {
        return eulerAngleRad(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z));
    }

    /**
     * Creates a new {@link Quaternionf} using the x, y and z euler angles as a rotation. Those angles are measured in
     * radians.
     *
     * @param x The X rotation of the quaternion.
     * @param y The Y rotation of the quaternion.
     * @param z The Z rotation of the quaternion.
     * @return A new quaternion using those x y and z rotations.
     * @see #eulerAngleDeg(double, double, double)
     */
    public static Quaternionf eulerAngleRad(double x, double y, double z) {
        return new Quaternionf().rotationXYZ((float)x, (float)y, (float)z);
    }
}