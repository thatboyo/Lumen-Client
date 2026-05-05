package net.lumen.client.mixin;

import net.lumen.client.LumenClient;
import net.lumen.client.event.EventMotion;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    @Inject(method = "sendMovementPackets", at = @At("HEAD"))
    private void onSendMovementPackets(CallbackInfo ci) {
        // Post currently active rotation and onGround state for motion modification.
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;
        LumenClient.getEventBus().post(new EventMotion(self.getYaw(), self.getPitch(), self.isOnGround()));
    }
}
