package net.lumen.client.module.ghost;

package net.lumen.client.module.ghost;

import net.lumen.client.event.EventMotion;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.EnumSetting;
import net.minecraft.client.MinecraftClient;

public class AntiAimModule extends Module {
    public enum Mode {
        STATIC_DOWN,
        SPIN,
        BACKWARDS
    }

    private final EnumSetting<Mode> mode;

    public AntiAimModule() {
        super("AntiAim", "Injects invalid head rotations without moving the visible camera.", Category.GHOST, 0);
        mode = new EnumSetting<>("Mode", "Anti-aim mode.", Mode.STATIC_DOWN, Mode.class);
        registerSetting(mode);
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

        switch (mode.getValue()) {
            case STATIC_DOWN -> client.player.setYaw(0);
            case SPIN -> client.player.setYaw(client.player.getYaw() + 10);
            case BACKWARDS -> client.player.setYaw(client.player.getYaw() + 180);
        }
    }
}
