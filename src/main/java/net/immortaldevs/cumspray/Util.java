package net.immortaldevs.cumspray;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.Nullable;

public final class Util {
    public static MutableText rainbow(MutableText text, @Nullable NbtCompound nbt) {
        if (nbt == null || !nbt.getBoolean("cum_coated")) return text;

        return text.styled(style -> {
            int time = (int) System.currentTimeMillis();
            int rem = time & 0xff;

            return style.withColor(switch ((time % 1536) / 256) {
                case 0 -> rgb(255, rem, 0);
                case 1, -5 -> rgb(255 - rem, 255, 0);
                case 2, -4 -> rgb(0, 255, rem);
                case 3, -3 -> rgb(0, 255 - rem, 255);
                case 4, -2 -> rgb(rem, 0, 255);
                case 5, -1 -> rgb(255, 0, 255 - rem);
                default -> throw new IllegalStateException("Unexpected value: " + (time % 1536) / 256);
            });
        });
    }

    private static int rgb(int r, int g, int b) {
        return r << 16 | g << 8 | b;
    }
}
