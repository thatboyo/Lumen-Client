package net.lumen.client.setting;

public class SliderSetting extends Setting<Double> {
    private final double min;
    private final double max;
    private final double step;

    public SliderSetting(String name, String description, double defaultValue, double min, double max, double step) {
        super(name, description, defaultValue);
        this.min = min;
        this.max = max;
        this.step = step;
        setValidator(value -> value != null && value >= min && value <= max);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStep() {
        return step;
    }
}
