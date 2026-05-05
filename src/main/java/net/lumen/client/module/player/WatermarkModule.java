package net.lumen.client.module.player;

import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.EnumSetting;

public class WatermarkModule extends Module {
    public enum Position {
        TOP_LEFT,
        TOP_RIGHT
    }

    private final EnumSetting<Position> position;

    public WatermarkModule() {
        super("Watermark", "Renders the Lumen watermark in the HUD corner.", Category.PLAYER, 0);
        position = new EnumSetting<>("Position", "Watermark position on screen.", Position.TOP_LEFT, Position.class);
        registerSetting(position);
    }
}
