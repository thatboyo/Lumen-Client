package net.lumen.client.setting;

public class EnumSetting<T extends Enum<T>> extends Setting<T> {
    private final Class<T> enumClass;

    public EnumSetting(String name, String description, T defaultValue, Class<T> enumClass) {
        super(name, description, defaultValue);
        this.enumClass = enumClass;
    }

    public Class<T> getEnumClass() {
        return enumClass;
    }
}
