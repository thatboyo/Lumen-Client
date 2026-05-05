package net.lumen.client.setting;

public class ColorSetting extends Setting<Integer> {
    public ColorSetting(String name, String description, int defaultValue) {
        super(name, description, defaultValue);
    }

    public void setHex(String hex) {
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        try {
            int value = (int) Long.parseLong(hex, 16);
            setValue(value);
        } catch (NumberFormatException ignored) {
        }
    }
}
