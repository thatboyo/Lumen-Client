package net.lumen.client.module.player;

import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.lumen.client.setting.SliderSetting;

public class FPSDisplayModule extends Module {
    private final BooleanSetting showGraph;
    private final SliderSetting graphHistoryLength;

    public FPSDisplayModule() {
        super("FPS Display", "Renders current FPS and optional graph.", Category.PLAYER, 0);
        showGraph = new BooleanSetting("Show Graph", "Render an FPS history graph.", false);
        graphHistoryLength = new SliderSetting("Graph History", "Number of frames stored for the graph.", 50.0, 10.0, 120.0, 1.0);
        registerSetting(showGraph);
        registerSetting(graphHistoryLength);
    }
}
