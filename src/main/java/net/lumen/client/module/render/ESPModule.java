package net.lumen.client.module.render;

import net.lumen.client.event.EventRender3D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.lumen.client.setting.ColorSetting;
import net.lumen.client.setting.EnumSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

public class ESPModule extends Module {
    public enum Mode {
        FULL_BOX,
        CORNER_BRACKETS,
        OUTLINE
    }

    private final EnumSetting<Mode> mode;
    private final BooleanSetting players;
    private final BooleanSetting mobs;
    private final BooleanSetting items;
    private final ColorSetting color;

    public ESPModule() {
        super("ESP", "Renders bounding boxes around entities.", Category.RENDER, 0);
        mode = new EnumSetting<>("Mode", "Visual ESP mode.", Mode.FULL_BOX, Mode.class);
        players = new BooleanSetting("Players", "Render player ESP.", true);
        mobs = new BooleanSetting("Mobs", "Render mob ESP.", false);
        items = new BooleanSetting("Items", "Render item ESP.", false);
        color = new ColorSetting("Color", "ESP color.", 0xFF00AAFF);
        registerSetting(mode);
        registerSetting(players);
        registerSetting(mobs);
        registerSetting(items);
        registerSetting(color);
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
            if (entity == client.player) {
                continue;
            }

            boolean shouldRender = false;
            if (entity instanceof PlayerEntity && players.getValue()) {
                shouldRender = true;
            } else if (entity instanceof LivingEntity && mobs.getValue() && !(entity instanceof PlayerEntity)) {
                shouldRender = true;
            } else if (entity instanceof ItemEntity && items.getValue()) {
                shouldRender = true;
            }

            if (shouldRender) {
                Box boundingBox = entity.getBoundingBox();
                renderBox(event, boundingBox, color.getValue());
            }
        }
    }

    private void renderBox(EventRender3D event, Box box, int color) {
        // Basic box rendering can be extended here
        // This would use matrix stack and vertex consumer in practice
    }
}
