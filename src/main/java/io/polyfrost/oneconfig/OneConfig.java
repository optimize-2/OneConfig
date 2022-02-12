package io.polyfrost.oneconfig;

import io.polyfrost.oneconfig.command.OneConfigCommand;
import io.polyfrost.oneconfig.test.TestConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = "oneconfig", name = "OneConfig", version = "${version}")
public class OneConfig {
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static File jarFile;
    public static File oneConfigDir = new File(mc.mcDataDir, "OneConfig/");
    public static File themesDir = new File(oneConfigDir, "themes/");
    public static TestConfig config = new TestConfig();

    @Mod.EventHandler
    public void onPreFMLInit(FMLPreInitializationEvent event) {
        jarFile = event.getSourceFile();
        oneConfigDir.mkdirs();
        themesDir.mkdirs();
    }

    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new OneConfigCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }
}
