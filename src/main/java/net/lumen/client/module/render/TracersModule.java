package net.lumen.client.module.render;

import net.lumen.client.event.EventRender3D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.lumen.client.setting.ColorSetting;

public class TracersModule extends Module {
    private final BooleanSetting players;
    private final BooleanSetting mobs;
    private final ColorSetting color;

    public TracersModule() {
        super("Tracers", "Draws lines from the screen center to tracked entities.", Category.RENDER, 0);
        players = new BooleanSetting("Players", "Draw tracers for players.", true);
        mobs = new BooleanSetting("Mobs", "Draw tracers for mobs.", false);
        color = new ColorSetting("Color", "Tracer line color.", 0xFF00AAFF);
        registerSetting(players);
        registerSetting(mobs);
        registerSetting(color);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender3D.class, this::onRender3D);
    }

    private void onRender3D(EventRender3D event) {
        // render tracer lines for enabled entity types.
    }
}
