package net.lumen.client.gui.elements;

import net.lumen.client.hud.HudElement;
import net.lumen.client.theme.LumenTheme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CoordinatesHudElement extends HudElement {
    public CoordinatesHudElement(float x, float y, float scale) {
        super(x, y, scale);
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) {
            return;
        }
        String coords = String.format("X: %.1f Y: %.1f Z: %.1f", client.player.getX(), client.player.getY(), client.player.getZ());
        context.drawTextWithShadow(context.textRenderer, coords, x, y, LumenTheme.active.accent);
    }
}
