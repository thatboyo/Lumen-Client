package net.lumen.client.module.movement;

import net.lumen.client.event.EventMotion;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffects;

public class NoSlowModule extends Module {
    private final BooleanSetting items;
    private final BooleanSetting cobweb;

    public NoSlowModule() {
        super("NoSlow", "Removes movement slowdowns from items and cobwebs.", Category.MOVEMENT, 0);
        items = new BooleanSetting("Items", "Prevent slowdowns from item use.", true);
        cobweb = new BooleanSetting("Cobweb", "Prevent cobweb movement penalties.", true);
        registerSetting(items);
        registerSetting(cobweb);
    }

    @Override
    protected void onEnable() {
        subscribe(EventMotion.class, this::onMotion);
    }

    private void onMotion(EventMotion event) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return;
        }

        if (items.getValue() && client.player.hasStatusEffect(StatusEffects.SLOWNESS)) {
            client.player.removeStatusEffect(StatusEffects.SLOWNESS);
        }

        if (items.getValue() && client.player.isUsingItem()) {
            client.player.setMovementSpeed(0.1f);
        }
    }
}
