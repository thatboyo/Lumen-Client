package net.lumen.client.module.render;

import net.lumen.client.event.EventRender3D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.lumen.client.setting.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class NametagsModule extends Module {
    private final BooleanSetting showHealth;
    private final BooleanSetting showDistance;
    private final SliderSetting scale;

    public NametagsModule() {
        super("Nametags", "Renders enhanced floating nametags above entities.", Category.RENDER, 0);
        showHealth = new BooleanSetting("Show Health", "Display entity health.", true);
        showDistance = new BooleanSetting("Show Distance", "Display entity distance.", true);
        scale = new SliderSetting("Scale", "Nametag size scaling.", 1.0, 0.5, 3.0, 0.1);
        registerSetting(showHealth);
        registerSetting(showDistance);
        registerSetting(scale);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender3D.class, this::onRender3D);
    }

    private void onRender3D(EventRender3D event) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null || client.player == null) {
            return;
        }

        for (Entity entity : client.world.getEntities()) {
            if (entity == client.player || !(entity instanceof LivingEntity)) {
                continue;
            }

            LivingEntity living = (LivingEntity) entity;
            StringBuilder display = new StringBuilder();

            // Add entity name or player name
            if (entity instanceof PlayerEntity) {
                display.append(entity.getName().getString());
            } else {
                display.append(entity.getType().getName().getString());
            }

            // Add health if enabled
            if (showHealth.getValue()) {
                display.append(" ").append(String.format("%.1f", living.getHealth())).append("❤");
            }

            // Add distance if enabled
            if (showDistance.getValue()) {
                double distance = client.player.distanceTo(entity);
                display.append(" ").append(String.format("%.1fm", distance));
            }

            // Rendering would be done here with DrawContext and text renderer
            // This is a placeholder that would render the display string above the entity
        }
    }
}
