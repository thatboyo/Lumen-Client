package net.lumen.client.module.movement;

import net.lumen.client.event.EventTick;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;

public class ScaffoldModule extends Module {
    private final BooleanSetting safeWalk;
    private final BooleanSetting autoJump;

    public ScaffoldModule() {
        super("Scaffold", "Assists block bridging by auto-placing blocks beneath you.", Category.MOVEMENT, 0);
        safeWalk = new BooleanSetting("Safe Walk", "Prevent falling off edges while scaffolding.", true);
        autoJump = new BooleanSetting("Auto Jump", "Automatically jump when placing blocks.", false);
        registerSetting(safeWalk);
        registerSetting(autoJump);
    }

    @Override
    protected void onEnable() {
        subscribe(EventTick.class, this::onTick);
    }

    private void onTick(EventTick event) {
        // detect block placement and support scaffold movement.
    }
}
