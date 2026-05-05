package net.lumen.client.module.movement;

import net.lumen.client.event.EventTick;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class AutoJumpModule extends Module {
    public AutoJumpModule() {
        super("AutoJump", "Automatically jumps when movement is blocked.", Category.MOVEMENT, 0);
    }

    @Override
    protected void onEnable() {
        subscribe(EventTick.class, this::onTick);
    }

    private void onTick(EventTick event) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return;
        }

        // Check if blocked by looking at crosshair target
        if (client.crosshairTarget instanceof BlockHitResult) {
            if (client.crosshairTarget.getType() == HitResult.Type.BLOCK) {
                if ((client.options.forwardKey.isPressed() || client.options.leftKey.isPressed() || 
                     client.options.rightKey.isPressed()) && client.player.isOnGround()) {
                    client.player.jump();
                }
            }
        }
    }
}
