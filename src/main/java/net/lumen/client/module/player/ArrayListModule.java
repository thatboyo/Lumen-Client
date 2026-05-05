package net.lumen.client.module.player;

import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.EnumSetting;

public class ArrayListModule extends Module {
    public enum Style {
        FILLED,
        TEXT,
        OUTLINED
    }

    private final EnumSetting<Style> style;

    public ArrayListModule() {
        super("Arraylist", "Displays enabled modules with accent bars.", Category.PLAYER, 0);
        style = new EnumSetting<>("Style", "Visual style for the array list.", Style.FILLED, Style.class);
        registerSetting(style);
    }
}
