package net.lumen.client.module.combat;

import net.lumen.client.event.EventTick;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.lumen.client.setting.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class AutoHitModule extends Module {
    private final SliderSetting cooldownThreshold;
    private final BooleanSetting targetMobs;

    public AutoHitModule() {
        super("Auto Hit", "Automatically times attacks when the crosshair is over a valid target.", Category.COMBAT, 0);
        this.isGhost = true;
        cooldownThreshold = new SliderSetting("Cooldown Threshold", "Wait before attacking.", 70.0, 50.0, 100.0, 1.0);
        targetMobs = new BooleanSetting("Target Mobs", "Attack mobs as well as players.", false);
        registerSetting(cooldownThreshold);
        registerSetting(targetMobs);
    }

    @Override
    protected void onEnable() {
        subscribe(EventTick.class, this::onTick);
    }

    private void onTick(EventTick event) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || client.interactionManager == null) {
            return;
        }

        Entity target = client.targetedEntity;
        if (!(target instanceof LivingEntity)) {
            return;
        }

        if (target instanceof PlayerEntity && !targetMobs.getValue()) {
            if (client.player.getAttackCooldownProgress((float) (cooldownThreshold.getValue() / 100.0)) >= 1.0f) {
                client.interactionManager.attackEntity(client.player, target);
            }
        } else if (!targetMobs.getValue()) {
            if (client.player.getAttackCooldownProgress((float) (cooldownThreshold.getValue() / 100.0)) >= 1.0f) {
                client.interactionManager.attackEntity(client.player, target);
            }
        }
    }
}
