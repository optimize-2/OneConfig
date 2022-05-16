package cc.polyfrost.oneconfig.utils;

import cc.polyfrost.oneconfig.gui.OneConfigGui;

public class MathUtils {
    public static float clamp(float number) {
        return number < 0f ? 0f : Math.min(number, 1f);
    }

    public static float clamp(float number, float min, float max) {
        return number < min ? min : Math.min(number, max);
    }

    public static float easeOut(float current, float goal, float speed) {
        if (Math.round(Math.abs(goal - current) * 100) > 0) {
            return current + (goal - current) / speed * (OneConfigGui.INSTANCE == null ? 17 : OneConfigGui.INSTANCE.getDeltaTime());
        } else {
            return goal;
        }
    }

    public static float easeInQuad(float current) {
        return current * current;
    }

    /**
     * taken from <a href="https://github.com/jesusgollonet/processing-penner-easing">https://github.com/jesusgollonet/processing-penner-easing</a>
     */
    public static float easeInOutCirc(float t, float b, float c, float d) {
        c *= OneConfigGui.INSTANCE == null ? 17 : OneConfigGui.INSTANCE.getDeltaTime();
        if ((t /= d / 2) < 1) return -c / 2 * ((float) Math.sqrt(1 - t * t) - 1) + b;
        return c / 2 * ((float) Math.sqrt(1 - (t -= 2) * t) + 1) + b;
    }

    public static float map(float value, float start1, float stop1, float start2, float stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }
}
