package net.lumen.client.module.legit;

import net.lumen.client.event.EventKey;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.KeybindSetting;
import net.lumen.client.setting.SliderSetting;

public class ZoomModule extends Module {
    private final SliderSetting zoomFov;
    private final SliderSetting smoothness;
    private final KeybindSetting holdKey;

    public ZoomModule() {
        super("Zoom", "Reduces FOV while held for a smooth zoom effect.", Category.LEGIT, 0);
        zoomFov = new SliderSetting("Zoom FOV", "Target FOV when zooming.", 30.0, 10.0, 90.0, 1.0);
        smoothness = new SliderSetting("Smoothness", "Speed of zoom transition.", 5.0, 1.0, 20.0, 0.5);
        holdKey = new KeybindSetting("Hold Key", "Key to hold for zoom.", 340);
        registerSetting(zoomFov);
        registerSetting(smoothness);
        registerSetting(holdKey);
    }

    @Override
    protected void onEnable() {
        subscribe(EventKey.class, this::onKey);
    }

    private void onKey(EventKey event) {
        // handle zoom key press and release.
    }
}
