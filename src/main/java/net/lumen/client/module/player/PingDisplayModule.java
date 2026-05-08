package net.lumen.client.module.player;

import net.lumen.client.event.EventRender2D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.theme.LumenTheme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class PingDisplayModule extends Module {
    private float x = 10;
    private float y = 10;
    private float scale = 1.0f;

    public PingDisplayModule() {
        super("Ping Display", "Displays your ping.", Category.PLAYER, 0);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender2D.class, this::onRender2D);
    }

    private void onRender2D(EventRender2D event) {
        DrawContext context = event.drawContext;
        if (!isEnabled()) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.getNetworkHandler() == null || client.player == null) return;

        var entry = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid());
        if (entry == null) return;

        int ping = entry.getLatency();
        String pingStr = "Ping: " + ping + "ms";

        // Color based on ping (green good, red bad)
        int color;
        if (ping < 50) {
            color = 0xFF00FF00; // Green
        } else if (ping < 100) {
            color = 0xFFFFFF00; // Yellow
        } else {
            color = 0xFFFF0000; // Red
        }

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, pingStr, (int) x, (int) y, color);
    }
}
