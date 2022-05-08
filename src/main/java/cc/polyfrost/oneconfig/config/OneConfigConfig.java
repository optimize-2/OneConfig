package cc.polyfrost.oneconfig.config;

import cc.polyfrost.oneconfig.config.interfaces.Config;
import com.google.gson.JsonParser;
import cc.polyfrost.oneconfig.config.data.Mod;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class OneConfigConfig extends Config {

    public static String currentProfile = "Default Profile";
    public static ArrayList<String> favoriteMods = new ArrayList<>();

    public static final int TRANSPARENT = new Color(0, 0, 0, 0).getRGB();                // Transparent
    public static final int TRANSPARENT_25 = new Color(0, 0, 0, 25).getRGB();         // Transparent 25% (used because of NanoVG culling)

    public static final int BLACK = new Color(0,0,0,255).getRGB();                     // Black
    public static final int GRAY_900 = new Color(13, 14, 15, 255).getRGB();           // Gray 900
    public static final int GRAY_900_80 = new Color(13, 14, 15, 204).getRGB();         // Gray 900 80%
    public static final int GRAY_800 = new Color(21, 22, 23, 255).getRGB();           // Gray 800
    public static final int GRAY_700 = new Color(34, 35, 38, 255).getRGB();           // Gray 700
    public static final int GRAY_600 = new Color(42, 44, 48, 255).getRGB();           // Gray 600
    public static final int GRAY_500 = new Color(49, 51, 56, 255).getRGB();           // Gray 500         // button sidebar hover, button gray normal
    public static final int GRAY_500_80 = new Color(49, 51, 56, 204).getRGB();        // Gray 500 80%     // button sidebar pressed

    public static final int GRAY_400 = new Color(55, 59, 69, 255).getRGB();           // Gray 400
    public static final int GRAY_300 = new Color(73, 79, 92, 255).getRGB();           // Gray 300         // button gray hover
    public static final int GRAY_200 = new Color(100, 107, 125, 255).getRGB();        // Gray 200
    public static final int GRAY_400_80 = new Color(55, 59, 69, 204).getRGB();        // Gray 400 80%     // button gray pressed
    public static final int BLUE_800 = new Color(13, 51, 128, 255).getRGB();          // Blue 800
    public static final int BLUE_700 = new Color(18, 71, 178, 255).getRGB();          // Blue 700
    public static final int BLUE_700_80 = new Color(18, 71, 178, 204).getRGB();       // Blue 700 80%
    public static final int BLUE_600 = new Color(20, 82, 204, 255).getRGB();          // Blue 600         // button blue normal
    public static final int BLUE_600_80 = new Color(20, 82, 204, 204).getRGB();       // Blue 600 80%     // button blue click
    public static final int BLUE_500 = new Color(25, 103, 255, 255).getRGB();         // Blue 500         // button blue hover
    public static final int BLUE_400 = new Color(48, 129, 242, 255).getRGB();
    public static final int BLUE_400_80 = new Color(48, 129, 242, 204).getRGB();
    public static final int WHITE_50 = new Color(255, 255, 255, 127).getRGB();        // White 60%
    public static final int WHITE_60 = new Color(255, 255, 255, 153).getRGB();        // White 60%
    public static final int WHITE_80 = new Color(255, 255, 255, 204).getRGB();        // White 80%
    public static final int WHITE_90 = new Color(255, 255, 255, 229).getRGB();        // White 90%
    public static final int WHITE_95 = new Color(255, 255, 255, 242).getRGB();        // White 90%
    public static final int WHITE = new Color(255, 255, 255, 255).getRGB();           // White 100%

    public static final int ERROR_700 = new Color(180, 24, 24, 255).getRGB();         // Red 700

    public static boolean ROUNDED_CORNERS = true;
    public static float CORNER_RADIUS_WIN = 20f;
    public static float CORNER_RADIUS = 12f;

    public static boolean thirdPartyAll = false;

    public OneConfigConfig() {
        super(null, "OneConfig.json");
    }

    @Override
    public void init(Mod mod) {
        if (new File("OneConfig/" + configFile).exists()) load();
        else save();
    }

    @Override
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get("OneConfig/" + configFile)), StandardCharsets.UTF_8))) {
            writer.write(gson.toJson(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("OneConfig/" + configFile)), StandardCharsets.UTF_8))) {
            deserializePart(new JsonParser().parse(reader).getAsJsonObject(), this.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
