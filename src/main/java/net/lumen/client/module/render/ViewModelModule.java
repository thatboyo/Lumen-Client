package net.lumen.client.module.render;

import net.lumen.client.event.EventRender3D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.SliderSetting;

public class ViewModelModule extends Module {
    private final SliderSetting offsetX;
    private final SliderSetting offsetY;
    private final SliderSetting offsetZ;
    private final SliderSetting fov;

    public ViewModelModule() {
        super("ViewModel", "Overrides hand/item rendering position and FOV.", Category.RENDER, 0);
        offsetX = new SliderSetting("Offset X", "Horizontal hand offset.", 0.0, -2.0, 2.0, 0.1);
        offsetY = new SliderSetting("Offset Y", "Vertical hand offset.", 0.0, -2.0, 2.0, 0.1);
        offsetZ = new SliderSetting("Offset Z", "Depth hand offset.", 0.0, -2.0, 2.0, 0.1);
        fov = new SliderSetting("FOV", "Viewmodel field of view override.", 70.0, 40.0, 120.0, 1.0);
        registerSetting(offsetX);
        registerSetting(offsetY);
        registerSetting(offsetZ);
        registerSetting(fov);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender3D.class, this::onRender3D);
    }

    private void onRender3D(EventRender3D event) {
        // adjust hand rendering and FOV overrides.
    }
}
