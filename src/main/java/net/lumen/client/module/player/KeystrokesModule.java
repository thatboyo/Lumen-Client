package net.lumen.client.module.player;

import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.ColorSetting;

public class KeystrokesModule extends Module {
    private final ColorSetting activeColor;
    private final ColorSetting inactiveColor;

    public KeystrokesModule() {
        super("Keystrokes", "Renders a WASD/Mouse keystroke display.", Category.PLAYER, 0);
        activeColor = new ColorSetting("Active Color", "Color when a key is pressed.", 0xFF00FF00);
        inactiveColor = new ColorSetting("Inactive Color", "Color when a key is not pressed.", 0xFF444444);
        registerSetting(activeColor);
        registerSetting(inactiveColor);
    }
}
