package net.lumen.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lumen.client.LumenClient;
import net.lumen.client.event.EventRender3D;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;FJ)V", at = @At("TAIL"))
    private void onRender(PoseStack matrices, float tickDelta, long startTime, CallbackInfo ci) {
        LumenClient.getEventBus().post(new EventRender3D(matrices, tickDelta));
    }
}
