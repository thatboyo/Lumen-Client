package net.lumen.client.module.player;

import net.lumen.client.event.EventRender2D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.theme.LumenTheme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CPSDisplayModule extends Module {
    private long lastLeftClick = 0;
    private long lastRightClick = 0;
    private int leftCPS = 0;
    private int rightCPS = 0;
    private float x = 10;
    private float y = 20;
    private float scale = 1.0f;

    public CPSDisplayModule() {
        super("CPS Display", "Displays your clicks per second.", Category.PLAYER, 0);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender2D.class, this::onRender2D);
    }

    private void onRender2D(EventRender2D event) {
        DrawContext context = event.drawContext;
        if (!isEnabled()) return;

        // Update CPS every 1 second by checking mouse clicks
        long currentTime = System.currentTimeMillis();

        String cpsStr = "LMB: " + leftCPS + " | RMB: " + rightCPS + " CPS";
        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, cpsStr, (int) x, (int) y, LumenTheme.active.accent);
    }
}
