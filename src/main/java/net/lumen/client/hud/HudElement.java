package net.lumen.client.hud;

public abstract class HudElement {
    protected float x;
    protected float y;
    protected float scale = 1.0f;
    protected boolean enabled = true;

    public HudElement(float x, float y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getScale() {
        return scale;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract void render(net.minecraft.client.gui.DrawContext context, float tickDelta);
}
