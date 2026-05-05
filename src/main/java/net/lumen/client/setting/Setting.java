package net.lumen.client.setting;

import java.util.function.Function;

public abstract class Setting<T> {
    private final String name;
    private final String description;
    private T value;
    private final T defaultValue;
    private Function<T, Boolean> validator;

    protected Setting(String name, String description, T defaultValue) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.validator = v -> true;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (validator.apply(value)) {
            this.value = value;
        }
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setValidator(Function<T, Boolean> validator) {
        this.validator = validator;
    }
}
