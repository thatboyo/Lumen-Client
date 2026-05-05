package net.lumen.client.module.movement;

import net.lumen.client.event.EventMotion;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.EnumSetting;
import net.minecraft.client.MinecraftClient;

public class SprintModule extends Module {
    public enum Mode {
        OMNIDIRECTIONAL,
        FORWARD_ONLY
    }

    private final EnumSetting<Mode> mode;

    public SprintModule() {
        super("Sprint", "Forces omnidirectional sprinting.", Category.MOVEMENT, 0);
        mode = new EnumSetting<>("Mode", "Sprint mode.", Mode.OMNIDIRECTIONAL, Mode.class);
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

        Mode sprintMode = mode.getValue();
        
        if (sprintMode == Mode.OMNIDIRECTIONAL) {
            if (!client.player.isSprinting() && client.player.getHungerManager().getFoodLevel() > 6) {
                client.player.setSprinting(true);
            }
        } else if (sprintMode == Mode.FORWARD_ONLY) {
            if (client.options.forwardKey.isPressed() && !client.player.isSprinting() && 
                client.player.getHungerManager().getFoodLevel() > 6) {
                client.player.setSprinting(true);
            }
        }
    }
}
