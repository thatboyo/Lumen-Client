package net.lumen.client.gui.elements;

import net.lumen.client.hud.HudElement;
import net.lumen.client.theme.LumenTheme;
import net.lumen.client.hud.HudElement;
import net.lumen.client.theme.LumenTheme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeystrokesHudElement extends HudElement {
    public KeystrokesHudElement(float x, float y, float scale) {
        super(x, y, scale);
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return;
        }
        int activeColor = LumenTheme.active.accent;
        int inactiveColor = LumenTheme.active.muted;
        boolean forward = InputUtil.isKeyPressed(client.getWindow().getHandle(), InputUtil.fromKeyCode(GLFW.GLFW_KEY_W, 0).getCode());
        boolean left = InputUtil.isKeyPressed(client.getWindow().getHandle(), InputUtil.fromKeyCode(GLFW.GLFW_KEY_A, 0).getCode());
        boolean back = InputUtil.isKeyPressed(client.getWindow().getHandle(), InputUtil.fromKeyCode(GLFW.GLFW_KEY_S, 0).getCode());
        boolean right = InputUtil.isKeyPressed(client.getWindow().getHandle(), InputUtil.fromKeyCode(GLFW.GLFW_KEY_D, 0).getCode());

        context.drawTextWithShadow(context.textRenderer, "W", x + 14, y, forward ? activeColor : inactiveColor);
        context.drawTextWithShadow(context.textRenderer, "A", x, y + 12, left ? activeColor : inactiveColor);
        context.drawTextWithShadow(context.textRenderer, "S", x + 14, y + 12, back ? activeColor : inactiveColor);
        context.drawTextWithShadow(context.textRenderer, "D", x + 28, y + 12, right ? activeColor : inactiveColor);
    }
}
