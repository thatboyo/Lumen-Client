package net.lumen.client;

import net.fabricmc.api.ClientModInitializer;
import net.lumen.client.config.ConfigManager;
import net.lumen.client.event.EventBus;
import net.lumen.client.gui.GuiManager;
import net.lumen.client.hud.HudManager;
import net.lumen.client.module.ModuleManager;
import net.lumen.client.theme.ThemeManager;

public class LumenClient implements ClientModInitializer {
    private static LumenClient instance;
    private ThemeManager themeManager;
    private EventBus eventBus;
    private ModuleManager moduleManager;
    private HudManager hudManager;
    private ConfigManager configManager;
    private GuiManager guiManager;

    public static LumenClient getInstance() {
        return instance;
    }

    public static ThemeManager getThemeManager() {
        return getInstance().themeManager;
    }

    public static EventBus getEventBus() {
        return getInstance().eventBus;
    }

    public static ModuleManager getModuleManager() {
        return getInstance().moduleManager;
    }

    public static HudManager getHudManager() {
        return getInstance().hudManager;
    }

    public static ConfigManager getConfigManager() {
        return getInstance().configManager;
    }

    public static GuiManager getGuiManager() {
        return getInstance().guiManager;
    }

    @Override
    public void onInitializeClient() {
        instance = this;
        themeManager = new ThemeManager();
        eventBus = new EventBus();
        moduleManager = new ModuleManager();
        hudManager = new HudManager();
        configManager = new ConfigManager();
        guiManager = new GuiManager();

        themeManager.initialize();
        moduleManager.initialize();
        hudManager.initialize();
        configManager.initialize();
        guiManager.initialize();
    }
}
