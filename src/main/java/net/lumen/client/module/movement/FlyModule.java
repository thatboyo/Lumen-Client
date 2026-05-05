package net.lumen.client.module.movement;

import net.lumen.client.event.EventMotion;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.EnumSetting;
import net.lumen.client.setting.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class FlyModule extends Module {
    public enum Mode {
        CREATIVE,
        GLIDE
    }

    private final EnumSetting<Mode> mode;
    private final SliderSetting speed;

    public FlyModule() {
        super("Fly", "Enables flight either in creative or glide style.", Category.MOVEMENT, 0);
        mode = new EnumSetting<>("Mode", "Fly mode.", Mode.CREATIVE, Mode.class);
        speed = new SliderSetting("Speed", "Fly speed.", 0.8, 0.1, 3.0, 0.05);
        registerSetting(mode);
        registerSetting(speed);
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

        Mode flyMode = mode.getValue();
        double speedVal = speed.getValue();

        Vec3d forward = Vec3d.fromPolar(0, client.player.getYaw()).normalize();
        Vec3d right = Vec3d.fromPolar(0, client.player.getYaw() + 90).normalize();
        Vec3d movement = Vec3d.ZERO;

        if (client.options.forwardKey.isPressed()) {
            movement = movement.add(forward.multiply(speedVal));
        }
        if (client.options.backKey.isPressed()) {
            movement = movement.add(forward.multiply(-speedVal));
        }
        if (client.options.leftKey.isPressed()) {
            movement = movement.add(right.multiply(-speedVal));
        }
        if (client.options.rightKey.isPressed()) {
            movement = movement.add(right.multiply(speedVal));
        }

        if (flyMode == Mode.CREATIVE) {
            if (client.options.jumpKey.isPressed()) {
                movement = movement.add(0, speedVal, 0);
            }
            if (client.options.sneakKey.isPressed()) {
                movement = movement.add(0, -speedVal, 0);
            }
        }

        client.player.setVelocity(movement);
    }
}
