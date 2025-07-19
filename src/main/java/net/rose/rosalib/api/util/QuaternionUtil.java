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
     * @see #eulerAngleRadians(float, float, float)
     */
    public static Quaternionf eulerAngles(double x, double y, double z) {
        return eulerAngleRadians((float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z));
    }

    /**
     * Creates a new {@link Quaternionf} using the x, y and z euler angles as a rotation. Those angles are measured in
     * radians.
     *
     * @param x The X rotation of the quaternion.
     * @param y The Y rotation of the quaternion.
     * @param z The Z rotation of the quaternion.
     * @return A new quaternion using those x y and z rotations.
     * @see #eulerAngles(double, double, double)
     */
    public static Quaternionf eulerAngleRadians(float x, float y, float z) {
        return new Quaternionf().rotationXYZ(x, y, z);
    }
}