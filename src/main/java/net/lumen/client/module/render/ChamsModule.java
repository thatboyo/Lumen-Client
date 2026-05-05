package net.lumen.client.module.render;

import net.lumen.client.event.EventRender3D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.lumen.client.setting.ColorSetting;
import net.lumen.client.setting.EnumSetting;

public class ChamsModule extends Module {
    public enum ChamMode {
        FLAT,
        GLOW
    }

    private final EnumSetting<ChamMode> mode;
    private final ColorSetting color;
    private final BooleanSetting throughWalls;

    public ChamsModule() {
        super("Chams", "Replaces entity models with flat colored fills.", Category.RENDER, 0);
        mode = new EnumSetting<>("Mode", "Chams rendering mode.", ChamMode.FLAT, ChamMode.class);
        color = new ColorSetting("Color", "Chams color.", 0xFF00FF00);
        throughWalls = new BooleanSetting("Through Walls", "Only apply when entities are behind walls.", true);
        registerSetting(mode);
        registerSetting(color);
        registerSetting(throughWalls);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender3D.class, this::onRender3D);
    }

    private void onRender3D(EventRender3D event) {
        // override model rendering color.
    }
}
