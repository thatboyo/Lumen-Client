package net.lumen.client.module.ghost;

import net.lumen.client.event.EventPacket;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.EnumSetting;

public class DisablerModule extends Module {
    public enum Target {
        GRIM,
        NCP,
        AAC,
        MATRIX
    }

    private final EnumSetting<Target> target;

    public DisablerModule() {
        super("Disabler", "Sends crafted packets to confuse anticheat systems.", Category.GHOST, 0);
        target = new EnumSetting<>("Target", "Anticheat target selection.", Target.GRIM, Target.class);
        registerSetting(target);
    }

    @Override
    protected void onEnable() {
        subscribe(EventPacket.class, this::onPacket);
    }

    private void onPacket(EventPacket event) {
        // modify or inject crafted packets for the selected anticheat.
    }
}
