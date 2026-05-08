package net.lumen.client.module.player;

import net.lumen.client.hud.HudElement;
import net.lumen.client.theme.LumenTheme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Items;
import net.minecraft.inventory.Inventory;

public class PotCounterModule extends HudElement {
    public PotCounterModule(float x, float y, float scale) {
        super(x, y, scale);
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        if (!enabled) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        Inventory inventory = client.player.getInventory();

        // Count potions
        int healthPots = 0;
        int healthSplash = 0;
        int speedPots = 0;
        int strengthPots = 0;

        for (int i = 0; i < inventory.size(); i++) {
            var stack = inventory.getStack(i);
            if (stack.getItem() == Items.POTION) {
                healthPots++;
            } else if (stack.getItem() == Items.SPLASH_POTION) {
                healthSplash++;
            }
        }

        String potStr = String.format("Pots: %d | Splash: %d", healthPots, healthSplash);
        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, potStr, (int) x, (int) y, LumenTheme.active.accent);
    }
}
