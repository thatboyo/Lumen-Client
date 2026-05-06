package net.lumen.client.module.render;

import net.lumen.client.event.EventRender3D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.lumen.client.setting.ColorSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import com.mojang.blaze3d.systems.RenderSystem;

public class TracersModule extends Module {
    private final BooleanSetting players;
    private final BooleanSetting mobs;
    private final ColorSetting color;

    public TracersModule() {
        super("Tracers", "Draws lines from the screen center to tracked entities.", Category.RENDER, 0);
        players = new BooleanSetting("Players", "Draw tracers for players.", true);
        mobs = new BooleanSetting("Mobs", "Draw tracers for mobs.", false);
        color = new ColorSetting("Color", "Tracer line color.", 0xFF00AAFF);
        registerSetting(players);
        registerSetting(mobs);
        registerSetting(color);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender3D.class, this::onRender3D);
    }

    private void onRender3D(EventRender3D event) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        Vec3d cameraPos = client.gameRenderer.getCamera().getPos();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);

        for (Entity entity : client.world.getEntities()) {
            if (entity == client.player) continue;

            boolean shouldDraw = false;
            if (players.getValue() && entity instanceof PlayerEntity) shouldDraw = true;
            if (mobs.getValue() && entity instanceof MobEntity) shouldDraw = true;

            if (shouldDraw) {
                Vec3d entityPos = entity.getPos();
                int col = color.getValue();
                float r = ((col >> 16) & 0xFF) / 255.0f;
                float g = ((col >> 8) & 0xFF) / 255.0f;
                float b = (col & 0xFF) / 255.0f;
                float a = ((col >> 24) & 0xFF) / 255.0f;

                // Line from camera to entity
                bufferBuilder.vertex(event.matrices, (float)(cameraPos.x - cameraPos.x), (float)(cameraPos.y - cameraPos.y), (float)(cameraPos.z - cameraPos.z)).color(r, g, b, a).next();
                bufferBuilder.vertex(event.matrices, (float)(entityPos.x - cameraPos.x), (float)(entityPos.y - cameraPos.y), (float)(entityPos.z - cameraPos.z)).color(r, g, b, a).next();
            }
        }

        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }
}
