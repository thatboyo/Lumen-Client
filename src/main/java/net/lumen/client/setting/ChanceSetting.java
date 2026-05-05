package net.lumen.client.setting;

import java.util.Random;

public class ChanceSetting extends SliderSetting {
    private final Random random = new Random();

    public ChanceSetting(String name, String description, double defaultValue) {
        super(name, description, defaultValue, 0.0, 100.0, 1.0);
    }

    public boolean roll() {
        return random.nextDouble() * 100.0 <= getValue();
    }
}
