package net.lumen.client.module.player;

import net.lumen.client.hud.HudElement;
import net.lumen.client.theme.LumenTheme;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class ArmorDisplayModule extends HudElement {
    private final Boolean showDurability;

    public ArmorDisplayModule(float x, float y, float scale) {
        super(x, y, scale);
        this.showDurability = true;
    }

    @Override
    public void render(DrawContext context, float tickDelta) {
        if (!enabled) return;

        int armorY = (int) y;
        int armorX = (int) x;

        // Render armor slots horizontally
        ItemStack helmet = MinecraftClient.getInstance().player.getInventory().armor.get(3);
        ItemStack chestplate = context.getMinecraftClient().player.getInventory().armor.get(2);
        ItemStack leggings = context.getMinecraftClient().player.getInventory().armor.get(1);
        ItemStack boots = context.getMinecraftClient().player.getInventory().armor.get(0);

        ItemStack[] armorItems = {helmet, chestplate, leggings, boots};

        for (int i = 0; i < armorItems.length; i++) {
            ItemStack armor = armorItems[i];
            if (!armor.isEmpty()) {
                context.drawItem(armor, armorX + (i * 18), armorY);
                if (showDurability && armor.isDamaged()) {
                    int damage = armor.getMaxDamage() - armor.getDamage();
                    String durStr = damage + "";
                    context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, durStr, armorX + (i * 18), armorY + 10, LumenTheme.active.muted);
                }
            }
        }
    }
}
