package net.lumen.client.event;

import net.minecraft.client.util.math.MatrixStack;

public class EventRender3D {
    public final MatrixStack matrices;
    public final float tickDelta;

    public EventRender3D(MatrixStack matrices, float tickDelta) {
        this.matrices = matrices;
        this.tickDelta = tickDelta;
    }
}
