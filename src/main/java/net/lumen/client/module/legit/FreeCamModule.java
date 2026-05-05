package net.lumen.client.module.legit;

import net.lumen.client.event.EventMotion;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.SliderSetting;

public class FreeCamModule extends Module {
    private final SliderSetting speed;

    public FreeCamModule() {
        super("FreeCam", "Detaches the camera for spectator-style movement.", Category.LEGIT, 0);
        speed = new SliderSetting("Speed", "FreeCam movement speed.", 0.5, 0.1, 2.0, 0.05);
        registerSetting(speed);
    }

    @Override
    protected void onEnable() {
        subscribe(EventMotion.class, this::onMotion);
    }

    private void onMotion(EventMotion event) {
        // allow free camera movement separate from player.
    }
}
