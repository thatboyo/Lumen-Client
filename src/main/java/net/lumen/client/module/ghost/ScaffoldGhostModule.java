package net.lumen.client.module.ghost;

import net.lumen.client.event.EventTick;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;

public class ScaffoldGhostModule extends Module {
    private final BooleanSetting onlyOnBridgingBlocks;

    public ScaffoldGhostModule() {
        super("Scaffold Ghost", "Automates sneaking only while bridging at edges.", Category.GHOST, 0);
        onlyOnBridgingBlocks = new BooleanSetting("Only On Bridging Blocks", "Only activate when bridging.", true);
        registerSetting(onlyOnBridgingBlocks);
    }

    @Override
    protected void onEnable() {
        subscribe(EventTick.class, this::onTick);
    }

    private void onTick(EventTick event) {
        // manage sneak state based on edge detection.
    }
}
