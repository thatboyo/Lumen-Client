package net.lumen.client.event;

import net.minecraft.client.gui.DrawContext;

public class EventRender2D {
    public final DrawContext drawContext;
    public final float tickDelta;

    public EventRender2D(DrawContext drawContext, float tickDelta) {
        this.drawContext = drawContext;
        this.tickDelta = tickDelta;
    }
}
