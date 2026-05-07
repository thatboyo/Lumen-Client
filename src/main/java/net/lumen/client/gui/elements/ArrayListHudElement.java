package net.lumen.client.gui.elements;

import net.lumen.client.LumenClient;
import net.lumen.client.hud.HudElement;
import net.lumen.client.module.Module;
import net.lumen.client.module.ModuleManager;
import net.lumen.client.theme.LumenTheme;import net.minecraft.client.MinecraftClient;import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class ArrayListHudElement extends HudElement {
    public ArrayListHudElement(float x, float y, float scale) {
        super(x, y, scale);
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        ModuleManager manager = LumenClient.getModuleManager();
        if (manager == null) {
            return;
        }
        float currentY = y;
        for (Module module : manager.getEnabled()) {
            int textColor = LumenTheme.active.accent;
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, module.getName(), (int)x, (int)currentY, textColor);
            currentY += 10 * scale;
        }
    }
}
