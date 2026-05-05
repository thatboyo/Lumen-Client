package net.lumen.client.module.movement;

import net.lumen.client.event.EventMotion;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class SpeedModule extends Module {
    private final SliderSetting multiplier;

    public SpeedModule() {
        super("Speed", "Increases base movement speed.", Category.MOVEMENT, 0);
        multiplier = new SliderSetting("Multiplier", "Movement speed multiplier.", 1.2, 1.0, 3.0, 0.05);
        registerSetting(multiplier);
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

        Vec3d velocity = client.player.getVelocity();
        double multiplierVal = multiplier.getValue();
        
        double newX = velocity.x * multiplierVal;
        double newZ = velocity.z * multiplierVal;
        
        client.player.setVelocity(newX, velocity.y, newZ);
    }
}
