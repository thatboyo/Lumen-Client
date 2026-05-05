package net.lumen.client.event;

import com.mojang.blaze3d.vertex.PoseStack;

public class EventRender3D {
    public final PoseStack matrices;
    public final float tickDelta;

    public EventRender3D(PoseStack matrices, float tickDelta) {
        this.matrices = matrices;
        this.tickDelta = tickDelta;
    }
}
