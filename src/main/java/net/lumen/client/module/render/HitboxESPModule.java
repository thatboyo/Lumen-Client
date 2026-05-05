package net.lumen.client.module.render;

import net.lumen.client.event.EventRender3D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.ColorSetting;
import net.lumen.client.setting.SliderSetting;

public class HitboxESPModule extends Module {
    private final ColorSetting color;
    private final SliderSetting expansion;

    public HitboxESPModule() {
        super("HitboxESP", "Renders expanded hitbox wireframes around players.", Category.RENDER, 0);
        color = new ColorSetting("Color", "Hitbox color.", 0xFF00FF00);
        expansion = new SliderSetting("Expansion", "Hitbox expansion amount.", 0.2, 0.0, 1.0, 0.05);
        registerSetting(color);
        registerSetting(expansion);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender3D.class, this::onRender3D);
    }

    private void onRender3D(EventRender3D event) {
        // render expanded player hitboxes.
    }
}
