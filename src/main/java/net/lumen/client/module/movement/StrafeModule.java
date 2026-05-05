package net.lumen.client.module.movement;

import net.lumen.client.event.EventMotion;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class StrafeModule extends Module {
    private final SliderSetting intensity;

    public StrafeModule() {
        super("Strafe", "Improves strafing movement for combat.", Category.MOVEMENT, 0);
        intensity = new SliderSetting("Intensity", "Strafe intensity.", 1.0, 0.5, 2.0, 0.05);
        registerSetting(intensity);
    }

    @Override
    protected void onEnable() {
        subscribe(EventMotion.class, this::onMotion);
    }

    private void onMotion(EventMotion event) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return;
        }

        double intensityVal = intensity.getValue();
        
        // Improve strafing by optimizing movement angles
        Vec3d velocity = client.player.getVelocity();
        Vec3d strafeDirection = Vec3d.fromPolar(0, client.player.getYaw() + 90).normalize();
        
        // Apply strafe motion
        double currentSpeed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
        if (currentSpeed > 0) {
            double multiplier = 1.0 + (intensityVal - 1.0) * 0.2;
            Vec3d newVelocity = velocity.multiply(multiplier);
            client.player.setVelocity(newVelocity.x, newVelocity.y, newVelocity.z);
        }
    }
}
