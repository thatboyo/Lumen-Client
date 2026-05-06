package net.lumen.client.gui;

import net.lumen.client.LumenClient;
import net.lumen.client.event.EventKey;
import net.lumen.client.event.EventRender2D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.KeybindSetting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;

import java.util.List;

public class GuiManager {
    private static GuiManager instance;
    private GuiStyle activeStyle = GuiStyle.CLASSIC;
    private boolean guiOpen = false;
    private final KeybindSetting guiKeybind = new KeybindSetting("GUI Keybind", "Key to open/close the ClickGUI.", InputUtil.GLFW_KEY_RIGHT_SHIFT);

    public GuiManager() {
        instance = this;
    }

    public static GuiManager getInstance() {
        return instance;
    }

    public void initialize() {
        activeStyle = GuiStyle.CLASSIC;
        LumenClient.getEventBus().subscribe(EventRender2D.class, this::onRender2D);
        LumenClient.getEventBus().subscribe(EventKey.class, this::onKeyEvent);
    }

    private void onRender2D(EventRender2D event) {
        render(event.drawContext, event.tickDelta);
    }

    private void onKeyEvent(EventKey event) {
        if (event.action != 1) {
            return;
        }
        if (event.key == guiKeybind.getValue()) {
            toggleGui();
        }
    }

    public boolean isGuiOpen() {
        return guiOpen;
    }

    public void setGuiOpen(boolean open) {
        this.guiOpen = open;
    }

    public void toggleGui() {
        this.guiOpen = !this.guiOpen;
    }

    public GuiStyle getActiveStyle() {
        return activeStyle;
    }

    public void setActiveStyle(GuiStyle activeStyle) {
        if (activeStyle == null) {
            activeStyle = GuiStyle.CLASSIC;
        }
        this.activeStyle = activeStyle;
    }

    public void render(DrawContext context, float tickDelta) {
        if (!guiOpen) {
            return;
        }
        if (activeStyle == GuiStyle.CLASSIC) {
            renderClassic(context, tickDelta);
        } else {
            renderSidebar(context, tickDelta);
        }
    }

    private void renderClassic(DrawContext context, float tickDelta) {
        // Basic implementation of Classic GUI
        // TODO: Implement full draggable panels, settings widgets, animations

        int panelWidth = 120;
        int panelHeight = 200;
        int startX = 20;
        int startY = 20;
        int spacing = 10;

        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            Category category = categories[i];
            int x = startX + (i * (panelWidth + spacing));
            int y = startY;

            // Panel background
            context.fill(x, y, x + panelWidth, y + panelHeight, LumenClient.getThemeManager().getActiveTheme().panel);

            // Category title
            context.drawTextWithShadow(context.textRenderer, category.name(), x + 5, y + 5, LumenClient.getThemeManager().getActiveTheme().accent);

            // Modules list (simplified)
            var modules = LumenClient.getModuleManager().getModules(category);
            int moduleY = y + 20;
            for (Module module : modules) {
                if (moduleY + 15 > y + panelHeight) break; // Simple clipping

                // Module name
                int color = module.isEnabled() ? LumenClient.getThemeManager().getActiveTheme().enabledIndicator : LumenClient.getThemeManager().getActiveTheme().muted;
                context.drawTextWithShadow(context.textRenderer, module.getName(), x + 5, moduleY, color);

                moduleY += 12;
            }
        }
    }

    private void renderSidebar(DrawContext context, float tickDelta) {
        int width = 240;
        int height = 220;
        int x = 20;
        int y = 20;
        int background = 0xCC000000;
        context.fill(x, y, x + width, y + height, background);
        context.drawTextWithShadow(context.textRenderer, "Lumen Sidebar GUI", x + 10, y + 10, LumenClient.getThemeManager().getActiveTheme().accent);
    }
}
