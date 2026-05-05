package net.lumen.client.mixin;

import net.lumen.client.LumenClient;
import net.lumen.client.event.EventPacket;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"))
    private void onSendPacket(Packet<?> packet, CallbackInfo ci) {
        LumenClient.getEventBus().post(new EventPacket(packet));
    }
}
