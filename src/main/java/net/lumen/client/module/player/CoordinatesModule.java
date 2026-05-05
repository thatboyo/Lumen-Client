package net.lumen.client.module.player;

import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;

public class CoordinatesModule extends Module {
    private final BooleanSetting showFacing;
    private final BooleanSetting showDecimals;

    public CoordinatesModule() {
        super("Coordinates", "Renders current X/Y/Z and facing cardinal direction.", Category.PLAYER, 0);
        showFacing = new BooleanSetting("Show Facing", "Display cardinal facing.", true);
        showDecimals = new BooleanSetting("Show Decimals", "Display coordinate decimals.", false);
        registerSetting(showFacing);
        registerSetting(showDecimals);
    }
}
