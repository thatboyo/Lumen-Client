package net.lumen.client.gui;

import net.lumen.client.LumenClient;
import net.lumen.client.event.EventKey;
import net.lumen.client.event.EventRender2D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.KeybindSetting;
import net.minecraft.client.MinecraftClient;
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
        int panelWidth = 140;
        int panelHeight = 240;
        int startX = 20;
        int startY = 20;
        int spacing = 12;

        Category[] categories = Category.values();
        int columns = Math.min(categories.length, 4);

        for (int i = 0; i < categories.length; i++) {
            Category category = categories[i];
            int x = startX + ((i % columns) * (panelWidth + spacing));
            int y = startY + ((i / columns) * (panelHeight + spacing));

            context.fill(x, y, x + panelWidth, y + panelHeight, LumenClient.getThemeManager().getActiveTheme().panel);
            context.fill(x, y, x + panelWidth, y + 18, LumenClient.getThemeManager().getActiveTheme().background);
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, category.name(), x + 6, y + 4, LumenClient.getThemeManager().getActiveTheme().accent);

            int moduleY = y + 24;
            for (Module module : LumenClient.getModuleManager().getModules(category)) {
                if (moduleY + 14 > y + panelHeight - 6) break;
                int indicatorColor = module.isEnabled() ? LumenClient.getThemeManager().getActiveTheme().enabledIndicator : 0xFF444444;
                context.fill(x + 4, moduleY - 2, x + 8, moduleY + 10, indicatorColor);
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, module.getName(), x + 10, moduleY, LumenClient.getThemeManager().getActiveTheme().accent);
                moduleY += 14;
            }
        }
    }

    private void renderSidebar(DrawContext context, float tickDelta) {
        int width = 320;
        int height = 260;
        int x = 20;
        int y = 20;
        int sidebarWidth = 100;

        context.fill(x, y, x + width, y + height, LumenClient.getThemeManager().getActiveTheme().panel);
        context.fill(x, y, x + sidebarWidth, y + height, LumenClient.getThemeManager().getActiveTheme().background);
        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, "Lumen", x + 10, y + 10, LumenClient.getThemeManager().getActiveTheme().accent);

        int entryY = y + 30;
        for (Category category : Category.values()) {
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, category.name(), x + 10, entryY, LumenClient.getThemeManager().getActiveTheme().muted);
            entryY += 14;
        }

        int listX = x + sidebarWidth + 10;
        int listY = y + 10;
        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, "Modules", listX, listY, LumenClient.getThemeManager().getActiveTheme().accent);
        listY += 18;

        for (Module module : LumenClient.getModuleManager().getEnabled()) {
            if (listY + 12 > y + height - 10) break;
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, module.getName(), listX, listY, LumenClient.getThemeManager().getActiveTheme().accent);
            listY += 12;
        }
    }
}
