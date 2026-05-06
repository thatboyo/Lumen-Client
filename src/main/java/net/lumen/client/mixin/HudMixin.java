package net.lumen.client.mixin;

import net.lumen.client.LumenClient;
import net.lumen.client.event.EventRender2D;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class HudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext drawContext, float tickDelta, CallbackInfo ci) {
        LumenClient.getEventBus().post(new EventRender2D(drawContext, tickDelta));
    }
}
