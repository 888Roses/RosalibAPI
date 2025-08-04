package net.rose.rosalib.api.type;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public record Range(double min, double max) {
    public double get(Random random) {
        if (this.min == this.max) return this.min;
        return MathHelper.nextDouble(random, this.min, this.max);
    }

    public static Range of(double value) {
        return new Range(value, value);
    }
}
