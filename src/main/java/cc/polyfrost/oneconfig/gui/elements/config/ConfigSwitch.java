package cc.polyfrost.oneconfig.gui.elements.config;

import cc.polyfrost.oneconfig.config.OneConfigConfig;
import cc.polyfrost.oneconfig.config.interfaces.BasicOption;
import cc.polyfrost.oneconfig.gui.animations.Animation;
import cc.polyfrost.oneconfig.gui.animations.DummyAnimation;
import cc.polyfrost.oneconfig.gui.animations.EaseInOutQuad;
import cc.polyfrost.oneconfig.lwjgl.RenderManager;
import cc.polyfrost.oneconfig.lwjgl.font.Fonts;
import cc.polyfrost.oneconfig.utils.color.ColorPalette;
import cc.polyfrost.oneconfig.utils.color.ColorUtils;
import cc.polyfrost.oneconfig.utils.InputUtils;
import cc.polyfrost.oneconfig.utils.MathUtils;

import java.lang.reflect.Field;

public class ConfigSwitch extends BasicOption {
    private int colorEnabled;
    private int colorDisabled;
    private Animation animation;

    public ConfigSwitch(Field field, Object parent, String name, int size) {
        super(field, parent, name, size);
    }

    @Override
    public void draw(long vg, int x, int y) {
        boolean toggled = false;
        try {
            toggled = (boolean) get();
            if (animation == null) animation = new DummyAnimation(toggled ? 0 : 1);
        } catch (IllegalAccessException ignored) {
        }
        float percentOn = animation.get();
        int x2 = x + 3 + (int) (percentOn * 18);
        boolean hovered = InputUtils.isAreaHovered(x, y, 42, 32);
        colorDisabled = ColorUtils.getColor(colorDisabled, ColorPalette.SECONDARY, hovered, false);
        colorEnabled = ColorUtils.smoothColor(colorEnabled, OneConfigConfig.PRIMARY_600, OneConfigConfig.PRIMARY_500, hovered, 40f);
        if (!isEnabled()) RenderManager.setAlpha(vg, 0.5f);
        RenderManager.drawRoundedRect(vg, x, y + 4, 42, 24, colorDisabled, 12f);
        if (percentOn != 0)
            RenderManager.drawRoundedRect(vg, x, y + 4, 42, 24, ColorUtils.setAlpha(colorEnabled, (int) (255 * percentOn)), 12f);
        RenderManager.drawRoundedRect(vg, x2, y + 7, 18, 18, OneConfigConfig.WHITE, 9f);
        RenderManager.drawText(vg, name, x + 50, y + 17, OneConfigConfig.WHITE, 14f, Fonts.MEDIUM);

        if (InputUtils.isAreaClicked(x, y, 42, 32) && isEnabled()) {
            toggled = !toggled;
            animation = new EaseInOutQuad(200, 0, 1, !toggled);
            try {
                set(toggled);
            } catch (IllegalAccessException e) {
                System.err.println("failed to write config value: class=" + this + " fieldWatching=" + field + " valueWrite=" + toggled);
                e.printStackTrace();
            }
        }
        RenderManager.setAlpha(vg, 1f);
    }

    @Override
    public int getHeight() {
        return 32;
    }
}
