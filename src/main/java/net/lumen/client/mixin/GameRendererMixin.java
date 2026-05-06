package net.lumen.client.mixin;

import net.lumen.client.LumenClient;
import net.lumen.client.event.EventRender3D;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(MatrixStack matrices, float tickDelta, long startTime, CallbackInfo ci) {
        LumenClient.getEventBus().post(new EventRender3D(matrices, tickDelta));
    }
}
