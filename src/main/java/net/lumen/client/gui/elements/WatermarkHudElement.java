package net.lumen.client.gui.elements;

import net.lumen.client.hud.HudElement;
import net.lumen.client.theme.LumenTheme;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class WatermarkHudElement extends HudElement {
    public WatermarkHudElement(float x, float y, float scale) {
        super(x, y, scale);
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        int textColor = LumenTheme.active.accent;
        context.drawTextWithShadow(context.textRenderer, "Lumen v1.0 | 1.21.x", x, y, textColor);
        // TODO: replace the text render with watermark image asset once provided.
    }
}
