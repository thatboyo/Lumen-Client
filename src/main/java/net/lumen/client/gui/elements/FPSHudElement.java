package net.lumen.client.gui.elements;

import net.lumen.client.hud.HudElement;
import net.lumen.client.theme.LumenTheme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FPSHudElement extends HudElement {
    public FPSHudElement(float x, float y, float scale) {
        super(x, y, scale);
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return;
        }
        String fps = "FPS: " + client.getCurrentFps();
        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, fps, (int)x, (int)y, LumenTheme.active.accent);
    }
}
